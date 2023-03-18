package fel.cvut.cz.tamburinektor.model.lecture;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "LectureEntity")
@Getter
@Setter
@NoArgsConstructor
public class LectureEntity {
    @Id
    @GeneratedValue
    private Long id;
}
