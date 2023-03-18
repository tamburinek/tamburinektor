package fel.cvut.cz.tamburinektor.model.lecture;

import fel.cvut.cz.tamburinektor.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Definition")
@Getter
@Setter
@NoArgsConstructor
public class Definition extends LectureEntity {

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false, length = 1000)
    private String definition;

    @OneToOne
    private Image imageLink;

    @OneToOne(optional = false)
    private User createdBy;
}
