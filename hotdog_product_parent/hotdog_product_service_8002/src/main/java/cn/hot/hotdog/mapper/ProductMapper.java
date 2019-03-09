package cn.hot.hotdog.mapper;

import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.query.ProductQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
public interface ProductMapper extends BaseMapper<Product> {

    long queryPageCount(ProductQuery query);

    List<Product> queryPageList(ProductQuery query);


    void updateAllputaway(Map<String, Object> params);

    void updateAllsoldout(Map<String, Object> params);
}
