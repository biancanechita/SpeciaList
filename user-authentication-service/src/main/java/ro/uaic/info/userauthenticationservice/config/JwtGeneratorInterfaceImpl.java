package ro.uaic.info.userauthenticationservice.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.uaic.info.userauthenticationservice.entities.User;

@Service
public class JwtGeneratorInterfaceImpl implements JwtGeneratorInterface {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generateJwtToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("userId", user.getId());
        claims.put("role", user.getRole());

        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        return jwtToken;
    }
}
