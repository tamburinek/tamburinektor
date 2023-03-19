package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
