package fel.cvut.cz.tamburinektor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("fel.cvut.cz.tamburinektor")
public class TamburinektorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TamburinektorApplication.class, args);
    }
}
