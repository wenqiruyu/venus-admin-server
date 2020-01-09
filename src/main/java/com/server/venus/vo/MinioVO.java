package com.server.venus.vo;

/**
 * 项目名称：venus-admin-server
 * 类名称：MinioVO
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2020/1/9
 * 修改人：yingx
 * 修改时间： 2020/1/9
 * 修改备注：
 */
public class MinioVO {

    /** 上传对象的文件名*/
    private String fileName;

    /** 上传对象存储的路径*/
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Override
    public String toString() {
        return "MinioVO{" +
                "fileName='" + fileName + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
