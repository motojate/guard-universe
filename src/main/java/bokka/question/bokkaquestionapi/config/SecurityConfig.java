package bokka.question.bokkaquestionapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtConfig jwtConfig;

    @Bean
    public PublicKey publicKey() throws Exception {
//        String publicKeyPem =  Files.readString(Path.of(new ClassPathResource("jwt_public_key.pem").getURI()));

        String publicKeyPem =  "-----BEGIN PUBLIC KEY-----\n" +
                "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAwCRRVqPZi9CcjI+RLd/Y\n" +
                "6Mzx0ARsrMj/c8KoDo3cm8B4Q2TImwhy3gzN1D5j77aWlBoaDOVjcbhAFSbJAeqT\n" +
                "KXHjHF+k16IYvfldS30DlFdKOGYgCJrmLbXx/CPWHvdx+9Ws8LJ0qQgS9q/5Skss\n" +
                "wu3xP7+oweBZR1DcEKAK4z2RpXxfRp8/wiTM63RLoEYmJUD/IkHl41mlHhoqKI/4\n" +
                "Ikv41Rr6BGWD3yFCdHI+5WpXQMHzTleuOuyrFQTYBgTtnymObsQN3XKSRlDgAf0N\n" +
                "kzRSJu0g2+E0nS7wb9Od4LcQSCSA9b5vOdy2llv9wLQGbB+rB+ytsP/SeIerlCbC\n" +
                "YgxJpWlStLNweLq9eIVMqcVwDsoB3RxfdQ976+xU9Rg9pe2znX+ONkWp7fQgKfwn\n" +
                "xIoVrtETcYBFbcad80cWoztUFvk421z8s/b4RARN1kc/NW36fIUk3+wf+hTKMvle\n" +
                "fOVEmf2G8WjdCRFZC+W4XKxR2kDLq1S0Io94cgVWu55qxHAfimQDf0UZzbOCyu6K\n" +
                "tAwRBDO1ZayFGepzZItQ/vX+Xao9o3P0Bpj56gQquDlFjJiFydjWlg7Cmf0klppu\n" +
                "NrWhjbYy2BikqDxItIWiRzUb3Iv8AM/cklrb8k6YXwWY/R2TkxTVzV0vCj9v7ODL\n" +
                "P82aFVLmgpTMeBFog3ekgoMCAwEAAQ==\n" +
                "-----END PUBLIC KEY-----";
        publicKeyPem = publicKeyPem.replace("-----BEGIN PUBLIC KEY-----", "").replace("-----END PUBLIC KEY-----", "").replaceAll("\\s", "");
        byte[] decoded = Base64.getDecoder().decode(publicKeyPem);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtToeknVerifier jwtTokenVerifier = new JwtToeknVerifier(publicKey());

        // csrf disable
        http.csrf(AbstractHttpConfigurer::disable).addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class);

        // form 로그인 방식 disable
        http.formLogin(AbstractHttpConfigurer::disable);

        // http basic 인증 방식 disable
        http.httpBasic(AbstractHttpConfigurer::disable);

        // 인가 설정
        http.authorizeHttpRequests((auth) -> auth.anyRequest().authenticated());

        // 세션 설정 - 가장 중요한 작업
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
