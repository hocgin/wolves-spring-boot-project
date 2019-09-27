package in.hocg.wolves.mybatis.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RequiredArgsConstructor
@RestController
@SpringBootApplication
//@SpringBootApplication(exclude = WolvesAutoConfiguration.class)
public class WolvesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WolvesApplication.class, args);
    }
    
    private final ExampleService exampleService;
    
    @GetMapping("worked")
    public ResponseEntity<String> worked() {
        boolean b = new Random().nextInt() % 2 == 0;
        if (b) {
            exampleService.find1();
        } else {
            exampleService.find2();
        }
        return ResponseEntity.ok(b ? "find1" : "find2");
    }
}
