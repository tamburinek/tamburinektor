package fel.cvut.cz.tamburinektor.model.enums;

import lombok.Getter;

@Getter
public enum UserType {
    ROLE_STUDENT("student"),
    ROLE_ADMIN("admin"),
    ROLE_TEACHER("teacher");

    private final String name;

    UserType(String name) {
        this.name = name;
    }
}
