package com.laguna.project18.WallapopGratuito.auth.service;


import com.laguna.project18.WallapopGratuito.auth.dto.OAuthTokenDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.OAuthUserDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.TokenDTO;
import com.laguna.project18.WallapopGratuito.dto.UserResponseDTO;
import com.laguna.project18.WallapopGratuito.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final UserService userService;
    private final RestClient restClient = RestClient.create();
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${token.expiration-time}")
    private Long TOKEN_EXPIRATION_TIME;
    @Value("${token.secret}")
    private String SECRET;

    public OAuthTokenDTO getAccessToken(String authorizationCode){
        ResponseEntity<OAuthTokenDTO> tokenResponse = restClient.post()
                .uri("https://oauth2.googleapis.com/token?" +
                                "client_id={googleClientId}&" +
                                "client_secret={googleClientSecret}&" +
                                "redirect_uri=http://localhost:8080/auth/callback&" +
                                "grant_type=authorization_code&" +
//                                "scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile&" +
                                "code={authorizationCode}",
                        GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET, authorizationCode
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

    public TokenDTO genetateToken(String email) {

        String token = Jwts.builder()
                .id("WallapopGratuito")
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();

        return new TokenDTO(
                token,
                null,
                "Bearer"
        );
    }

}
