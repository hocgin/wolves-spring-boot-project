package in.hocg.wolves.jpa.sample;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2019/5/25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@ToString
@Data
@Entity(name = "t_example")
@EntityListeners(AuditingEntityListener.class)
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @CreatedDate
    private LocalDateTime createdAt;
}
