package fel.cvut.cz.tamburinektor.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ImageDto {

    private Long id;

    private String description;

    private String imageUrl;

    private String lectureType;
}
