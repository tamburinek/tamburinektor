package fel.cvut.cz.tamburinektor.model.lecture;

import fel.cvut.cz.tamburinektor.model.enums.GraphType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "Graph")
@Getter
@Setter
@NoArgsConstructor
public class Graph extends LectureEntity{

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GraphType graphType;
}
