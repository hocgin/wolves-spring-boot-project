package in.hocg.wolves.spring.boot.autoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author hocgin
 * @date 2019/6/25
 */
@Slf4j
@Aspect
@Order(Integer.MIN_VALUE)
public class DynamicDataSourceAspect {
    
    @Pointcut("@annotation(in.hocg.wolves.spring.boot.autoconfigure.UseDataSource)")
    public void aspect() {
    }
    
    @Around("aspect()")
    public Object doAspect(ProceedingJoinPoint point) throws Throwable {
        Object target = point.getTarget();
        String methodName = point.getSignature().getName();
        Class[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod()
                .getParameterTypes();
        Method method = target.getClass().getMethod(methodName, parameterTypes);
        UseDataSource annotation = method.getAnnotation(UseDataSource.class);
        String dataSourceName = annotation.name();
        
        boolean exist = DynamicDataSourceHolder.isExist(dataSourceName);
        if (!exist) {
            throw DynamicDataSourceException.wrap("设定的动态数据源: " + dataSourceName + " 不存在");
        }
        DynamicDataSourceHolder.setDataSource(dataSourceName);
        Object ret;
        try {
            ret = point.proceed();
        } finally {
            DynamicDataSourceHolder.clear();
        }
        return ret;
    }
}
