package cn.hot.hotdog.service;

import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.query.ProductQuery;
import cn.hot.hotdog.util.PageList;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
public interface IProductService extends IService<Product> {
    @Override
    boolean insert(Product product);
    PageList<Product> selectQuery(ProductQuery query);

    void addskuPropertise(Object productId, List<Map<String, Object>> skuProperties, List<Map<String, Object>> skuDatas);
}
