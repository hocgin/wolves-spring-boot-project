package in.hocg.wolves.mybatis.sample;

import in.hocg.wolves.spring.boot.autoconfigure.SetDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Service
@RequiredArgsConstructor
public class ExampleService {
    private final ExampleMapper mapper;
    
    @SetDataSource("n1")
    public void find1() {
        mapper.findFirst();
    }
    
    public void find2() {
        mapper.findFirst();
    }
    
}
