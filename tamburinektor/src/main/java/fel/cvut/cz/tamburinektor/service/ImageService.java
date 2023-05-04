package fel.cvut.cz.tamburinektor.service;

import fel.cvut.cz.tamburinektor.dao.ImageRepository;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
}
