package com.server.venus.vo;

/**
 * 项目名称：venus-admin-server
 * 类名称：VenusChatVO
 * 类描述：在线聊天
 * 创建人：yingx
 * 创建时间： 2019/12/8
 * 修改人：yingx
 * 修改时间： 2019/12/8
 * 修改备注：
 */
public class VenusChatVO {

    /**
     * 聊天用户昵称
     */
    private String username;

    /**
     * 用户聊天内容
     */
    private String chatMessage;


    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "VenusChatVO{" +
                "username='" + username + '\'' +
                ", chatMessage='" + chatMessage + '\'' +
                '}';
    }
}
