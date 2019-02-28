package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.ProductType;
import cn.hot.hotdog.mapper.ProductTypeMapper;
import cn.hot.hotdog.service.IProductTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    @Autowired
private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getTreeDate() {
        Map<Long ,ProductType> map = new HashMap<>();
        List<ProductType> productTypes = productTypeMapper.selectList(null);
        for (ProductType productType : productTypes) {
            Long id = productType.getId();
            map.put(id, productType);//将所有的数据存入map中
        }
        ArrayList<ProductType> pt = new ArrayList<>();
        for (ProductType productType : productTypes) {
            Long pid = productType.getPid();//老子的id
            if (pid==0) {
                pt.add(productType);
            }
            else{
                ProductType parent = map.get(pid);//获得老子
                parent.getChildren().add(productType);//将所有的儿子全添加进自己的老子中

            }

        }



            return pt ;
    }
}
