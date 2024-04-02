package university.jala.usersapi.domain.service;

import university.jala.usersapi.domain.models.dto.AuthenticationRequestDTO;
import university.jala.usersapi.domain.models.dto.AuthenticationResponseDTO;
import university.jala.usersapi.domain.models.dto.RegisterRequestDTO;

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
