package com.laguna.project18.WallapopGratuito.auth.controller;

import com.laguna.project18.WallapopGratuito.auth.dto.LoginRequestDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthTokenDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthUserDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.TokenDTO;
import com.laguna.project18.WallapopGratuito.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//    private final OAuth2AuthorizedClientService authorizedClientService;
    private final OAuthService oAuthService;
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @RequestMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDto){
        return null;
    }

//    @GetMapping("/login-success")
//    public String getLoginInfo(OAuth2AuthenticationToken authentication) {
//        OAuth2AuthorizedClient client = authorizedClientService
//                .loadAuthorizedClient(
//                        authentication.getAuthorizedClientRegistrationId(),
//                        authentication.getName());
//
//        return authentication.getName() + authentication.getPrincipal().getAttribute("email");
//    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {

        RestClient restClient = RestClient.create();
        ResponseEntity<OAuthTokenDTO> tokenResponse = restClient.post()
                .uri("https://oauth2.googleapis.com/token?" +
                                "client_id={googleClientId}&" +
                                "client_secret={googleClientSecret}&" +
                                "redirect_uri=http://localhost:8080/auth/callback&" +
                                "grant_type=authorization_code&" +
//                                "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&" +
                                "code={code}",
                        googleClientId, googleClientSecret, code
                        )
//                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(OAuthTokenDTO.class);

        String accessToken = tokenResponse.getBody().getAccess_token();

        ResponseEntity<OAuthUserDTO> userInfo = restClient.get()
                .uri("https://www.googleapis.com/oauth2/v1/userinfo")
                .header("Authorization", "Bearer "+ accessToken)
                .retrieve().
                toEntity(OAuthUserDTO.class);

        return userInfo.getBody().toString();
    }

//    @PostMapping
//    public String getTokenFromAuthorizationCode(@RequestParam("access_token") String accessToken){
//        System.out.println(accessToken);
//        return accessToken;
//    }

}
