package com.server.venus.elasticsearch.mapper;

import com.server.venus.elasticsearch.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 项目名称：venus-admin-server
 * 类名称：IProductMapper
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/16
 * 修改人：yingx
 * 修改时间： 2019/12/16
 * 修改备注： Product:实体类名 Long:实体类中主键的数据类型
 */
public interface IProductMapper extends ElasticsearchRepository<Product, Long> {
}
