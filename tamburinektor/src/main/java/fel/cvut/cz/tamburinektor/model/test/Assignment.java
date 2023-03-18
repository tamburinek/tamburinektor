package fel.cvut.cz.tamburinektor.model.test;

import fel.cvut.cz.tamburinektor.model.lecture.Image;
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
@Table(name = "Assignment")
@Getter
@Setter
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int point;

    @Column(nullable = false)
    private String question;

    @OneToOne
    private Image imageLink;

    @Column(nullable = false)
    private boolean openQuestion;

    @Column
    @ElementCollection
    private List<String> rightAnswers;

    @Column
    @ElementCollection
    private List<String> wrongAnswers;
}
