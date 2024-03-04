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

    private final OAuthService oAuthService;

    @RequestMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return null;
    }

    @GetMapping("/callback")
    public ResponseEntity<TokenDTO> callback(@RequestParam("code") String code) {
        OAuthTokenDTO oAuthTokenDTO = oAuthService.getAccessToken(code);
        OAuthUserDTO oAuthUserDTO = oAuthService.getOAuthUser(oAuthTokenDTO.getAccess_token());

        if(!oAuthService.userExists(oAuthUserDTO)){
            oAuthService.createUser(oAuthUserDTO);
        }

        return ResponseEntity.ok(
                oAuthService.genetateToken(oAuthUserDTO.getEmail())
        );
    }
}
