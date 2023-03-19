package fel.cvut.cz.tamburinektor.model.test;

import fel.cvut.cz.tamburinektor.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "TestResult")
@Getter
@Setter
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(optional = false)
    private User writtenBy;

    @Column(nullable = false)
    private int result;

    @OneToOne(optional = false)
    private Test test;
}
