package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.mapper.ProductMapper;
import cn.hot.hotdog.query.ProductQuery;
import cn.hot.hotdog.service.IProductService;
import cn.hot.hotdog.util.PageList;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
