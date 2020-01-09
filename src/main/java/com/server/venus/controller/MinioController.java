package com.server.venus.controller;

import com.google.api.client.util.IOUtils;
import com.server.venus.vo.MinioVO;
import com.server.venus.vo.ResultVO;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.messages.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：venus-admin-server
 * 类名称：MinioController
 * 类描述：对象存储的控制器类
 * 创建人：yingx
 * 创建时间： 2020/1/9
 * 修改人：yingx
 * 修改时间： 2020/1/9
 * 修改备注：
 */
@RestController
@RequestMapping("/minio")
public class MinioController {

    private static final Logger logger = LoggerFactory.getLogger(MinioController.class);

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucketName}")
    private String bucketName;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @PostMapping(value = "/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file) {

        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
            logger.info("成功创建minio客户端");
            boolean isExist = minioClient.bucketExists(bucketName);
            if (isExist) {
                logger.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(bucketName);
                minioClient.setBucketPolicy(bucketName, "*.*");
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(bucketName, objectName, file.getInputStream(), file.getContentType());
            logger.info("文件上传成功!");
            MinioVO minioVO = new MinioVO();
            minioVO.setFileName(filename);
            minioVO.setFileUrl(endpoint + "/" + bucketName + "/" + objectName);
            return new ResultVO(minioVO);
        } catch (Exception e) {
            logger.info("上传发生错误: {}！", e);
        }
        return new ResultVO();
    }

    @PostMapping(value = "/delete")
    public ResultVO delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);
//            minioClient.removeObject(bucketName, objectName);
            // String url = minioClient.presignedGetObject("venus", "test.jpg"); // 获取文件目录
            List<Item> list = new ArrayList<>();
            Iterable<Result<Item>> results = minioClient.listObjects("venus");
            for (Result<Item> result : results) {
                Item item = result.get();
                list.add(item);
            }
            return new ResultVO(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO();
    }
}
