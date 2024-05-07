package university.jala.usersapi.core.application.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


/**
 * UserDataService class responsible for generating and validating JWT tokens.
 */
@Service
public class JwtService {

  /**
   * Secret key used for signing JWT tokens.
   */
  @Value("${jwt.secret}")
  private String secretKey;

  /**
   * Constant representing the duration of one hour in milliseconds.
   */
  private static final int ADD_ONE_HOUR = 1000 * 100 * 36;

  /**
   * Generates a JWT token for the given user details.
   *
   * @param userDetails The user details to be included in the token.
   * @param userId      The user _id.
   * @return The JWT token.
   */
  public String getToken(final UserDetails userDetails, final String userId) {
    HashMap<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("id", userId);
    return getToken(extraClaims, userDetails);
  }

  /**
   * Generates a JWT token with additional claims for the given user details.
   *
   * @param extraClaims Additional claims to be included in the token.
   * @param userDetails The user details to be included in the token.
   * @return The JWT token.
   */
  private String getToken(final HashMap<String, Object> extraClaims,
      final UserDetails userDetails) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(
            new Date(System.currentTimeMillis() + ADD_ONE_HOUR))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * Retrieves the key used for signing JWT tokens.
   *
   * @return The signing key.
   */
  private Key getKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  /**
   * Retrieves the login username from the provided JWT token.
   *
   * @param token The JWT token.
   * @return The login username extracted from the token.
   */
  public String getLoginFromToken(final String token) {
    return getClaim(token, Claims::getSubject);
  }

  /**
   * Validates whether the provided JWT token is valid for the given user
   * details.
   *
   * @param token       The JWT token to be validated.
   * @param userDetails The user details to be validated against the token.
   * @return True if the token is valid for the user details, otherwise false.
   */
  public boolean isTokenValid(final String token,
      final UserDetails userDetails) {
    final String userLogin = getLoginFromToken(token);
    return (userLogin.equals(userDetails.getUsername())
        && !isTokenExpired(token));
  }

  /**
   * Retrieves all claims from the provided JWT token.
   *
   * @param token The JWT token.
   * @return All claims extracted from the token.
   */
  private Claims getAllClaims(final String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * Retrieves a specific claim from the provided JWT token.
   *
   * @param token          The JWT token.
   * @param claimsResolver Resolves the desired claim from the token's claims.
   * @param <T>            The type of the desired claim.
   * @return The resolved claim.
   */
  public <T> T getClaim(final String token,
      final Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaims(token);
    return claimsResolver.apply(claims);
  }

  /**
   * Retrieves the expiration date of the provided JWT token.
   *
   * @param token The JWT token.
   * @return The expiration date of the token.
   */
  private Date getExpiration(final String token) {
    return getClaim(token, Claims::getExpiration);
  }


  /**
   * Checks whether the provided JWT token has expired.
   *
   * @param token The JWT token.
   * @return True if the token has expired, otherwise false.
   */
  private boolean isTokenExpired(final String token) {
    return getExpiration(token).before(new Date());
  }
}

