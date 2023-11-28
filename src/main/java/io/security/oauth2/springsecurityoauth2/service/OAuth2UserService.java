// OAuth2UserService.java 파일
package io.security.oauth2.springsecurityoauth2.service;

import io.security.oauth2.springsecurityoauth2.model.KeycloakUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String registrationId = clientRegistration.getRegistrationId();

        if(registrationId.equals("keycloak")){
            KeycloakUser keycloakUser = new KeycloakUser(oAuth2User, clientRegistration);
            // state 값을 가져와서 keycloakUser에 저장
            String state = userRequest.getAdditionalParameters().get("state").toString();
            keycloakUser.setState(state);
            return keycloakUser;
        }

        return oAuth2User;
    }
}

