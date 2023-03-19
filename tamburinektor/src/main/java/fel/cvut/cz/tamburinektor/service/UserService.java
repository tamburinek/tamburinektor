package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.UserRepository;
import fel.cvut.cz.tamburinektor.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String username){
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.getUserByUsername(username);
    }
}
