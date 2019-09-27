package in.hocg.wolves.spring.boot.autoconfigure;

import in.hocg.wolves.spring.boot.autoconfigure.pool.DataSourceHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by hocgin on 2019-09-25.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = WolvesProperties.PREFIX, name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties({WolvesProperties.class, DataSourceProperties.class})
public class WolvesAutoConfiguration {
    private final WolvesProperties properties;
    private final DataSourceProperties dataSourceProperties;
    
    @Bean
    @Primary
    public DataSource dataSource() {
        Map<Object, Object> dataSources;
        DataSourceHelper dataSourceHelper = getDataSourceHelper();
        dataSources = dataSourceHelper.getSlaveDataSources(properties.getSlave());
        DataSource masterDataSource = dataSourceHelper.getMasterDataSource(dataSourceProperties);
        dataSources.put(Constant.MASTER, masterDataSource);
        
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSources);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        DynamicDataSourceHolder.ALL_DATA_SOURCE = dataSources.keySet();
        return dynamicDataSource;
    }
    
    private DataSourceHelper getDataSourceHelper() {
        try {
            return properties.getHelperClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw DynamicDataSourceException.wrap("请配置正确的" + DataSourceHelper.class.getName());
        }
    }
    
    @Bean
    public DynamicDataSourceAspect aspect() {
        return new DynamicDataSourceAspect();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
}