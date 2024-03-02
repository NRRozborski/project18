package com.laguna.project18.WallapopGratuito.auth.controller;

import com.laguna.project18.WallapopGratuito.auth.dto.LoginRequestDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.TokenDTO;
import com.laguna.project18.WallapopGratuito.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

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

//        OAuthDTO oAuthDTO = new OAuthDTO();

        RestClient restClient = RestClient.create();
        ResponseEntity<String> result = restClient.post()
                .uri("https://oauth2.googleapis.com/token?" +
                                "client_id={googleClientId}&" +
//                                "response_type=code&" +
                                "client_secret={googleClientSecret}&" +
                                "redirect_uri=http://localhost:8080/auth/callback&" +
                                "grant_type=authorization_code&" +
//                                "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&" +
                                "code={code}",
                        googleClientId, googleClientSecret, code

                        )
//                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                //TODO: Pasar a distinta entidad para recoger token
                .toEntity(String.class);


//        System.out.println("Response status: " + result.getStatusCode());
//        System.out.println("Response headers: " + result.getHeaders());
//        System.out.println("Contents: " + result.getBody());

        return result.getBody();
    }

//    @PostMapping
//    public String getTokenFromAuthorizationCode(@RequestParam("access_token") String accessToken){
//        System.out.println(accessToken);
//        return accessToken;
//    }

}
