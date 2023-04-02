package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.UserDto;
import fel.cvut.cz.tamburinektor.model.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return null;
    }

    public User toUser(UserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setUserType(userDTO.getUserType());
        return user;
    }
}
