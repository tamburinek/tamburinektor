package fel.cvut.cz.tamburinektor.model.lecture;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
