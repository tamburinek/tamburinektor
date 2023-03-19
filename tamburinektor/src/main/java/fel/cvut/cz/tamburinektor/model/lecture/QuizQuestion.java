package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
