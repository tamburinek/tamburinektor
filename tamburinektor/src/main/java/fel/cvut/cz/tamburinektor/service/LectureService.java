package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureRepository;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    public void createOrUpdateLecture(Lecture lecture){
        lectureRepository.save(lecture);
    }
}
