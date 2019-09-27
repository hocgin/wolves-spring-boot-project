package in.hocg.wolves.spring.boot.autoconfigure.pool;

import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 * 自动配置数据源
 * - 支持 Spring Boot 默认
 * - 支持 Druid
 *
 * @author hocgin
 */
public class AutoDataSourceHelper extends DataSourceHelper {
    
    private static final String DRUID_DATA_SOURCE = "com.alibaba.druid.pool.DruidDataSource";
    private DataSourceHelper dataSourceHelper;
    
    public AutoDataSourceHelper(Environment environment) {
        super(environment);
    }
    
    private DataSourceHelper getDataSourceHelper() {
        if (Objects.nonNull(dataSourceHelper)) {
            return dataSourceHelper;
        }
        
        try {
            Class.forName(AutoDataSourceHelper.DRUID_DATA_SOURCE);
            dataSourceHelper = new DruidDataSourceHelper(getEnvironment());
        } catch (ClassNotFoundException e) {
            dataSourceHelper = new DefaultDataSourceHelper(getEnvironment());
        }
        return dataSourceHelper;
    }
    
    @Override
    public DataSource getMasterDataSource(DataSourceProperties properties) {
        return getDataSourceHelper().getMasterDataSource(properties);
    }
    
    @Override
    public DataSource getSlaveDataSource(WolvesProperties.WolvesDataSourceProperties properties) {
        return getDataSourceHelper().getSlaveDataSource(properties);
    }
    
    @Override
    public Map<Object, Object> getSlaveDataSources(List<WolvesProperties.WolvesDataSourceProperties> properties) {
        return getDataSourceHelper().getSlaveDataSources(properties);
    }
}
