package com.server.venus.elasticsearch.controller;

import com.server.venus.elasticsearch.entity.Product;
import com.server.venus.elasticsearch.mapper.IProductMapper;
import com.server.venus.vo.ResultVO;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/16
     */
    @PostMapping("/add/product")
    public ResultVO addProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("三体");
        product.setCategory("科幻");
        product.setBrand("人民出版社");
        product.setPrice(11.11);
        Product productResult = productMapper.save(product);
        return new ResultVO(productResult);
    }

    /**
     * 批量新增
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/16
     */
    @PostMapping("/add/allProduct")
    public ResultVO addAllProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("三体");
        product.setCategory("科幻");
        product.setBrand("人民出版社");
        product.setPrice(11.11);
        Product product1 = new Product();
        product1.setId(2L);
        product1.setTitle("挪威的森林");
        product1.setCategory("小说");
        product1.setBrand("人民出版社");
        product1.setPrice(11.11);
        List<Product> allProduct = new ArrayList<>();
        allProduct.add(product);
        allProduct.add(product1);
        Iterable<Product> products = productMapper.saveAll(allProduct);
        return new ResultVO(products);
    }

    /**
     * 修改操作，修改和新增是使用的同一个接口，区别是根据主键来进行修改
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/update/product")
    public ResultVO updateProduct() {

        Product product = new Product();
        product.setId(1L);
        product.setTitle("流浪地球");
        product.setCategory("科幻");
        product.setBrand("人民出版社");
        product.setPrice(12.12);
        Product productResult = productMapper.save(product);
        return new ResultVO<>(productResult);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/product")
    public ResultVO getProductById(Long id) {

        Product product = productMapper.queryProductById(id);
        return new ResultVO(product);
    }

    /**
     * 查询所有数据
     * 对某字段排序查找所有 Sort.by("price").descending() 降序 Sort.by("price").ascending():升序
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/allProduct")
    public ResultVO getAllProduct() {

        Iterable<Product> allProduct = productMapper.findAll(Sort.by("price").descending());
        List<Product> productList = new ArrayList<>();
        for (Product product : allProduct) {
            productList.add(product);
        }
        return new ResultVO<>(productList);
    }

    /**
     * 获取价格区间的商品
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/between")
    public ResultVO getProductByBetween() {

        List<Product> allProduct = productMapper.findByPriceBetween(10.00, 12.00);
        return new ResultVO<>(allProduct);
    }

    /**
     * 匹配查询 词条的查询匹配 若要匹配查询除String类型的数据，可使用termQuery
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/matchQuery")
    public ResultVO getMatchQuery() {

        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        // 构建分词查询
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title", "流浪地球"));
        // 布尔查询
        /*nativeSearchQueryBuilder.withQuery(
                QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("title","三体"))
                        .must(QueryBuilders.matchQuery("brand","清华出版社"))
        );*/
        // 进行对查询结果排序
        /*nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));*/
        Page<Product> search = productMapper.search(nativeSearchQueryBuilder.build());
        return new ResultVO<>(search);
    }

    /**
     * 模糊查询
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/fuzzyQuery")
    public ResultVO getFuzzyQuery() {

        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.fuzzyQuery("title", "的"));
        Page<Product> search = productMapper.search(nativeSearchQueryBuilder.build());
        return new ResultVO<>(search);
    }


    /**
     * 分页查询
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/page")
    public ResultVO getQueryPage() {

        // 构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.termQuery("title", "的"));
        nativeSearchQueryBuilder.withPageable(PageRequest.of(1, 10));
        Page<Product> search = productMapper.search(nativeSearchQueryBuilder.build());
        return new ResultVO<>(search);
    }

    /**
     * 将数据聚合为桶，可以理解为分组
     *
     * @param
     * @return com.server.venus.vo.ResultVO
     * @author yingx
     * @date 2019/12/17
     */
    @PostMapping("/get/agg")
    public ResultVO getAgg() {

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 不查询任何结果
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
        // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand 在品牌聚合桶内进行嵌套聚合，求平均值
        queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand")
                        .subAggregation(AggregationBuilders.avg("priceAvg").field("price")));
        // 2、查询,需要把结果强转为AggregatedPage类型
        AggregatedPage<Product> aggPage = (AggregatedPage<Product>) productMapper.search(queryBuilder.build());
        // 3、解析
        // 3.1、从结果中取出名为brands的那个聚合，
        // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
        // 3.2、获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        // 3.3、遍历
        for (StringTerms.Bucket bucket : buckets) {
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }
        return new ResultVO<>(buckets);
    }
}
