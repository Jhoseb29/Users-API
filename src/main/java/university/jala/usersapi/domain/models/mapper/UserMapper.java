package university.jala.usersapi.domain.models.mapper;

import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.UserDTO;
import university.jala.usersapi.domain.models.dto.UserDTOById;


public class UserMapper {

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user The user entity to convert.
     * @return The corresponding UserDTO.
     */
    public static UserDTO convertToDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        return userDTO;
    }

    /**
     * Converts a User entity to a detailed UserDTO for response.
     *
     * @param user The user entity to convert.
     * @return The corresponding detailed UserDTO for response.
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
