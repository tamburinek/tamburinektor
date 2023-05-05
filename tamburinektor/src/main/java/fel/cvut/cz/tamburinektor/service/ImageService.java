package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.ImageRepository;
import fel.cvut.cz.tamburinektor.dao.LectureEntityRepository;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.enums.LectureType;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import fel.cvut.cz.tamburinektor.model.lecture.LectureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void createImage(Image image){
        imageRepository.save(image);
    }

    public List<Image> getAllByUser(User user){
        return imageRepository.getAllByCreatedBy(user);
    }
}
