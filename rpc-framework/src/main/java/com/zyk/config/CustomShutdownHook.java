package com.zyk.config;

import com.zyk.registry.zk.util.CuratorUtils;
import com.zyk.remoting.transport.netty.server.NettyRpcServer;
import com.zyk.util.concurrent.threadpool.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * When the server  is closed, do something such as unregister all services
 */
@Slf4j
public class CustomShutdownHook {
    private static final CustomShutdownHook CUSTOM_SHUTDOWN_HOOK = new CustomShutdownHook();

    public static CustomShutdownHook getCustomShutdownConfig() {
        return CUSTOM_SHUTDOWN_HOOK;
    }

    public void clearAll() {
        log.info("addShutdownHook for clearAll");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                InetSocketAddress inetSocketAddress = new InetSocketAddress(
                        InetAddress.getLocalHost().getHostAddress(), NettyRpcServer.PORT
                );
                CuratorUtils.clearRegistry(CuratorUtils.getZkClient(), inetSocketAddress);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            ThreadPoolFactoryUtil.shutdownAllThreadPool();
        }));
    }
}
