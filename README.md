# Boot-init

Spring boot + Mybatis

This project provides reusable initial project structure, dependencies, and basic tools.

Provided features:

- Maven multi-module support
- Unified `Response` structure && unified response code definition
- The project uses Java8 jsr310 format time uniformly, supports automatic convert input and response time fields, and automatic convert database type.
- `DateTimeFormatterUtil`
- `JsonUtil`
- `LogUtil`
- `LocalHost` is used to get the local IP and machine name
- Mybatis-generator support
  - Custom Dao name plugin to modify the suffix of the Dao class
  - Custom Lombok plugin for generating models using Lombok instead of getter and setter
- Multiple data source support
- AOP support (mainly used to output log & bind exception handling)
  - Controller AOP output request input, response, and binding exception handling
  - Dao AOP access time exceeds threshold log
- Custom Validate annotation
- Custom property support IDE smart tips and jumps
- Unite test based on Spring MVC Test Framework

## Quick Start

### Configuring the data source

In the web module configuration data source, add the `application-datasource.properties` file to the `resources` directory and save the following configuration.

```properties
# 支持多数据源配置
#
# boot 默认数据源

## 默认数据源的 mapper 位置
me.rainstorm.boot.datasource.mapper-location=classpath:/mybatis/mapper/boot/*.xml
me.rainstorm.boot.datasource.url=jdbc:mysql://xxxx:3306/boot
me.rainstorm.boot.datasource.driver=com.mysql.jdbc.Driver
me.rainstorm.boot.datasource.username=xxx
me.rainstorm.boot.datasource.password=xxx
```

- Multiple data source configuration See Wiki - Multi-Data Source Configuration.
- If you need the SQL, See Wiki - Database Initialization SQL

### Mybatis generator support

- **please install `mybatis-generator-plugin` module first**

Add the `generatorConfig.properties` file to the dao module `test/resources` directory and save the following configuration

```properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://xxxx:3306/boot?characterEncoding=utf-8
jdbc.username=xxxx
jdbc.password=xxxx

# 需要自动生成代码的表名
table.name=user
```

- Mybatis generator configuration see Wiki - Custom Mybatis generator

## TODO

- [ ] Redis distributed lock
  - [ ] Single node
  - [ ] Cluster
  - [ ] Sharding
  - [ ] Sentinel
- [ ] Distributed configuration center
  - [ ] Apollo
  - [ ] Disconf
  - [ ] xxl-conf

## Written at the end

Please refer to the wiki for detailed documentation.

Hope it will be helpful. Have a nice day!
