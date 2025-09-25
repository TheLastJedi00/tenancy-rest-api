package api_rest.zoologico.Infra.Security;

import api_rest.zoologico.Domain.Models.Admin;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Admin admin){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Zoo API")
                    .withSubject(admin.getLogin())
                    .withClaim("Admin: ", admin.getName())
                    .withExpiresAt(expires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro na geração do token: " + exception.getMessage());
        }
    }

    public String getSubject(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Zoo API")
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido ou expirado: " + exception.getMessage());
        }
    }

    public Instant expires(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
