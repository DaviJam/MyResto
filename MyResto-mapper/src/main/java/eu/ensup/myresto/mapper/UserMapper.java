package eu.ensup.myresto.mapper;

import eu.ensup.myresto.business.User;
import eu.ensup.myresto.dto.UserDTO;

/**
 * The type User mapper.
 */
public class UserMapper {
    /**
     * Business to user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public static UserDTO businessToDto(User user){
        UserDTO userDto = new UserDTO();
        userDto.setSurname(user.getSurname());
        userDto.setFirstname(user.getFirstname());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setRole(user.getRole());
        userDto.setId(user.getId());
        return userDto;
    };

    /**
     * Dto to business user.
     *
     * @param userDto the user dto
     * @return the user
     */
     public static User dtoToBusiness(UserDTO userDto){
        User user = new User();
        user.setSurname(userDto.getSurname());
        user.setFirstname(userDto.getFirstname());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        user.setId(userDto.getId());
        return user;
    };
}
