package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Question")
@Getter
@Setter
@NoArgsConstructor
public class Question extends LectureEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 200)
    private String question;

    @Column(nullable = false)
    private boolean anonymous;

    @OneToMany
    private List<QuestionAnswer> answers;
}
