package fel.cvut.cz.tamburinektor.model.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
