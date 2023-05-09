package fel.cvut.cz.tamburinektor.model.enums;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum LectureType {
    DEFINITION("definition"),
    QUESTION("question"),
    IMAGE("image"),
    QUIZ("quiz"),
    TASK("task");

    private final String name;

    LectureType(String name) {
        this.name = name;
    }
}
