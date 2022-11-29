package com.zyk.registry.zk;

import com.zyk.registry.ServiceDiscovery;
import com.zyk.remoting.dto.RpcRequest;

import java.net.InetSocketAddress;

public class ZkServiceDiscoveryImpl implements ServiceDiscovery {
    @Override
    public InetSocketAddress lookupService(RpcRequest rpcRequest) {
        return null;
    }
}
