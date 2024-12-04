package com.amdocs.coe_dashboard.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthForgotPassword {

    private final String SECRET_KEY = "forgot_password_Secret";  // Use a secure key here
    private final long EXPIRATION_TIME = 3_600_000L; // 10 days in milliseconds

    public AuthForgotPassword() {
    }

    public String generateJwtToken(String email) {
        System.out.println(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        return JWT.create()
                .withSubject(email) // Store the email in the JWT
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration date
                .withIssuer("your-app")  // You can replace with your app name
                .sign(Algorithm.HMAC256(SECRET_KEY)); // Sign with a secret key
    }

    public DecodedJWT validateJwtToken(String token) throws JWTVerificationException {
        try {
            // Create JWT verifier using the SECRET_KEY and ISSUER to validate the token
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer("your-app") // Ensure the token was issued by your app
                    .build();  // Create the verifier

            // Verify the token and return the decoded JWT
//            DecodedJWT d = verifier.verify(token);
            return verifier.verify(token);

        } catch (JWTVerificationException e) {
            // If verification fails (invalid token, expired token, etc.), throw an exception
            throw new JWTVerificationException("Invalid or expired token");
        }
    }

    public String getEmployeeEmail(String token) {
        try {
            // Decode the JWT token
            DecodedJWT decodedJWT = JWT.decode(token);

            // Return the subject from the decoded JWT
            return decodedJWT.getSubject(); // This returns the 'sub' claim, which is typically the subject of the token
        } catch (Exception e) {
            // Handle invalid token or decoding error
            System.out.println("Error decoding token: " + e.getMessage());
            return null;
        }
    }
}
