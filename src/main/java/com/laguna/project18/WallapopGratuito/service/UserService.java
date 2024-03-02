package com.laguna.project18.WallapopGratuito.service;

import com.laguna.project18.WallapopGratuito.dto.UserRequestDTO;
import com.laguna.project18.WallapopGratuito.dto.UserResponseDTO;
import com.laguna.project18.WallapopGratuito.mapper.UserMapper;
import com.laguna.project18.WallapopGratuito.model.User;
import com.laguna.project18.WallapopGratuito.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user = userRepository.save(
                userMapper.toModel(userRequestDTO)
        );
        return userMapper.toResponse(user);
    }
}
