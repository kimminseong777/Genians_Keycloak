package io.security.oauth2.springsecurityoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(2)
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 모든 요청은 인증을 필요로 합니다.
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            // OAuth2 로그인을 사용합니다.
            .oauth2Login()
            // 인증 성공 후 리디렉트할 URL을 지정합니다.
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                    // 리디렉트할 URL을 response에 설정합니다.
                    response.sendRedirect("http://localhost:3000");
                }
            });
        return http.build();
    }
}
