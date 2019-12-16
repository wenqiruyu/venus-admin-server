package com.server.venus.elasticsearch.controller;

import com.server.venus.elasticsearch.entity.Product;
import com.server.venus.elasticsearch.mapper.IProductMapper;
import com.server.venus.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称：venus-admin-server
 * 类名称：ElasticSearchController
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/16
 * 修改人：yingx
 * 修改时间： 2019/12/16
 * 修改备注：
 */
@RestController
@RequestMapping("/elastic")
public class ElasticSearchController {

    @Autowired
    private IProductMapper productMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 将相关类创建索引和映射
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/16
     */
    @PostMapping("/add/index")
    public ResultVO createIndex() {

        // 创建索引，会根据product类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(Product.class);
        // 配置映射，会根据product类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(Product.class);
        return new ResultVO();
    }

    /**
     * 删除相关类的索引
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/16
     */
    @PostMapping("/del/index")
    public ResultVO deleteIndex() {

        // 可以根据类名或者索引名进行索引的删除
        elasticsearchTemplate.deleteIndex(Product.class);
        return new ResultVO();
    }

    /**
     * 新增一个对象
     *
     * @param product
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/16
     */
    @PostMapping("/add/Product")
    public ResultVO insertProduct(Product product) {

        productMapper.save(product);
        return new ResultVO();
    }
}
