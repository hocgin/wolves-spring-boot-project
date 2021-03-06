package in.hocg.wolves.spring.boot.autoconfigure;

import com.google.common.collect.Maps;
import in.hocg.wolves.spring.boot.autoconfigure.pool.AutoDataSourceHelper;
import in.hocg.wolves.spring.boot.autoconfigure.pool.DataSourceHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@ConfigurationProperties(prefix = WolvesProperties.PREFIX)
public class WolvesProperties {
    public static final String PREFIX = "spring.wolves";
    
    private Class<? extends DataSourceHelper> helperClass = AutoDataSourceHelper.class;
    
    private List<WolvesDataSourceProperties> slave = new ArrayList<>();
    
    @Data
    @Accessors(chain = true)
    @ToString
    @NoArgsConstructor
    public static class WolvesDataSourceProperties {
        private String name;
        private String url;
        private String driverClassName;
        private String username;
        private String password;
        private Map<String, String> settings = Maps.newHashMap();
    }
}
