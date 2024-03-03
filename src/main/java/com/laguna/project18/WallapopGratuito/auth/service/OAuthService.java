package com.laguna.project18.WallapopGratuito.auth.service;


import com.laguna.project18.WallapopGratuito.auth.dto.OAuthTokenDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthUserDTO;
import com.laguna.project18.WallapopGratuito.dto.UserResponseDTO;
import com.laguna.project18.WallapopGratuito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final UserService userService;
    private RestClient restClient = RestClient.create();
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    public OAuthTokenDTO getAccessToken(String authorizationCode){
        ResponseEntity<OAuthTokenDTO> tokenResponse = restClient.post()
                .uri("https://oauth2.googleapis.com/token?" +
                                "client_id={googleClientId}&" +
                                "client_secret={googleClientSecret}&" +
                                "redirect_uri=http://localhost:8080/auth/callback&" +
                                "grant_type=authorization_code&" +
//                                "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&" +
                                "code={authorizationCode}",
                        googleClientId, googleClientSecret, authorizationCode
                )
                .retrieve()
                .toEntity(OAuthTokenDTO.class);

        return tokenResponse.getBody();
    }

    public OAuthUserDTO getOAuthUser(String accessToken){
        ResponseEntity<OAuthUserDTO> userInfo = restClient.get()
                .uri("https://www.googleapis.com/oauth2/v1/userinfo")
                .header("Authorization", "Bearer "+ accessToken)
                .retrieve().
                toEntity(OAuthUserDTO.class);

        return userInfo.getBody();
    }

    public Boolean userExists(OAuthUserDTO oAuthUserDTO){
        return userService.userExistsByEmail(oAuthUserDTO.getEmail());
    }

    public UserResponseDTO createUser(OAuthUserDTO oAuthUserDTO){
        return userService.createUser(oAuthUserDTO);
    }

}
