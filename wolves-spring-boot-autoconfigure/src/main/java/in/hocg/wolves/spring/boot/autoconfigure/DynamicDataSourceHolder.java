package in.hocg.wolves.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据源绑定到当前线程
 *
 * @author hocgin
 * @date 2019/6/25
 */
@Slf4j
public class DynamicDataSourceHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDERS = new ThreadLocal<>();
    
    public static Set<Object> ALL_DATA_SOURCE = new HashSet<>();
    
    public static void setDataSource(String dataSource) {
        CONTEXT_HOLDERS.set(dataSource);
    }
    
    public static String getDataSource() {
        String name = CONTEXT_HOLDERS.get();
        log.debug("[Wolves] 当前使用的数据源名称为: {}", name);
        return name;
    }
    
    public static void clear() {
        CONTEXT_HOLDERS.remove();
    }
    
    public static boolean isExist(String dataSource) {
        return ALL_DATA_SOURCE.contains(dataSource);
    }
}
