package university.jala.usersapi.domain.models.mapper;

import university.jala.usersapi.domain.models.User;
import university.jala.usersapi.domain.models.dto.UserDTO;


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
    public static UserDTO convertToDetailedDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        // Puedes agregar más campos aquí si es necesario
        return userDTO;
    }

    protected UserMapper() {
        // Constructor protegido para evitar instanciación
        throw new UnsupportedOperationException();
    }
}
