## Wolves
> 开箱即用的多数据源方案

## 配置说明
```yaml
spring:
  # 主库配置
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://mysql.localhost:3306/db_test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
    username: root
    password: hocgin
    schema:
      - classpath:db/schema-maria.sql
    data:
      - classpath:db/data-maria.sql
    initialization-mode: ALWAYS
  wolves:
    # 从库配置
    slave:
      - name: n1
        url: jdbc:mariadb://mysql.localhost:3306/db_test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
        driver-class-name: org.mariadb.jdbc.Driver
        username: root
        password: hocgin
      - name: n2
        url: jdbc:mariadb://mysql.localhost:3306/db_test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true
        driver-class-name: org.mariadb.jdbc.Driver
        username: root
        password: hocgin
    # 可用于扩展不同的数据源装置。例如: DruidDataSourceHelper
    helper-class: in.hocg.wolves.spring.boot.autoconfigure.pool.AutoDataSourceHelper
```

## 使用说明
> 提供 JPA 和 MyBatis 的 Sample

1. 注解方式，参照 Sample 的测试用例。
2. 函数方式，参照 Sample 的测试用例。

## 需知
1. 使用 Druid 作为数据源。
请使用以下方式排除掉`DruidDataSourceAutoConfigure`.
```yaml
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class WolvesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WolvesApplication.class, args);
    }
}
```

## 更新
- [x] 默认为主
- 兼容多种数据源
    - 目前仅适配 Druid 和 Spring Boot 默认支持，其他的请自行实现适配。
- [x] MyBatis 案例
- [x] JPA 案例