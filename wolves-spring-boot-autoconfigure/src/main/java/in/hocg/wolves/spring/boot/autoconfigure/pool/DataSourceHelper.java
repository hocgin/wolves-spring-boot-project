package in.hocg.wolves.spring.boot.autoconfigure.pool;

import com.google.common.collect.Maps;
import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import lombok.Getter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
public abstract class DataSourceHelper {
    private Environment environment;
    
    public DataSourceHelper(Environment environment) {
        this.environment = environment;
    }
    
    /**
     * 获取主数据源
     *
     * @param properties 配置
     * @return
     */
    public abstract DataSource getMasterDataSource(DataSourceProperties properties);
    
    /**
     * 获取从数据源
     *
     * @param properties 配置
     * @return
     */
    public abstract DataSource getSlaveDataSource(WolvesProperties.WolvesDataSourceProperties properties);
    
    /**
     * 获取多从数据源
     *
     * @param properties 配置
     * @return
     */
    public Map<Object, Object> getSlaveDataSources(List<WolvesProperties.WolvesDataSourceProperties> properties) {
        Map<Object, Object> dataSources = Maps.newHashMap();
        for (WolvesProperties.WolvesDataSourceProperties prop : properties) {
            dataSources.put(prop.getName(), getSlaveDataSource(prop));
        }
        return dataSources;
    }
    
    
}
