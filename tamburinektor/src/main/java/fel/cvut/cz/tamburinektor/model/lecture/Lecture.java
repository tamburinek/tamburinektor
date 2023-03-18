package fel.cvut.cz.tamburinektor.model.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Lecture")
@Getter
@Setter
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToMany
    @OrderColumn
    private List<LectureEntity> lectureEntities;
}
