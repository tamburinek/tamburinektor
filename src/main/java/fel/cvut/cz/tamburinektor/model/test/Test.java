package fel.cvut.cz.tamburinektor.model.test;

import fel.cvut.cz.tamburinektor.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Test")
@Getter
@Setter
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String description;

    @OneToOne
    private User createBy;

    @ManyToMany
    @OrderColumn
    private List<Assignment> assignments;
}
