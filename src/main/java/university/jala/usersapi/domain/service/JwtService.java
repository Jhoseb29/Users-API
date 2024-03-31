package university.jala.usersapi.domain.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.security.Key;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  private static final String SECRET_KEY
      = "SABR0SIT0ZTEAMRULES1984738949394U738Y4VIVASD31284021478371353151";

  private static final int ADD_ONE_DAY = 1000 * 60 * 24;

  public String getToken(UserDetails userDetails) {
    return getToken(new HashMap<>(), userDetails);
  }

  private String getToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + ADD_ONE_DAY))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  private Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String getLoginFromToken(String token) {
    return getClaim(token, Claims::getSubject);
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String userLogin = getLoginFromToken(token);
    return (userLogin.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private Claims getAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date getExpiration(String token) {
    return getClaim(token, Claims::getExpiration);
  }

  private boolean isTokenExpired(String token) {
    return getExpiration(token).before(new Date());
  }
}
