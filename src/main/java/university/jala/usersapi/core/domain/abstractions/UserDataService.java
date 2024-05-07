package university.jala.usersapi.core.domain.abstractions;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import university.jala.usersapi.core.domain.models.dto.response.UserGetAllResponseDTO;
import university.jala.usersapi.core.domain.models.dto.response.UserDTOById;
import university.jala.usersapi.core.domain.models.entities.User;

/**
 * Service for managing user data.
 */
@Service
public interface UserDataService {

  /**
   * Retrieves a list of user DTOs with pagination.
   *
   * @param page the page number
   * @param size the number of items per page
   * @return a list of user DTOs
   */
  List<UserGetAllResponseDTO> getAllUsersDTO(int page, int size);

  /**
   * Retrieves a user DTO by ID.
   *
   * @param userId the ID of the user
   * @return an optional containing the user DTO if found, otherwise empty
   */
  Optional<User> getUserById(String userId);

  /**
   * Updates a user by ID.
   *
   * @param request the user DTO containing updated data
   * @param id      the ID of the user to update
   * @return the updated user DTO
   */
  User updateByID(UserDTOById request, String id) throws Exception;

  /**
   * Deletes a user by ID.
   *
   * @param id the ID of the user to delete
   * @return an optional containing the deleted user DTO if found and deleted,
   * otherwise empty
   */
  User deleteById(String id);
}
