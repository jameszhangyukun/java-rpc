package com.zyk.registry;

import com.zyk.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

/**
 * service discovery
 */
public interface ServiceDiscovery {

    /**
     * lockup service by service name
     */
    InetSocketAddress lookupService(RpcRequest rpcRequest);
}
