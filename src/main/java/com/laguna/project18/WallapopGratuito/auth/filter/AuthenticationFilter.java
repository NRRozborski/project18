package com.laguna.project18.WallapopGratuito.auth.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final String HEADER_AUTHORIZACION_KEY = "Authorization";
    private final String TOKEN_BEARER_PREFIX = "Bearer ";
    @Value("${token.secret}")
    private String SECRET;

    public Boolean isJWTValid(HttpServletRequest request){
        String authenticationHeader = request.getHeader(HEADER_AUTHORIZACION_KEY);
        if(authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_BEARER_PREFIX)){
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(isJWTValid(request)){
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                    .build()
                    .parseSignedClaims(
                            request.getHeader(HEADER_AUTHORIZACION_KEY).replace(TOKEN_BEARER_PREFIX, "")
                    )
                    .getPayload();

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(claims.getSubject(), null, null);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }
            filterChain.doFilter(request, response);
    }
}
