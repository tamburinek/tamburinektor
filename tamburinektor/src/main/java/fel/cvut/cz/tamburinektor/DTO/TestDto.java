package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TestDto {

    private Long id;

    private String description;

    private List<Long> assignments;
}
