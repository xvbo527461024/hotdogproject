package cn.hot.hotdog.mapper;

import cn.hot.hotdog.domain.Brand;
import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.query.BrandQuery;
import cn.hot.hotdog.query.ProductQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 品牌信息 Mapper 接口
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
public interface BrandMapper extends BaseMapper<Brand> {
    List<Brand> getPage(BrandQuery query);
    Long getcount(BrandQuery brandQuery);
}
