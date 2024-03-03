package com.laguna.project18.WallapopGratuito.auth.controller;

import com.laguna.project18.WallapopGratuito.auth.dto.LoginRequestDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthTokenDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthUserDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.TokenDTO;
import com.laguna.project18.WallapopGratuito.auth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

//    private final OAuth2AuthorizedClientService authorizedClientService;
    private final OAuthService oAuthService;


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
        OAuthTokenDTO oAuthTokenDTO = oAuthService.getAccessToken(code);
        OAuthUserDTO oAuthUserDTO = oAuthService.getOAuthUser(oAuthTokenDTO.getAccess_token());

        if(oAuthService.userExists(oAuthUserDTO)){
            oAuthService.logIn();
        }else{
            oAuthService.createUser(oAuthUserDTO);
        }

        return "Logged in!";
    }

}
