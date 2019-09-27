package in.hocg.wolves.jpa.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@EnableJpaAuditing
@SpringBootApplication
public class WolvesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WolvesApplication.class, args);
    }
}
