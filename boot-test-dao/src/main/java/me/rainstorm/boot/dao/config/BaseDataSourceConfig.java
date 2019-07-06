package me.rainstorm.boot.dao.config;

import lombok.Data;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
@Data
public class BaseDataSourceConfig {
    protected String mapperLocation;
    protected String url;
    protected String username;
    protected String password;
    protected String driver;
}
