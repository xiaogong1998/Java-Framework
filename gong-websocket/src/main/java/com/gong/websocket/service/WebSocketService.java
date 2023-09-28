package com.gong.websocket.service;

import javax.websocket.Session;
import javax.websocket.server.PathParam;

/**
 * TODO WebSocket服务
 *
 * @author xiaogong
 * @since 2023/9/4 17:05
 */

public interface WebSocketService {

    /**
     * 链接关闭调用的方法
     * @param session
     * @param userId
     */
    void onOpen(Session session, @PathParam(value="userId")String userId);

    /**
     * 链接关闭调用的方法
     */
    void onClose();

    /**
     * 收到客户端消息后调用的方法
     * @param message
     */
    void onMessage(String message);

    /**
     * 发送错误时的处理
     * @param session
     * @param error
     */
    void onError(Session session, Throwable error);

    /**
     * 此为单点消息
     * @param message
     */
    void sendAllMessage(String message);

    /**
     * 此为单点消息
     * @param userId
     * @param message
     */
    void sendOneMessage(String userId, String message);

    /**
     * 此为单点消息(多人)
     * @param userIds
     * @param message
     */
    void sendMoreMessage(String[] userIds, String message);
}
