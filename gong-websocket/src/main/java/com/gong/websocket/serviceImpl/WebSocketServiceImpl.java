package com.gong.websocket.serviceImpl;

import com.gong.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * TODO WebSocket实现类
 *
 * @author xiaogong
 * @since 2023/9/4 17:11
 */
@Component
@Slf4j
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServiceImpl implements WebSocketService {

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * 用户ID
     */
    private String userId;

    // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    // 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    //  注：底下WebSocket是当前类名
    private static final CopyOnWriteArraySet<WebSocketServiceImpl> webSockets = new CopyOnWriteArraySet<>();
    // 用来存在线连接用户信息
    private static final ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<String, Session>();

    /**
     * 链接成功调用的方法
     */
    @OnOpen
    @Override
    public void onOpen(Session session, String userId) {
        this.session = session;
        this.userId = userId;
        webSockets.add(this);
        sessionPool.put(userId, session);
        log.info("【websocket消息】有新的连接，总数为:" + webSockets.size());
    }

    @OnClose
    @Override
    public void onClose() {
        webSockets.remove(this);
        sessionPool.remove(this.userId);
        log.info("【websocket消息】连接断开，总数为:" + webSockets.size());
    }

    @OnMessage
    @Override
    public void onMessage(String message) {
        webSockets.remove(this);
        sessionPool.remove(this.userId);
        log.info("【websocket消息】连接断开，总数为:" + webSockets.size());
    }

    @OnError
    @Override
    public void onError(Session session, Throwable error) {
        log.error("用户错误,原因:" + error.getMessage());
        error.printStackTrace();
    }

    @Override
    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:" + message);
        for (WebSocketServiceImpl webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendOneMessage(String userId, String message) {
        for (WebSocketServiceImpl webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sendMoreMessage(String[] userIds, String message) {
        for (WebSocketServiceImpl webSocket : webSockets) {
            try {
                if (webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
