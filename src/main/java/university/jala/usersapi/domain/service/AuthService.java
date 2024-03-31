package university.jala.usersapi.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import university.jala.usersapi.domain.service.security.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.service.security.dto.AuthenticationResponseDTO;
import university.jala.usersapi.persistance.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponseDTO login(AuthenticationRequestDTO authenticationRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(),
            authenticationRequest.getPassword()));

    UserDetails userDetails = userRepository.findByLogin(authenticationRequest.getLogin()).orElseThrow();
    String token = jwtService.getToken(userDetails);

    return AuthenticationResponseDTO.builder()
        .token(token)
        .build();
  }

}
