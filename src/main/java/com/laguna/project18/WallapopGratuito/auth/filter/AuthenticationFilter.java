package com.laguna.project18.WallapopGratuito.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final OAuth2AuthorizedClientService clientService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        Authentication authentication =
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication();
//
//        OAuth2AuthenticationToken oauthToken =
//                (OAuth2AuthenticationToken) authentication;
//
//        OAuth2AuthorizedClient client =
//                clientService.loadAuthorizedClient(
//                        oauthToken.getAuthorizedClientRegistrationId(),
//                        oauthToken.getName());
//
//        String accessToken = client.getAccessToken().getTokenValue();
//            logger.info(accessToken);
//            logger.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            filterChain.doFilter(request, response);
    }
}
