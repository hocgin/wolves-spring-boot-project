package in.hocg.wolves.sample;

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
public class AccountService {
    private final AccountRepository repository;
    
    @SetDataSource("n1")
    public void useN1() {
        repository.findAll();
    }
}
