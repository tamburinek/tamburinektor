package fel.cvut.cz.tamburinektor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Classroom")
@Getter
@Setter
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = true)
    private String password;

    @Column(unique = true)
    private String name;

    @OneToOne
    private User createBy;

    @OneToMany
    private List<User> users;
}
