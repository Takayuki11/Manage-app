package com.example.manage;

import com.example.manage.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JWTProvider {

    private static final String TOKEN_SECRET_KEY = "ThisIsSecretKey";

//    トークンの有効時間（一時間）
    private static final long TOKEN_VALID_DURATION = 1000L * 60L * 60L;

    public String createToken(User user) {
        Claims claim = Jwts.claims().setSubject(String.valueOf(user.getId()));
        claim.put("name", user.getName());
        claim.put("email", user.getEmail());
//        claim.put("role", user.getRole());

        Date iat = new Date();
        Date exp = new Date(iat.getTime() + TOKEN_VALID_DURATION);

        return Jwts.builder()
                .setClaims(claim)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY)
                .compact();
    }

    public String getToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

    public Claims getClaims(String token){
        return Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
