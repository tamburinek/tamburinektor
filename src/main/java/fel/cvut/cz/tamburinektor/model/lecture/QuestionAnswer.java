package fel.cvut.cz.tamburinektor.model.lecture;

import fel.cvut.cz.tamburinektor.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "QuestionAnswer")
@Getter
@Setter
@NoArgsConstructor
public class QuestionAnswer {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String message;

    @ManyToOne(optional = false)
    private User createdBy;
}
