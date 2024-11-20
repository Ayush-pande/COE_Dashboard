package com.amdocs.coe_dashboard;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {

    private String secretKey = "aabesd-adasdfe-outhem";  // Use a strong secret key in real apps!

    // Generate a JWT token
    public String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 1 hour
                .sign(algorithm);
    }

    // Extract username from the token
    public String extractUsername(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    // Validate the token
    public boolean validateToken(String token, String username) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return (username.equals(extractUsername(token)) && !isTokenExpired(decodedJWT));
    }

    // Check if the token has expired
    private boolean isTokenExpired(DecodedJWT decodedJWT) {
        return decodedJWT.getExpiresAt().before(new Date());
    }
}

