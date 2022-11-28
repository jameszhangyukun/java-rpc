package com.zyk.util;

public class RuntimeUtil {
    public static int cpus() {
        return Runtime.getRuntime().availableProcessors();
    }
}
