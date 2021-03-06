package com.server.venus.interceptor;

import com.alibaba.fastjson.JSON;
import com.server.venus.vo.ResultVO;
import com.server.venus.vo.VenusChatVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 项目名称：gobang-game-server
 * 类名称：ChatWebSocketHandler
 * 类描述：Socket处理器
 * 创建人：yingx
 * 创建时间： 2019/12/5
 * 修改人：yingx
 * 修改时间： 2019/12/5
 * 修改备注：
 */
@Component
@ServerEndpoint(value = "/chat/{username}")
public class ChatWebSocketHandler {

    private static int COUNT = 0;

    private static CopyOnWriteArraySet<ChatWebSocketHandler> websocket = new CopyOnWriteArraySet<>();

    private Session session;

    /**
     * 后续有想到需求的话可以用json字段传
     */
    private String username;

    /**
     * 建立socket连接时调用
     *
     * @param session
     * @return void
     * @author yingx
     * @date 2019/12/6
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {

        this.session = session;
        this.username = username;
        websocket.add(this);
        addOnlineCount();
        sendAllMessage(username + "进入了聊天室哦", "venus");
        System.out.println("当前在线人数：" + COUNT);
    }

    /**
     * 用户关闭socket连接时调用
     *
     * @param
     * @return void
     * @author yingx
     * @date 2019/12/6
     */
    @OnClose
    public void onClose(@PathParam("username") String username) {

        //sendAllMessage(username + "退出了聊天室哦", username);
        websocket.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 接收客户端的信息
     *
     * @param message
     * @param session
     * @return void
     * @author yingx
     * @date 2019/12/6
     */
    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {

        System.out.println("server 收到的信息是：" + message);
        sendAllMessage(message, username);
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     * @return void
     * @author yingx
     * @date 2019/12/6
     */
    @OnError
    public void onError(Session session, Throwable error) {

        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 单人聊天
     *
     * @param message
     * @return void
     * @author yingx
     * @date 2019/12/6
     */
    public void sendMessage(String message, String username) {

        try {
            VenusChatVO venusChatVO = new VenusChatVO();
            if (StringUtils.isNotBlank(message) && StringUtils.isNotBlank(username)) {
                venusChatVO.setUsername(username);
                venusChatVO.setChatMessage(message);
            } else {
                venusChatVO.setUsername("venus");
                venusChatVO.setChatMessage("服务器出现错误，请稍后再试。。。^-^");
            }
            this.session.getBasicRemote().sendText(JSON.toJSONString(new ResultVO(venusChatVO)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 群发自定义信息
     *
     * @param message
     * @return void
     * @author yingx
     * @date 2019/12/6
     */
    public static void sendAllMessage(String message, @PathParam("username") String username) {

        for (ChatWebSocketHandler item : websocket) {
            item.sendMessage(message, username);
        }
    }

    public static synchronized int getOnlineCount() {
        return COUNT;
    }

    public static synchronized void addOnlineCount() {
        ChatWebSocketHandler.COUNT++;
    }

    public static synchronized void subOnlineCount() {
        ChatWebSocketHandler.COUNT--;
    }
}
