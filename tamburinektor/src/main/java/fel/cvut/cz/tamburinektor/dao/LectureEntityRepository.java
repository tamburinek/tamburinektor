package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LectureEntityRepository extends JpaRepository<LectureEntity, Integer> {

    List<LectureEntity> getAllByCreatedBy(User user);
}
