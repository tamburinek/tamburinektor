package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.mappers.ImageMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Image;
import fel.cvut.cz.tamburinektor.service.ImageService;
import fel.cvut.cz.tamburinektor.service.UserService;
import fel.cvut.cz.tamburinektor.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
@Slf4j
public class ImageController {

    private final ImageService imageService;

    private final ImageMapper imageMapper;

    private final UserService userService;

    @Autowired
    public ImageController(ImageService imageService, ImageMapper imageMapper, UserService userService) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
        this.userService = userService;
    }

    @CrossOrigin(origins = UserController.origin)
    @PostMapping(value = "/image", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createImage(@RequestBody ImageDto imageDto) {
        User user = userService.getCurrentUser();
        Image image = imageMapper.toImage(imageDto, user);
        imageService.createImage(image);
        log.info("New image created {} by {}", image.getDescription(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/image/{id}", image.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
