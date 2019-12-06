package com.server.venus.interceptor;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 项目名称：venus-admin-server
 * 类名称：GobangWebSocketHandler
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/6
 * 修改人：yingx
 * 修改时间： 2019/12/6
 * 修改备注：
 */
@Component
@ServerEndpoint(value = "/gobang/{username}")
public class GobangWebSocketHandler {

    private static final Set<GobangWebSocketHandler> socket = new CopyOnWriteArraySet<>();

    private Session session;

    private String username;

    private static String lastUser;

    private static int count = 0;

    private int[][] oriData = new int[15][25];

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {

        this.session = session;
        this.username = username;
        if (socket.size() > 1) {
            System.out.println("房间人满");
            session.getBasicRemote().sendText("房间人已满!");
        } else {
            socket.add(this);
            StringBuffer sb = new StringBuffer();
            for (GobangWebSocketHandler gobangWebSocketHandler : socket) {
                sb.append(gobangWebSocketHandler.username).append(";");
            }
            for (int i = 0; i < oriData.length; i++) {
                for (int j = 0; j < oriData[i].length; j++) {
                    oriData[i][j] = -1;
                }
            }
            sendText(username + "玩家进入房间;当前房间有:" + sb.toString());
        }
    }

    @OnMessage
    public void onMessage(String message, @PathParam("username") String username) {

        if (username == lastUser) {
            return;
        }
        int i = count++ % 2;
        String[] split = message.split("-");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        oriData[y][x] = i;
        int success = isSuccess(x, y, i, oriData);
        sendText(message + "-" + i);
        if (success == -1) {
            lastUser = username;
        } else {
            if (i == 0) {
                sendText("alert('白棋获胜,点击确定重新开始游戏!');location.reload(true);");
                return;
            } else if (i == 1) {
                sendText("alert('黑棋获胜,点击确定重新开始游戏!');location.reload(true);");
                return;
            }
        }
    }

    private static int isSuccess(int x, int y, int f, int[][] oriData) {

        //y的范围在0-14之间，x的范围在0-24之间
        int count = 0;
        for (int i = x - 1; i > -1; i--) {
            if (oriData[y][i] != f) {
                break;
            }
            count++;
        }
        for (int i = x + 1; i < 25; i++) {
            if (oriData[y][i] != f) {
                break;
            }
            count++;
        }
        if (count > 3) {
            return f;
        }
        count = 0;
        for (int i = y + 1; i < 15; i++) {
            if (oriData[i][x] != f) {
                break;
            }
            count++;
        }
        for (int i = y - 1; i > -1; i--) {
            if (oriData[i][x] != f) {
                break;
            }
            count++;
        }
        if (count > 3) {
            return f;
        }
        count = 0;
        for (int i = x + 1, j = y + 1; i < 25; i++, j++) {
            if (j < 15) {
                if (oriData[j][i] != f) {
                    break;
                }
                count++;
            } else
                break;
        }
        for (int i = x - 1, j = y - 1; i > -1; i--, j--) {
            if (j > -1) {
                if (oriData[j][i] != f) {
                    break;
                }
                count++;
            } else
                break;
        }
        if (count > 3) {
            return f;
        }
        count = 0;
        for (int i = x + 1, j = y - 1; i < 25; i++, j--) {
            if (j > -1) {
                if (oriData[j][i] != f) {
                    break;
                }
                count++;
            } else
                break;
        }
        for (int i = x - 1, j = y + 1; i > -1; i--, j++) {
            if (j < 25) {
                if (oriData[j][i] != f) {
                    break;
                }
                count++;
            } else
                break;
        }
        if (count > 3) {
            return f;
        }
        return -1;
    }

    private static void sendText(String msg) {

        for (GobangWebSocketHandler socketHandler : socket) {
            try {
                synchronized (socketHandler) {
                    socketHandler.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                socket.remove(socketHandler);
                try {
                    socketHandler.session.close();
                } catch (IOException e1) {
                }
                sendText(socketHandler.username + "玩家已下线");
            }
        }
    }

    @OnClose
    public void onClose(Session session) {

        socket.remove(this);
        for (GobangWebSocketHandler goBangGame : socket) {
            if (goBangGame.username == this.username) {
                sendText(this.username + "玩家已下线");
            }
        }
    }
}
