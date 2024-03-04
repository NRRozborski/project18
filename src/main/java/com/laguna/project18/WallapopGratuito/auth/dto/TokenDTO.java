package com.laguna.project18.WallapopGratuito.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String accessToken;
    private RefreshTokenDTO refreshTokenDTO;
    private String tokenType;
}
