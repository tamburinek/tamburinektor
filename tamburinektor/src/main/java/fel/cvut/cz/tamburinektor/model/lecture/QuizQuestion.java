package fel.cvut.cz.tamburinektor.model.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "QuizQuestion")
@Getter
@Setter
@NoArgsConstructor
public class QuizQuestion {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String question;

    @OneToOne
    private Image imageLink;

    @Column
    @ElementCollection
    private List<String> rightAnswers;

    @Column
    @ElementCollection
    private List<String> wrongAnswers;
}
