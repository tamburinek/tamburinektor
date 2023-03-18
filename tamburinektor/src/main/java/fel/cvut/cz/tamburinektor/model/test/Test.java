package fel.cvut.cz.tamburinektor.model.test;

import fel.cvut.cz.tamburinektor.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
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

    @Column(nullable = false)
    private boolean randomOrder = false;

    @OneToOne
    private User createBy;

    @OrderColumn
    @OneToMany
    private List<Assignment> assignments;
}
