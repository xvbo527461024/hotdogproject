package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.client.PageStaticClient;
import cn.hot.hotdog.client.ResClient;
import cn.hot.hotdog.constans.GlobeConstans;
import cn.hot.hotdog.domain.ProductType;
import cn.hot.hotdog.mapper.ProductTypeMapper;
import cn.hot.hotdog.service.IProductTypeService;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
    @Autowired
    private ResClient resClient;
    @Autowired
    private PageStaticClient pageStaticClient;

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

    @Override
    public List<ProductType> getTreeDateRedis() {
        // 1.如果redis中没有存在数据，那么就先查询，返回给前台，再存进redis中
        System.out.println("redis++++++++++++++++++++++++");
        String jsonArrStr = resClient.get(GlobeConstans.REDIS_TREEDATE);
        if (StringUtils.isEmpty(jsonArrStr)){
            List<ProductType> treeDate = getTreeDate();
            String string = JSONArray.toJSONString(treeDate);
           resClient.set(GlobeConstans.REDIS_TREEDATE, string);
            System.out.println("第一次查询+++++++++++++++++++++");
            return treeDate;
        }
        // 2.如果redis中存在数据，那就直接在redis中获取
        else{
            //String s1 = resClient.get(GlobeConstans.REDIS_TREEDATE);
            //List<ProductType> productTypes = JSONArray.parseArray(s1, ProductType.class);
            System.out.println("存在缓存");
            return JSONArray.parseArray(jsonArrStr, ProductType.class);
        }


    }
    /*


     */
    @Override
    public boolean updateById(ProductType entity){
        //修改完后生成html
        boolean b = super.updateById(entity);
        Map<String,Object> map=new HashMap<>();
        List<ProductType> treeDate = getTreeDate();
       map.put(GlobeConstans.PAGE_MODE, treeDate);
       map.put(GlobeConstans.PAGE_TEMPLATE, "C:\\Users\\DELL\\hotdog_parent\\hotdog_common_parent\\hotdog_redies_interface\\src\\main\\resources\\template\\product.type.vm");
       map.put(GlobeConstans.PAGE_PATH_HTML, "C:\\Users\\DELL\\hotdog_parent\\hotdog_common_parent\\hotdog_redies_interface\\src\\main\\resources\\template\\product.type.vm.html");
       pageStaticClient.getPageStatic(map);
       //再生成home.html 的页面
        Map<String,Object> maphome=new HashMap<>();
        Map<String,String> root=new HashMap<>();
        root.put("staticRoot", "C:\\Users\\DELL\\hotdog_parent\\hotdog_common_parent\\hotdog_redies_interface\\src\\main\\resources\\");
        maphome.put(GlobeConstans.PAGE_MODE, root);
        maphome.put(GlobeConstans.PAGE_TEMPLATE, "C:\\Users\\DELL\\hotdog_parent\\hotdog_common_parent\\hotdog_redies_interface\\src\\main\\resources\\template\\home.vm");
        maphome.put(GlobeConstans.PAGE_PATH_HTML, "C:\\Users\\DELL\\hotdog_parent\\hotdog_common_parent\\hotdog_redies_interface\\src\\main\\resources\\template\\home.html");
        pageStaticClient.getPageStatic(maphome);
        return b;
    }

}
