package bokka.question.bokkaquestionapi.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtConfig jwtConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).authorizeRequests(auth -> auth.anyRequest().authenticated()).oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
            try {

                jwt.decoder(jwtConfig.jwtDecoder());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));
        return http.build();
    }
}
