package fel.cvut.cz.tamburinektor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.test.Test;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "Classroom")
@Getter
@Setter
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String password;

    @Column(unique = true)
    private String name;

    @OneToOne
    private User createBy;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<Test> tests;

    @ManyToMany
    private List<Lecture> lectures;
}
