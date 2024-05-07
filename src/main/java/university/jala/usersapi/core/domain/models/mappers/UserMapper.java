package university.jala.usersapi.core.domain.models.mappers;

import university.jala.usersapi.core.domain.models.entities.User;
import university.jala.usersapi.core.domain.models.dto.response.UserGetAllResponseDTO;
import university.jala.usersapi.core.domain.models.dto.response.UserDTOById;


public class UserMapper {

    /**
     * Converts a User entity to a UserGetAllResponseDTO.
     *
     * @param user The user entity to convert.
     * @return The corresponding UserGetAllResponseDTO.
     */
    public static UserGetAllResponseDTO convertToDTO(final User user) {
        UserGetAllResponseDTO userGetAllResponseDTO
            = new UserGetAllResponseDTO();
        userGetAllResponseDTO.setId(user.getId());
        userGetAllResponseDTO.setName(user.getName());
        userGetAllResponseDTO.setLogin(user.getLogin());
        return userGetAllResponseDTO;
    }

    /**
     * Converts a User entity to a detailed UserGetAllResponseDTO for response.
     *
     * @param user The user entity to convert.
     * @return The corresponding detailed UserGetAllResponseDTO for response.
     */
    public static UserDTOById convertToDetailedDTO(final User user) {
        UserDTOById userDTOById = new UserDTOById();
        userDTOById.setId(user.getId());
        userDTOById.setName(user.getName());
        userDTOById.setLogin(user.getLogin());
        userDTOById.setPassword(user.getPassword());
        return userDTOById;
    }

    protected UserMapper() {
        throw new UnsupportedOperationException();
    }
}
