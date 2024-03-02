package com.laguna.project18.WallapopGratuito.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OAuthUserDTO {
//    private Long id;
    private Boolean verified_email;
    private String name;
    private String email;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;
}
