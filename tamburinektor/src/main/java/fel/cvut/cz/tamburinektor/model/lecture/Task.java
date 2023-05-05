package fel.cvut.cz.tamburinektor.model.lecture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "Task")
@Getter
@Setter
@NoArgsConstructor
public class Task extends LectureEntity{

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @Column
    private String questionImage;

    @Column
    private String answerImage;
}
