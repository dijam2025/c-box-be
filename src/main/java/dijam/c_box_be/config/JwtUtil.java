package dijam.c_box_be.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final String secret = "wL6QfhQxyuEnHw3U8lQvB+TtUtFxOAEkxRPtHqCtaK8="; // Base64 인코딩된 256비트 키
    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

    private final long expirationMs = 1000 * 60 * 60; // 1시간

    public String generateToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key)
                .compact();
    }

    public String validateTokenAndGetUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
