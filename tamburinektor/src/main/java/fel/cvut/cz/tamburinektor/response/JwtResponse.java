package fel.cvut.cz.tamburinektor.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String role;

    public JwtResponse(String accessToken, String username, String role) {
        this.token = accessToken;
        this.username = username;
        this.role = role;
    }
}
