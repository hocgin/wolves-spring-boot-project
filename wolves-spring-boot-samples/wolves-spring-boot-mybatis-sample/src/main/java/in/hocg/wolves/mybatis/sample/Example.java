package in.hocg.wolves.mybatis.sample;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2019/5/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ToString
@Data
public class Example {
    private Long id;
    private LocalDateTime createdAt;
}
