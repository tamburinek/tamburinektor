package fel.cvut.cz.tamburinektor.model.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
