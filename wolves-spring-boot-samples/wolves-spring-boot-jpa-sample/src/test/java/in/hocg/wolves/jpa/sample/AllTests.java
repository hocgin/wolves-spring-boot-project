package in.hocg.wolves.jpa.sample;

import in.hocg.wolves.spring.boot.autoconfigure.DynamicDataSourceHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@SpringBootTest(
        classes = WolvesApplication.class
)
@RunWith(SpringJUnit4ClassRunner.class)
public class AllTests {
    @Autowired
    private ExampleService exampleService;
    
    @Test
    public void test() {
        exampleService.find2();
    }
    
    
    @Test
    public void testSlave() {
        DynamicDataSourceHolder.setDataSource("n1");
        exampleService.find2();
        DynamicDataSourceHolder.clear();
    }
    
    @Test
    public void testAnnotation() {
        exampleService.find1();
    }
}
