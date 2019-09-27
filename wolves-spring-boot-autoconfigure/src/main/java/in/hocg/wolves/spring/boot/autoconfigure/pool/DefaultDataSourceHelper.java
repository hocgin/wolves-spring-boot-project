package in.hocg.wolves.spring.boot.autoconfigure.pool;

import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 * 默认数据源
 *
 * @author hocgin
 */
public class DefaultDataSourceHelper implements DataSourceHelper {
    
    @Override
    public DataSource getMasterDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
    
    @Override
    public DataSource getSlaveDataSource(WolvesProperties.WolvesDataSourceProperties properties) {
        return DataSourceBuilder.create()
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }
}
