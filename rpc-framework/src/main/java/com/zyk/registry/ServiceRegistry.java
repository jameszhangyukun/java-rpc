package com.zyk.registry;

import java.net.InetSocketAddress;

/**
 * service registration
 */
public interface ServiceRegistry {

    /**
     * register service
     */
    void registerService(String rpcServiceName, InetSocketAddress inetSocketAddress);
}
