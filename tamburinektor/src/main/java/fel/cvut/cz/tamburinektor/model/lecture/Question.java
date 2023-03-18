package fel.cvut.cz.tamburinektor.model.lecture;

import fel.cvut.cz.tamburinektor.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @OneToOne
    private Image imageLink;

    @OneToOne(optional = false)
    private User createdBy;

    @Column(nullable = false)
    private boolean anonymous = true;
}
