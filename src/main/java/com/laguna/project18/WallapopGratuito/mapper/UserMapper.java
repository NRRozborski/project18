package com.laguna.project18.WallapopGratuito.mapper;

import com.laguna.project18.WallapopGratuito.auth.dto.OAuthUserDTO;
import com.laguna.project18.WallapopGratuito.dto.UserRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.UserResponseDTO;
import com.laguna.project18.WallapopGratuito.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public User toModel(UserRequestDTO userRequestDTO) {
        return  new User(

        );
    }

    public User toModel(OAuthUserDTO oAuthUserDTO){
        return new User(
                0L,
                null,
                oAuthUserDTO.getEmail(),
                oAuthUserDTO.getGiven_name(),
                oAuthUserDTO.getFamily_name(),
                null,
                0,
                null,
                null
        );
    }

    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getName(),
                user.getSurname()
        );
    }
}
