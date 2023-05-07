package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClassRoomRepository extends JpaRepository<Classroom, Integer> {

    Classroom getClassroomByName(String name);

    List<Classroom> getAllByCreateBy(User user);

    Classroom getById(Long id);
}
