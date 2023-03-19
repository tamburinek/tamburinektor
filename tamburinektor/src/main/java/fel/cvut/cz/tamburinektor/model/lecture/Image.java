package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Image")
@Getter
@Setter
@NoArgsConstructor
public class Image extends LectureEntity {

    @Column(nullable = false)
    private String imageLink;

    @Column(nullable = false)
    private String description;

}
