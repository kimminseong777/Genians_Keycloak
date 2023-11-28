// OAuth2LoginSuccessHandler.java 파일
package io.security.oauth2.springsecurityoauth2.handler;

import io.security.oauth2.springsecurityoauth2.model.KeycloakUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String registrationId = token.getAuthorizedClientRegistrationId();

        if(registrationId.equals("keycloak")){
            // 인증 성공 후 리디렉트할 URL을 state 값에 따라 결정
            KeycloakUser keycloakUser = (KeycloakUser) token.getPrincipal();
            String state = keycloakUser.getState();
            if(state.equals("dashboard")){
                // dashboard 페이지로 리디렉트
                getRedirectStrategy().sendRedirect(request, response, "/dashboard");
            } else {
                // 기본 페이지로 리디렉트
                getRedirectStrategy().sendRedirect(request, response, "/");
            }
        } else {
            // 다른 OAuth2 제공자의 경우 기본 페이지로 리디렉트
            getRedirectStrategy().sendRedirect(request, response, "/");
        }
    }
}

