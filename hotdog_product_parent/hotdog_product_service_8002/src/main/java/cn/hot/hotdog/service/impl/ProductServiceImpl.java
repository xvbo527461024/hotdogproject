package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.domain.ProductExt;
import cn.hot.hotdog.mapper.ProductExtMapper;
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
    @Autowired
    private ProductExtMapper productExtMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public boolean insert(Product product){
        boolean b = super.insert(product);
        ProductExt productExt = product.getProductExt();
        // mp应该会返回保存数据的主键
        productExt.setProductId(product.getId());
        productExtMapper.insert(productExt);
        return b;
    };
    public PageList<Product> selectQuery(ProductQuery query) {
        //分页查询: 以前在分页查询的时候:需要两个请求:总的条数和当前分页的数据
        //1:设置总的页数
        PageList<Product> pageList = new PageList<>();
        long totalcount = productMapper.queryPageCount(query);
        if (totalcount > 0) {
            pageList.setTotal(totalcount);
            //2:设置当前分页数据:
            // Mapper.xml中查询的是分页的数据:rows
            List<Product> products = productMapper.queryPageList(query);
            pageList.setRows(products);
        }


        return pageList;
    }
}
