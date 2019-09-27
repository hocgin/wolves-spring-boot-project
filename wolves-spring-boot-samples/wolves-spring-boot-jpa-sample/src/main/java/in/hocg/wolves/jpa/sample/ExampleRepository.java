package in.hocg.wolves.jpa.sample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by hocgin on 2019-09-21.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
}
