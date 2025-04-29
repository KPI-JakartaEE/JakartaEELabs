package ua.kpi.jakartaee.utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

// JWT utils
public class Jwt {

    // Oh no, secret key leaked!..
    private static final Key KEY =
            new SecretKeySpec("SOME_PRETTY_LONG_AND_HOPEFULLY_SECURE_SECRET_KEY".getBytes(),
                    SignatureAlgorithm.HS256.getJcaName());
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour
    private static final String ISSUER = "JakartaEELabs";

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer(ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 1 hour
                .signWith(KEY)
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException  e) { // Any JWT exception, assume any throw just indicates invalid JWT.
            return false;
        }
    }
}
