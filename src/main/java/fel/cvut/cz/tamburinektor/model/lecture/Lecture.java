package fel.cvut.cz.tamburinektor.model.lecture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import fel.cvut.cz.tamburinektor.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Lecture")
@Getter
@Setter
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToOne
    private User createdBy;

    @ManyToMany
    @OrderColumn
    private List<LectureEntity> lectureEntities;

    @OneToOne(optional = true)
    private  LectureEntity lastEntity;

    @Column
    private boolean isOpen = false;
}
