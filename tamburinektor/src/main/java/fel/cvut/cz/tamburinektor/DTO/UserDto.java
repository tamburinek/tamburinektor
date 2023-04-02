package fel.cvut.cz.tamburinektor.DTO;

import fel.cvut.cz.tamburinektor.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private UserType userType;

}
