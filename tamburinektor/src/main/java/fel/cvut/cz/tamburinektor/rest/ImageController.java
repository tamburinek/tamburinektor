package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.DefinitionDto;
import fel.cvut.cz.tamburinektor.DTO.ImageDto;
import fel.cvut.cz.tamburinektor.mappers.ImageMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.model.lecture.Definition;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping
@Slf4j
@CrossOrigin
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


    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PostMapping(value = "/image", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createImage(@RequestBody ImageDto imageDto) {
        User user = userService.getCurrentUser();
        Image image = imageMapper.toImage(imageDto, user);
        imageService.createImage(image);
        log.info("New image created {} by {}", image.getDescription(), user.getUsername());
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/image/{id}", image.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @GetMapping(value = "/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ImageDto> getAllImagesByUser(){
        User user = userService.getCurrentUser();
        List<Image> images = imageService.getAllByUser(user);
        return images.stream().map(imageMapper::toDto).collect(Collectors.toList());
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageDto getImageById(@PathVariable Long id){
        Image image = imageService.getById(id);
        return imageMapper.toDto(image);
    }

    @PreAuthorize("hasRole('ROLE_TEACHER')")
    @PatchMapping(value = "/image/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestBody ImageDto dto){
        Image image = imageService.getById(id);
        image.setImageLink(dto.getImageUrl());
        image.setDescription(dto.getDescription());
        imageService.createImage(image);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
