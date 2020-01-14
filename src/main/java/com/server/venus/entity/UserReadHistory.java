package com.server.venus.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 项目名称：venus-admin-server
 * 类名称：UserReadHistory
 * 类描述：用户浏览记录实体类
 * 创建人：yingx
 * 创建时间： 2020/1/14
 * 修改人：yingx
 * 修改时间： 2020/1/14
 * 修改备注：@Document:标示映射到Mongodb文档上的领域对象
 *
 * @Id:标示某个域为ID域
 * @Indexed:标示某个字段为Mongodb的索引字段（需要检索字段使用）
 */
@Document
public class UserReadHistory {

    @Id
    private String id;

    @Indexed
    private Long userId;

    @Indexed
    private String videoId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "UserReadHistory{" +
                "id='" + id + '\'' +
                ", userId=" + userId +
                ", videoId='" + videoId + '\'' +
                '}';
    }
}
