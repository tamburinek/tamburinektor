package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @OneToOne
    private Image imageLink;
}
