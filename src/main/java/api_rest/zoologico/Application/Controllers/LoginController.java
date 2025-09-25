package api_rest.zoologico.Application.Controllers;

import api_rest.zoologico.Application.DTOs.LoginDto;
import api_rest.zoologico.Domain.Models.Admin;
import api_rest.zoologico.Infra.Security.TokenJwt;
import api_rest.zoologico.Infra.Security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login") // Alterado para /auth/login para corresponder ao caminho do frontend
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto data){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        Authentication auth = authenticationManager.authenticate(token);
        TokenJwt JWT = new TokenJwt(tokenService.generateToken((Admin) auth.getPrincipal()));
        return ResponseEntity.ok(JWT);
    }
}
