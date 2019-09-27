package in.hocg.wolves.jpa.sample;

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
    private final ExampleRepository repository;
    
    @SetDataSource("n1")
    public void find1() {
        repository.findAll();
    }
    
    public void find2() {
        repository.findAll();
    }
}
