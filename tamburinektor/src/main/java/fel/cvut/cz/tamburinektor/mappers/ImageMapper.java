package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.springframework.stereotype.Component;


@Component
public class ImageMapper {

    public ImageDto toDto(Image image) {
        return null;
    }

    public Image toImage(ImageDto imageDto, User user) {
        Image image = new Image();
        image.setType(LectureType.IMAGE);
        image.setDescription(imageDto.getDescription());
        image.setImageLink(imageDto.getImageUrl());
        image.setCreatedBy(user);
        return image;
    }
}
