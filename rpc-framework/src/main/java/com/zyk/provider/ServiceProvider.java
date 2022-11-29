package com.zyk.provider;

import com.zyk.config.RpcServiceConfig;

/**
 * store and provide service object.
 */
public interface ServiceProvider {
    void addService(RpcServiceConfig rpcServiceConfig);
    Object getService(String rpcServiceName);
    void publishService(RpcServiceConfig rpcServiceConfig);
}
