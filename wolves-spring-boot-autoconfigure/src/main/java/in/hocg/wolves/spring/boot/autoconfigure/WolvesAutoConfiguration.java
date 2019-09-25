package in.hocg.wolves.spring.boot.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
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
@EnableConfigurationProperties(WolvesProperties.class)
public class WolvesAutoConfiguration {
    private final WolvesProperties properties;
    
    @Bean
    @Primary
    public DataSource dataSource() {
        Map<Object, Object> dataSources = getDataSources(properties.getSlave());
        DataSource dataSource = getDataSource(properties.getMaster());
        dataSources.put(Constant.MASTER, dataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(dataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        DynamicDataSourceHolder.ALL_DATA_SOURCE = dataSources.keySet();
        return dynamicDataSource;
    }
    
    @Bean
    public DynamicDataSourceAspect aspect() {
        return new DynamicDataSourceAspect();
    }
    
    private Map<Object, Object> getDataSources(List<WolvesProperties.WolvesDataSourceProperties> dataSourceProperties) {
        Map<Object, Object> dataSources = new HashMap<>();
        for (WolvesProperties.WolvesDataSourceProperties properties : dataSourceProperties) {
            dataSources.put(properties.getFlag(), getDataSource(properties));
        }
        return dataSources;
    }
    
    private DataSource getDataSource(WolvesProperties.WolvesDataSourceProperties properties) {
        DataSource dataSource = DataSourceBuilder.create()
                .url(properties.getUrl())
                .driverClassName(properties.getDriverClassName())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
        return dataSource;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
}
