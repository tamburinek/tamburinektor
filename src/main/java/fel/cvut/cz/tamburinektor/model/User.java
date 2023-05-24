package fel.cvut.cz.tamburinektor.model;

import fel.cvut.cz.tamburinektor.model.enums.UserType;
import lombok.*;

import javax.persistence.Column;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;


@Entity
@Table(name = "Users")
@Getter @Setter @NoArgsConstructor
public class User{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;
}
