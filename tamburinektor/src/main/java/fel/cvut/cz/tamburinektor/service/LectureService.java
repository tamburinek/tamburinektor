package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<Lecture> getAllLecturesByUser(User user){
        return lectureRepository.getAllByCreatedBy(user);
    }

    public Lecture getById(Long id){
        return lectureRepository.getLectureById(id);
    }


    public Lecture getLast(User user) {
        List<Lecture> entities = lectureRepository.getAllByCreatedBy(user);
        if (entities.isEmpty()){
            return null;
        }
        Lecture last = entities.get(0);
        for (Lecture entity : entities) {
            if (entity.getId() > last.getId()){
                last = entity;
            }
        }
        return last;
    }
}
