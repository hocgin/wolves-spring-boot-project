package in.hocg.wolves.spring.boot.autoconfigure.pool;

import com.google.common.collect.Maps;
import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface DataSourceHelper {
    
    DataSource getMasterDataSource(DataSourceProperties properties);
    
    DataSource getSlaveDataSource(WolvesProperties.WolvesDataSourceProperties properties);
    
    default Map<Object, Object> getSlaveDataSources(List<WolvesProperties.WolvesDataSourceProperties> properties) {
        Map<Object, Object> dataSources = Maps.newHashMap();
        for (WolvesProperties.WolvesDataSourceProperties prop : properties) {
            dataSources.put(prop.getFlag(), getSlaveDataSource(prop));
        }
        return dataSources;
    }
    
    
}
