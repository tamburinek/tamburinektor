package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClassRoomRepository extends JpaRepository<Classroom, Integer> {

    Classroom getClassroomByName(String name);
}
