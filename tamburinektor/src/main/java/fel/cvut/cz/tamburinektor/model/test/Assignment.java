package fel.cvut.cz.tamburinektor.model.test;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
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
@Table(name = "Assignment")
@Getter
@Setter
@NoArgsConstructor
public class Assignment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int point = 1;

    @Column(nullable = false)
    private String question;

    @Column
    private String imageLink;

    @Column(nullable = false)
    private boolean openQuestion;

    @Column
    @ElementCollection
    private List<String> rightAnswers;

    @Column
    @ElementCollection
    private List<String> wrongAnswers;

    @OneToOne
    private User createBy;
}
