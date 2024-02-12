package com.laguna.project18.WallapopGratuito.auth.controller;

import com.laguna.project18.WallapopGratuito.auth.dto.LoginRequestDTO;
import com.laguna.project18.WallapopGratuito.auth.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@CrossOrigin("http://localhost:3000")
@CrossOrigin
@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/callback")
    public ResponseEntity<TokenDTO> login(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        return null;
    }

    @GetMapping("/login-success")
    public String getLoginInfo(OAuth2AuthenticationToken authentication) {
        OAuth2AuthorizedClient client = authorizedClientService
                .loadAuthorizedClient(
                        authentication.getAuthorizedClientRegistrationId(),
                        authentication.getName());

        System.out.println("sadffads sad fasdfafs af adsf s");

        return authentication.getName() + authentication.getPrincipal().getAttribute("email");
    }

    //FLOW:
    //El usuario pulsa en el botón login en el front el cual le lleva a la ventana de autenticación
    //del proveedor (github en este caso), introduce sus credenciales, y se devuelve un "authorization code"
    //en la uri especificada como callback, en este paso es cuando entra el back a funcionar. Desde el callback,
    //aún en el front, se hace una petición al back, con el código de autorización incluido, el cual se intercambiará
    //por un token de acceso. Debajo se puede apreciar este último punto.
    @SneakyThrows
    @PostMapping("/callback")
    public String token(@RequestParam(name = "code") String code){
        URL url = new URL(
                "https://github.com/login/oauth/access_token?" +
                        "client_id=d93b33cb1d04107b10e4&" +
                        "client_secret=676a9e723fa265a1da0a9a6ea4ff4c1343c1f772&" +
                        "redirect_uri=http://localhost:3000/callback&code="+code
        );
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");

        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        //TODO:
        //Falta hacer una llamada al endpoint de usuario (ver postman), obtener la información del usuario y
        //guardarlo en la base de datos si aún no se había logueado. ¿También generar token para dicho usuario
        // /guardarlo en el contexto de seguridad?.

        //TODO:
        //ORGANIZAR CÓDIGO


        return content.toString();
    }
}
