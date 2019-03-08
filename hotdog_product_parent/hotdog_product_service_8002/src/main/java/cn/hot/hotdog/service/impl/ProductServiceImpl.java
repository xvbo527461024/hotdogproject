package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.domain.ProductExt;
import cn.hot.hotdog.domain.Sku;
import cn.hot.hotdog.mapper.ProductExtMapper;
import cn.hot.hotdog.mapper.ProductMapper;
import cn.hot.hotdog.mapper.SkuMapper;
import cn.hot.hotdog.query.ProductQuery;
import cn.hot.hotdog.service.IProductExtService;
import cn.hot.hotdog.service.IProductService;
import cn.hot.hotdog.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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


    @Autowired
    private SkuMapper skuMapper;


    @Override
    public boolean insert(Product product) {
        boolean b = super.insert(product);
        ProductExt productExt = product.getProductExt();
        // mp应该会返回保存数据的主键
        productExt.setProductId(product.getId());
        productExtMapper.insert(productExt);
        return b;
    }

    ;

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

    @Override
    public void addskuPropertise(Object productId, List<Map<String, Object>> skuProperties, List<Map<String, Object>> skuDatas) {
        //1先更新ProductExt中的viewProperties属性
        ProductExt productExt = new ProductExt();
        String string = JSONArray.toJSONString(skuProperties);
        productExt.setViewProperties(string);
        productExtMapper.update(productExt, new EntityWrapper<ProductExt>().eq("productid", productId));
 /*          productId:5
skuProperties:[{id=34, specName=套装, productTypeId=5, type=2, value=null, skuValues=[套餐一, 套餐二]},
 {id=35, specName=包装, productTypeId=5, type=2, value=null, skuValues=[清水, 精装]}]
skuDatas:
[{套装=套餐一, 包装=清水, price=5, availableStock=4},
{套装=套餐一, 包装=精装, price=10, availableStock=6},
{套装=套餐二, 包装=清水, price=6, availableStock=5},
{套装=套餐二, 包装=精装, price=15, availableStock=10}]
 */
        for (Map<String, Object> skuData : skuDatas
                ) {
            Sku sku = new Sku();
            sku.setProductId(Long.valueOf(productId.toString()));//保存产品id
            Set<Map.Entry<String, Object>> entries = skuData.entrySet();
            List<Map<String, Object>> otherList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : entries) {
                Map<String, Object> otherMap = new HashMap();
                String key = entry.getKey();
                Object value = entry.getValue();
                if ("price".equals(key)) {
                    sku.setPrice(Integer.valueOf(value.toString()));
                } else if ("availableStock".equals(key)) {
                    sku.setAvailableStock(Integer.valueOf(value.toString()));
                } else {
                    otherMap.put(key, value);
                    otherList.add(otherMap);
                }
            }
            List<Map<String, Object>> skuList = new ArrayList<>();
            //otherList:[otherList:[{套装=套餐一}, {包装=清水}]otherList:[{套装=套餐一},
            // {包装=精装}]otherList:[{套装=套餐二}, {包装=清水}] otherList:[{套装=套餐二}, {包装=精装}]
            System.out.println("otherList:" + otherList);
            for (Map<String, Object> om : otherList) {
                Map<String, Object> mm = new HashMap<>();
                Set<Map.Entry<String, Object>> entry = om.entrySet();
                String properKey = "";
                //entry:[套装=套餐一]entry:[包装=清水]entry:[套装=套餐一]entry:
                // [包装=精装]entry:[套装=套餐二]entry:[包装=清水]entry:[套装=套餐二]entry:[套装=套餐二]
                for (Map.Entry<String, Object> objectEntry : entry) {
                    properKey = objectEntry.getKey();
                    mm.put("key", properKey);
                    mm.put("value", objectEntry.getValue());
                }
                    Long propertyId = getPropId(properKey, skuProperties);
                    mm.put("id", propertyId);
                    skuList.add(mm);

                }
                sku.setSkuProperties(JSONArray.toJSONString(skuList));//设置sku的SkuProperties属性
                StringBuffer stringBuffer = new StringBuffer();
                for (Map<String, Object> qm : skuList) {//{"id":34,"value":"套餐一","key":"套装"}
                    Object id = qm.get("id");
                    Object value = qm.get("value");
                    Integer index = getIndex(id, value, skuProperties);
                    System.out.println("index:" + index);
                    stringBuffer.append(index).append("_");
                }
                String str = stringBuffer.toString();
                str = str.substring(0, stringBuffer.lastIndexOf("_"));
                sku.setSkuIndex(str);
                skuMapper.insert(sku);
            }

        }

//{"id":34,"value":"套餐一","key":"套装"},{"id":35,"value":"精装","key":"包装"}]
    //skuProperties:[{id=34, specName=套装, productTypeId=5, type=2, value=null, skuValues=[套餐一, 套餐二]},
// {id=35, specName=包装, productTypeId=5, type=2, value=null, skuValues=[清水, 精装]}]
        private Integer getIndex (Object id, Object value, List < Map < String, Object >> skuProperties){
            for (Map<String, Object> skuProperty : skuProperties) {
                Long id1 = Long.valueOf(skuProperty.get("id").toString());
                Long aLong = Long.valueOf(id.toString());
                if (id1.longValue() == aLong.longValue()) {
                    List<String> skuValues = (List<String>) skuProperty.get("skuValues");
                    int index = 0;
                    for (String skuValue : skuValues) {
                        if (skuValue.equals(value.toString())) {
                            return index;
                        }
                        index++;
                    }
                }
            }
            return null;
        }

        private Long getPropId (String properKey, List < Map < String, Object >> skuProperties){
            for (Map<String, Object> skuProperty : skuProperties) {
                String specName = (String) skuProperty.get("specName");
                if (specName.equals(properKey)) {
                    System.out.println("----------------------------------------------------");
                    return Long.valueOf(skuProperty.get("id").toString());
                }

            }
            return null;
        }
    }
