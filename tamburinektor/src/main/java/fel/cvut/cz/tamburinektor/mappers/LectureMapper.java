package fel.cvut.cz.tamburinektor.mappers;

import fel.cvut.cz.tamburinektor.DTO.LectureDto;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LectureMapper {

    public LectureDto toDto(Lecture lecture) {
        return null;
    }

    public Lecture toLecture(LectureDto lectureDto, User user, List<LectureEntity> entities) {
        Lecture lecture = new Lecture();
        lecture.setDescription(lectureDto.getDescription());
        lecture.setCreatedBy(user);
        lecture.setLectureEntities(entities);
        return lecture;
    }
}
