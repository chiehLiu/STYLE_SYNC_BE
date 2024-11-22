package StyleSync.demo.controller;

import StyleSync.demo.dto.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private static final String SECRET_KEY = "1234";
  private Set<String> tokenBlacklist = new HashSet<>();

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    // Dummy validation - replace with your actual user validation
    if ("admin".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
      String token = Jwts.builder()
        .setSubject(loginRequest.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.status(401).body("Invalid credentials");
    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(@RequestHeader("Authorization") String token) {
    if (token != null && token.startsWith("Bearer ")) {
      String jwt = token.substring(7);
      tokenBlacklist.add(jwt);
      return ResponseEntity.ok("Logged out successfully");
    }
    return ResponseEntity.badRequest().body("Invalid token");
  }

  public boolean isTokenBlacklisted(String token) {
    return tokenBlacklist.contains(token);
  }
}