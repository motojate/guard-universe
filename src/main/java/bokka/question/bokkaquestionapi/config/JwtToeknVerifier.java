package bokka.question.bokkaquestionapi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtToeknVerifier extends OncePerRequestFilter {
    private final PublicKey publicKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractToken(request);
        log.info(token);
        log.info(this.publicKey.toString());
        if (token != null && !token.isEmpty()) {
            try {

                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(publicKey)
                        .parseClaimsJws(token);
                log.info("{}", claimsJws.toString());
                // 여기서 Claims를 사용하여 SecurityContext에 인증 정보 설정 가능
                // 예: UsernamePasswordAuthenticationToken authentication = ...
                // SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // 토큰 유효성 검증 실패 처리
                throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }

        return Arrays.stream(cookies)
                .filter(cookie -> "access_token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
    }
}
