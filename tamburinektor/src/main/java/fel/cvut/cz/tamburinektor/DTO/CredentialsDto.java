package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CredentialsDto {

    private String username;

    private String password;

    public CredentialsDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
