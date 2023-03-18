package fel.cvut.cz.tamburinektor.model.test;

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
@Table(name = "TestResult")
@Getter
@Setter
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @Column(nullable = false)
    private User writtenBy;

    @Column(nullable = false)
    private int result;

    @Column(nullable = false)
    @OneToOne
    private Test test;
}
