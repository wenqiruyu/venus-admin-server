package com.server.venus.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 项目名称：venus-admin-server
 * 类名称：RequestPramsUtils
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/5
 * 修改人：yingx
 * 修改时间： 2019/12/5
 * 修改备注：
 */
public class RequestPramsUtils {

    /**
     * 获取POST请求的JSON数据
     * @author yingx
     * @date 2019/12/5
     * @param request 请求
     * @return com.alibaba.fastjson.JSONObject
     */
    public static JSONObject getRequestParam(HttpServletRequest request) {

        JSONObject paramsObj = new JSONObject();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);

            paramsObj = JSONObject.parseObject(responseStrBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramsObj;
    }
}
