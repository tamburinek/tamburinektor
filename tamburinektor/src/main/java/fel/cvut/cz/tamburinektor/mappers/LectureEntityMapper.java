package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.LectureEntityDto;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.stereotype.Component;


@Component
public class LectureEntityMapper {

    public LectureEntityDto toDto(LectureEntity lectureEntity) {
        LectureEntityDto dto = new LectureEntityDto();
        dto.setId(lectureEntity.getId());
        dto.setLectureType(lectureEntity.getType().getName());
        return dto;
    }

    public LectureEntity toLectureEntity(LectureEntityDto lectureEntityDto) {
        return null;
    }
}
