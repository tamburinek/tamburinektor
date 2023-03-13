package fel.cvut.cz.tamburinektor.dao;

import fel.cvut.cz.tamburinektor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByUsername(String username);

}

