package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.ClassRoomRepository;
import fel.cvut.cz.tamburinektor.model.Classroom;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Lecture;
import fel.cvut.cz.tamburinektor.model.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;

    @Autowired
    public ClassRoomService(ClassRoomRepository classRoomRepository) {
        this.classRoomRepository = classRoomRepository;
    }


    public void createOrUpdateClass(Classroom classroom) {
        classRoomRepository.save(classroom);
    }

    public int getCountByUser(User user){
        return classRoomRepository.getAllByCreateBy(user).size();
    }

    public List<Classroom> getClassesCreatedBy(User user){
        return classRoomRepository.getAllByCreateBy(user);
    }


    public Classroom getLastClass(User user) {
        List<Classroom> entities = classRoomRepository.getAllByCreateBy(user);
        if (entities.isEmpty()){
            return null;
        }
        Classroom last = entities.get(0);
        for (Classroom entity : entities) {
            if (entity.getId() > last.getId()){
                last = entity;
            }
        }
        return last;
    }


    public Classroom getClassById(Long id) {
        return classRoomRepository.getById(id);
    }


    public void addLectureToClass(Classroom classroom, Lecture lecture) {
        List<Lecture> lectures = classroom.getLectures();
        lectures.add(lecture);
        classroom.setLectures(lectures);
        classRoomRepository.save(classroom);
    }


    public void removeLectureFromClass(Classroom classroom, Lecture lecture) {
        List<Lecture> lectures = classroom.getLectures().stream()
                .filter(lecture1 -> !Objects.equals(lecture1.getId(), lecture.getId())).collect(Collectors.toList());
        classroom.setLectures(lectures);
        classRoomRepository.save(classroom);
    }


    public void addTestToClass(Classroom classroom, Test test) {
        List<Test> tests = classroom.getTests();
        tests.add(test);
        classroom.setTests(tests);
        classRoomRepository.save(classroom);
    }


    public void removeTestFromClass(Classroom classroom, Test test) {
        List<Test> tests = classroom.getTests().stream()
                .filter(test1 -> !Objects.equals(test1.getId(), test.getId())).collect(Collectors.toList());
        classroom.setTests(tests);
        classRoomRepository.save(classroom);
    }


    public List<Classroom> getAllClasses() {
        return classRoomRepository.findAll();
    }


    public List<Classroom> getAllClassesMy(User user) {
        return classRoomRepository.getAllByUsersContaining(user);
    }


    public boolean putUserToClass(Classroom classroom, User user, String password) {
        if (Objects.equals(classroom.getPassword(), "") || classroom.getPassword().equals(password)){
            List<User> users = classroom.getUsers();
            users.add(user);
            classroom.setUsers(users);
            classRoomRepository.save(classroom);
            return true;
        }
        return false;
    }
}
