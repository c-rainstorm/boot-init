package me.rainstorm.boot;

import me.rainstorm.boot.service.UserService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author baochen1.zhang
 * @date 2019.08.24
 */
public class CacheServiceTest extends BaseController {
    @Resource
    private UserService userService;

    @Test
    public void test() {
        userService.get("rainstorm.me");
        userService.get("rainstorm.me");
    }
}
