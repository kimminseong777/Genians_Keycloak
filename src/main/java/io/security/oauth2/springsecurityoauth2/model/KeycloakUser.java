package io.security.oauth2.springsecurityoauth2.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class KeycloakUser extends OAuth2ProviderUser implements OAuth2User{

    public KeycloakUser(OAuth2User oAuth2User, ClientRegistration clientRegistration){
        super(oAuth2User.getAttributes(), oAuth2User, clientRegistration);
    }

    @Override
    public String getId() {
        return (String)getAttributes().get("sub");
    }

    @Override
    public String getUsername() {
        return (String)getAttributes().get("preferred_username");
    }

    // state 값을 저장할 수 있도록 state 속성을 추가
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Override
    public String getName() {
        // 키클락 사용자의 이름을 반환하는 로직
        // 예를 들어, username을 사용할 수 있음
        return getUsername();
    }

}
