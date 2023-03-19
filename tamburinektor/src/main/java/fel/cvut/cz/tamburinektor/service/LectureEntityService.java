package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LectureEntityService {

    private final LectureEntityRepository lectureEntityRepository;

    @Autowired
    public LectureEntityService(LectureEntityRepository lectureEntityRepository) {
        this.lectureEntityRepository = lectureEntityRepository;
    }
}
