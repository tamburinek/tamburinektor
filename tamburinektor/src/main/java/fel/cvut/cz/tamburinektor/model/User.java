package fel.cvut.cz.tamburinektor.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.Entity;


@Entity
@Table(name = "Users")
@Getter @Setter @NoArgsConstructor
public class User{

    @Id
    private String username;

}
