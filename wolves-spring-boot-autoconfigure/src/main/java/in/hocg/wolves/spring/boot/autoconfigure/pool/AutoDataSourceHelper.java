package in.hocg.wolves.spring.boot.autoconfigure.pool;

import in.hocg.wolves.spring.boot.autoconfigure.WolvesProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * Created by hocgin on 2019-09-27.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class AutoDataSourceHelper implements DataSourceHelper {
    
    private static final String DRUID_DATA_SOURCE = "com.alibaba.druid.pool.DruidDataSource";
    private DataSourceHelper dataSourceHelper;
    
    private DataSourceHelper getDataSourceHelper() {
        if (Objects.nonNull(dataSourceHelper)) {
            return dataSourceHelper;
        }
        
        try {
            Class.forName(AutoDataSourceHelper.DRUID_DATA_SOURCE);
            dataSourceHelper = new DruidDataSourceHelper();
        } catch (ClassNotFoundException e) {
            dataSourceHelper = new DefaultDataSourceHelper();
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
}
