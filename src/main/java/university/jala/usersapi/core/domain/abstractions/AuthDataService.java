package university.jala.usersapi.core.domain.abstractions;

import university.jala.usersapi.core.domain.models.dto.request.AuthenticationRequestDTO;
import university.jala.usersapi.core.domain.models.dto.response.AuthenticationResponseDTO;
import university.jala.usersapi.core.domain.models.dto.request.RegisterRequestDTO;

/**
 * Authentication Service Abstraction.
 */
public interface AuthDataService {

  /**
   * Performs the login process.
   *
   * @param authenticationRequest authentication request
   * @return authentication response
   * @throws Exception if an error occurs during the login process
   */
  AuthenticationResponseDTO login(
      AuthenticationRequestDTO authenticationRequest) throws Exception;

  /**
   * Performs the registration process for a new user.
   *
   * @param registerRequest registration request
   * @return authentication response
   * @throws Exception if an error occurs during the registration process
   */
  AuthenticationResponseDTO register(
      RegisterRequestDTO registerRequest) throws Exception;


}
