package com.ikshusaram.demo.security;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.*;

import io.github.cdimascio.dotenv.Dotenv;

import com.ikshusaram.demo.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

    private Dotenv dotenv;
    private final String SECRET_KEY = (String) dotenv.get("SECRET_KEY"); // This needs to be in .env
    private final long ACCESS_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 1; // single day
    private final long REFRESH_TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 7; // 7 days

    public JwtUtil(Dotenv dotenv){
        this.dotenv = dotenv; 
    }
    public String generateAccessToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        fillUserTokenDetails(user, claims);
        return Jwts.builder().setClaims(claims).setSubject("accessToken").setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public void fillUserTokenDetails(UserEntity user, Map<String, Object> claims) {
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());
        claims.put("id", user.getId());
    }

    public String generateRefreshToken(UserEntity user) {
        Map<String, Object> claims = new HashMap<String, Object>();
        fillUserTokenDetails(user, claims);
        return Jwts.builder().setClaims(claims).setSubject("refreshToken").setIssuedAt(new java.util.Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserEntity userDetails) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(userDetails.getId()) && !isTokenExpired(token));
    } 
     public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getId);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

  

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date());
    }

    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    // public String extractMobileNo(String token) {
    //     return extractClaim(token, claims -> claims.get("mobileNo", String.class));
    // }

    // public String extractAppType(String token) {
    //     return extractClaim(token, claims -> claims.get("appType", String.class));
    // }
    // public List<String> extractAuthorities(String token) {
    //     return extractClaim(token, claims -> claims.get("authorities", List.class));
    // }

    public boolean validateToken(String token, String email, String mobileNo) {
        final String extractedEmail = extractEmail(token);
        // final String extractedMobileNo = extractMobileNo(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

}
