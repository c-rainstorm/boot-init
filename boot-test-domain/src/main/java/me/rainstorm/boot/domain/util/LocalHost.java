package me.rainstorm.boot.domain.util;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author baochen1.zhang
 * @date 2019.07.05
 */
public class LocalHost {
    private static volatile String MACHINE_NAME;
    private static volatile String MACHINE_IP;

    public static String getMachineName() {
        if (StringUtils.isBlank(MACHINE_NAME)) {
            ensureLocalHostInfo();
        }
        return MACHINE_NAME;
    }

    public static String getMachineIp() {
        if (StringUtils.isBlank(MACHINE_IP)) {
            ensureLocalHostInfo();
        }
        return MACHINE_IP;
    }

    private synchronized static void ensureLocalHostInfo() {
        if (StringUtils.isNotBlank(MACHINE_IP) &&
                StringUtils.isNotBlank(MACHINE_NAME)) {
            return;
        }

        try {
            InetAddress localhost = InetAddress.getLocalHost();
            MACHINE_NAME = localhost.getHostName();
            MACHINE_IP = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            MACHINE_NAME = "localhost";
            MACHINE_IP = "127.0.0.1";
        }
    }
}
