package fel.cvut.cz.tamburinektor.rest;

import fel.cvut.cz.tamburinektor.DTO.CredentialsDto;
import fel.cvut.cz.tamburinektor.DTO.UserDto;
import fel.cvut.cz.tamburinektor.mappers.UserMapper;
import fel.cvut.cz.tamburinektor.model.User;
import fel.cvut.cz.tamburinektor.response.JwtResponse;
import fel.cvut.cz.tamburinektor.security.jwt.JwtUtils;
import fel.cvut.cz.tamburinektor.security.services.UserDetailsImpl;
import fel.cvut.cz.tamburinektor.service.UserService;
import fel.cvut.cz.tamburinektor.util.RestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController {

    private final UserMapper userMapper;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public UserController(UserMapper userMapper, UserService userService, AuthenticationManager authenticationManager, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users/me")
    public User getCurrentlyLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        return userService.getUserByUsername(userDetails.getUsername());
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDTO) {
        User user = userMapper.toUser(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userService.createUser(user);
        log.info("New user created {}", user);
        final HttpHeaders headers = RestUtil.createLocationHeaderNewUri("/users/{username}", user.getUsername());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users/signin")
    public ResponseEntity<?> authenticate(@RequestBody CredentialsDto credentialsDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentialsDTO.getUsername(), credentialsDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().get();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                role)
        );
    }
}
