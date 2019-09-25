package in.hocg.wolves.spring.boot.autoconfigure;

/**
 * @author hocgin
 * @date 2019/6/25
 */
public class DynamicDataSourceException extends RuntimeException {
    
    public DynamicDataSourceException(String message) {
        super(message);
    }
    
    public static DynamicDataSourceException wrap(String message) {
        return new DynamicDataSourceException(message);
    }
}
