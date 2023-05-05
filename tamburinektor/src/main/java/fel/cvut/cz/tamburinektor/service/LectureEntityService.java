package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LectureEntityService {

    private final LectureEntityRepository lectureEntityRepository;

    @Autowired
    public LectureEntityService(LectureEntityRepository lectureEntityRepository) {
        this.lectureEntityRepository = lectureEntityRepository;
    }


    public int getCountByUser(User user) {
        return lectureEntityRepository.getAllByCreatedBy(user).size();
    }

    public LectureEntity getLastMaterial(User user){
        List<LectureEntity> entities = lectureEntityRepository.getAllByCreatedBy(user);
        if (entities.isEmpty()){
            return null;
        }
        LectureEntity last = entities.get(0);
        for (LectureEntity entity : entities) {
            if (entity.getId() > last.getId()){
                last = entity;
            }
        }
        return last;
    }
}
