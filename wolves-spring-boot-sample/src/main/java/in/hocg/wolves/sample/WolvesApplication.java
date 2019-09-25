package in.hocg.wolves.sample;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
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
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class WolvesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WolvesApplication.class, args);
    }
}
