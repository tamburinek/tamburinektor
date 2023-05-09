package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @Column
    private String imageLink;
}
