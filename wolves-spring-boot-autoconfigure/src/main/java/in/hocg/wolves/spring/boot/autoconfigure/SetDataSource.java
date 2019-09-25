package in.hocg.wolves.spring.boot.autoconfigure;

/**
 * @author hocgin
 * @date 2019/6/25
 */
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD})
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Documented
public @interface SetDataSource {
    String value() default Constant.MASTER;
}
