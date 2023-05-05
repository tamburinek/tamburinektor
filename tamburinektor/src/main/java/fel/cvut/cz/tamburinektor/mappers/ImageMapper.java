package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.springframework.stereotype.Component;


@Component
public class ImageMapper {

    public ImageDto toDto(Image image) {
        ImageDto imageDto = new ImageDto();
        imageDto.setImageUrl(image.getImageLink());
        imageDto.setDescription(image.getDescription());
        imageDto.setId(image.getId());
        imageDto.setLectureType(image.getType().getName());
        return imageDto;
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
