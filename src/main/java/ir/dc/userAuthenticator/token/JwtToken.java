package ir.dc.userAuthenticator.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtToken {
    private static String secret = "asdfSFS34wfsdfsdfS33232dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public static  String  generateToken(Map map,long expiresIn){

        Instant now = Instant.now();
        String jwtToken = Jwts.builder().addClaims(map)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expiresIn, ChronoUnit.DAYS)))
                .signWith(generateKey())
                .compact();
        return jwtToken;
    }
    public static Jws<Claims> parseJwt(String jwtString) {
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);

        return jwt;
    }
    private static Key generateKey(){
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        return hmacKey;
    }
}
