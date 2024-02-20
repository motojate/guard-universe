package bokka.question.bokkaquestionapi.common.interceptor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.GenericFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class TokenAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = extractAccessToken(request);
        String userSeq = authenticate(accessToken);
        request.setAttribute("userSeq", userSeq);
        if (userSeq != null) return true;
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or missing token");
            return false;
        }

    }


    private String extractAccessToken(HttpServletRequest request) throws NoSuchElementException {
        Cookie[] cookies = request.getCookies();
        return Optional.ofNullable(cookies).flatMap(cs -> Arrays.stream(cs).filter(c -> "access_token".equals(c.getName())).findFirst()).map(Cookie::getValue).orElseThrow(() -> new NoSuchElementException("No Token"));
    }

    private String authenticate(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String authServerUrl = "http://localhost:3100/api/auth/jwt/check";

        Map<String, String> tokensMap = Map.of("accessToken", accessToken);
        Map<String, Object> requestBody = Map.of("tokens", tokensMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(authServerUrl, entity, String.class);

        return response.getBody();
    }
}
