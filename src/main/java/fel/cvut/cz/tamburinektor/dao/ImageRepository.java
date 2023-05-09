package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> getAllByCreatedBy(User user);

    Image getById(Long id);
}
