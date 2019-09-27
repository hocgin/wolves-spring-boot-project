package in.hocg.wolves.mybatis.sample;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class WolvesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WolvesApplication.class, args);
    }
}
