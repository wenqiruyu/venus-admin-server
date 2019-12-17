package com.server.venus.elasticsearch.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 项目名称：venus-admin-server
 * 类名称：Product
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/12/16
 * 修改人：yingx
 * 修改时间： 2019/12/16
 * 修改备注：   @Document 作用在类，标记实体类为文档对象，一般有两个属性
 *                      indexName：对应索引库名称
 *                      type：对应在索引库中的类型
 *                      shards：分片数量，默认5
 *                      replicas：副本数量，默认1
 *              @Id 作用在成员变量，标记一个字段作为id主键
 *              @Field 作用在成员变量，标记为文档的字段，并指定字段映射属性：
 *                      type：字段类型，是枚举：FieldType，可以是text、long、short、date、integer、object等
 *                      text：存储数据时候，会自动分词，并生成索引
 *                      keyword：存储数据时候，不会分词建立索引
 *                      Numerical：数值类型，分两类
 *                                  基本数据类型：long、interger、short、byte、double、float、half_float
 *                                  浮点数的高精度类型：scaled_float
 *                                  需要指定一个精度因子，比如10或100。elasticsearch会把真实值乘以这个因子后存储，取出时再还原。
 *                      Date：日期类型
 *                            elasticsearch可以对日期格式化为字符串存储，但是建议我们存储为毫秒值，存储为long，节省空间。
 *                      index：是否索引，布尔类型，默认是true
 *                      store：是否存储，布尔类型，默认是false
 *                      analyzer：分词器名称，这里的ik_max_word即使用ik分词器
 */
@Document(indexName = "venus-admin-server", type = "product", shards = 1, replicas = 0)
public class Product {

    @Id
    private Long id;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", store = true)
    private String title;

    /**
     * 分类
     */
    @Field(type = FieldType.Text, store = true)
    private String category;

    /**
     * 品牌
     */
    @Field(type = FieldType.Text, store = true)
    private String brand;

    /**
     * 价格
     */
    @Field(type = FieldType.Double, store = true)
    private Double price;

    /**
     * 图片地址
     */
    @Field(type = FieldType.Keyword, index = false, store = true)
    private String images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", images='" + images + '\'' +
                '}';
    }
}
