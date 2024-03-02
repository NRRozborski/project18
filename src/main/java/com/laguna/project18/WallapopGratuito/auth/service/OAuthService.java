package com.laguna.project18.WallapopGratuito.auth.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final UserDetailService userDetailService;
}
