package com.zyk.loadbalance;

import com.zyk.remoting.dto.RpcRequest;

import java.util.List;

public interface LoadBalance {

    /**
     * Choose one from the list of existing addresses list
     */
    String selectServiceAddress(List<String> serviceAddresses, RpcRequest rpcRequest);
}
