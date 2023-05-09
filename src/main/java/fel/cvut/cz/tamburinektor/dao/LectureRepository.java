package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {

    List<Lecture> getAllByCreatedBy(User user);

    Lecture getLectureById(Long id);
}
