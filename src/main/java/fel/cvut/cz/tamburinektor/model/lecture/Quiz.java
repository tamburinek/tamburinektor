package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Quiz")
@Getter
@Setter
@NoArgsConstructor
public class Quiz extends LectureEntity{

    @Column(nullable = false)
    private String name;

    @OneToMany
    private List<QuizQuestion> questions;

}
