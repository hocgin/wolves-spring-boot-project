package in.hocg.wolves.spring.boot.autoconfigure.pool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 * 支持 Druid
 *
 * @author hocgin
 */
public class DruidDataSourceHelper extends DataSourceHelper {
    
    public DruidDataSourceHelper(Environment environment) {
        super(environment);
    }
    
    @Override
    public DataSource getMasterDataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = Binder.get(getEnvironment()).bind("spring.datasource.druid", DruidDataSource.class)
                .orElse(DruidDataSourceBuilder.create().build());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(properties.getDriverClassName());
    
        return dataSource;
    }
    
    @Override
    public DataSource getSlaveDataSource(WolvesProperties.WolvesDataSourceProperties properties) {
        throw new UnsupportedOperationException("不该被调用");
    }
    
    @Override
    public Map<Object, Object> getSlaveDataSources(List<WolvesProperties.WolvesDataSourceProperties> properties) {
        Map<Object, Object> dataSources = Maps.newHashMap();
        List<DruidDataSource> druidDataSources = Binder.get(getEnvironment()).bind("spring.wolves.slave", Bindable.listOf(DruidDataSource.class))
                .orElse(Lists.newArrayList());
        for (int i = 0; i < properties.size(); i++) {
            WolvesProperties.WolvesDataSourceProperties prop = properties.get(i);
            DruidDataSource dataSource = druidDataSources.get(i);
            dataSource.setUrl(prop.getUrl());
            dataSource.setUsername(prop.getUsername());
            dataSource.setPassword(prop.getPassword());
            dataSource.setDriverClassName(prop.getDriverClassName());
            dataSources.put(prop.getName(), dataSource);
        }
        return dataSources;
    }
}
