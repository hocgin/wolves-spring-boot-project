package in.hocg.wolves.sample;

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
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    
    @Test
    public void test() {
        Account entity = new Account();
        entity.setName("666" + System.currentTimeMillis());
        accountRepository.save(entity);
    }
    
    
    @Test
    public void testSlave() {
        Account entity = new Account();
        DynamicDataSourceHolder.setDataSource("n1");
        entity.setName("666" + System.currentTimeMillis());
        accountRepository.save(entity);
        DynamicDataSourceHolder.clear();
    }
    
    @Test
    public void testAnnotation() {
        accountService.useN1();
    }
}
