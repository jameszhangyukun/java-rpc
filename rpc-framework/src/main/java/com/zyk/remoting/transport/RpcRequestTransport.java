package com.zyk.remoting.transport;


import com.zyk.remoting.dto.RpcRequest;

/**
 * send Rpc Request
 */
public interface RpcRequestTransport {
    /**
     * send rpc request to server and get result
     */
    Object sendRpcRequest(RpcRequest rpcRequest);
}
