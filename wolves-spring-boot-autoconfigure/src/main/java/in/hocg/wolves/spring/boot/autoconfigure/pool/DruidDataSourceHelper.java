package in.hocg.wolves.spring.boot.autoconfigure.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DruidDataSourceHelper implements DataSourceHelper {
    
    @Override
    public DataSource getMasterDataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClassName());
        return dataSource;
    }
    
    @Override
    public DataSource getSlaveDataSource(WolvesProperties.WolvesDataSourceProperties properties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClassName());
        return dataSource;
    }
}
