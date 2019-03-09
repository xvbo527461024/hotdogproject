package cn.hot.hotdog.controller;

import cn.hot.hotdog.domain.ProductExt;
import cn.hot.hotdog.domain.Specification;
import cn.hot.hotdog.query.ProductQuery;
import cn.hot.hotdog.service.IProductExtService;
import cn.hot.hotdog.service.IProductService;
import cn.hot.hotdog.domain.Product;
import cn.hot.hotdog.service.ISpecificationService;
import cn.hot.hotdog.util.AjaxResult;
import cn.hot.hotdog.util.PageList;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    public IProductService productService;
    @Autowired
    private IProductExtService ProductExtService;
    @Autowired
    private ISpecificationService iSpecificationService;

    /**
    * 保存和修改公用的
    * @param product  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody Product product){
        try {
            if(product.getId()!=null){
                productService.updateById(product);
            }else{
                productService.insert(product);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            productService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Product get(@PathVariable("id")Long id)
    {
        return productService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Product> list(){

        return productService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<Product> json(@RequestBody ProductQuery query)
   /* {
        Page<Product> page = new Page<Product>(query.getPage(),query.getRows());
            page = productService.selectPage(page);
            return new PageList<Product>(page.getTotal(),page.getRecords());*/
    {
        return productService.selectQuery(query);


    }

    @RequestMapping(value = "/viewProperties/{productid}/{producttypeid}",method = RequestMethod.POST)
    public List<Specification> viewProperties(@PathVariable("productid")Long productid,@PathVariable("producttypeid")Long producttypeid){
        ProductExt productExt = ProductExtService.selectOne(new EntityWrapper<ProductExt>().eq("productid", productid));
        //System.out.println(productExt.getViewProperties());
        String st = productExt.getViewProperties();

        if (productExt!=null&&!(st==null)&&!st.isEmpty()){
            //修改显示属性
            System.out.println("回显");
            String viewProperties = productExt.getViewProperties();
            List<Specification> specifications = JSONArray.parseArray(viewProperties, Specification.class);
            return specifications;
        }else {
            //新增
            System.out.println("新增");
            List<Specification> type = iSpecificationService.selectList(new EntityWrapper<Specification>().eq("type", 1).eq("product_type_id", producttypeid));
            return type;
        }

      
            
            }

    @RequestMapping(value = "/addviewProperties",method = RequestMethod.POST)
    public AjaxResult add(@RequestBody Map<String,Object> map){

        try {
            Object productId = map.get("productId");
            Object viewProperties = map.get("viewProperties");
            ProductExt productExt = new ProductExt();
            String string = JSONArray.toJSONString(viewProperties);
            productExt.setViewProperties(string);
            ProductExtService.update(productExt, new EntityWrapper<ProductExt>().eq("productId", productId));
            return AjaxResult.me().setMsg("显示属性添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("显示属性添加失败:"+e.getMessage());
        }




    }

    @RequestMapping(value = "/viewProperties/{producttypeid}",method = RequestMethod.POST)
    public List<Specification> skuProperties(@PathVariable("producttypeid")Long producttypeid){
        List<Specification> specifications = iSpecificationService.selectList(new EntityWrapper<Specification>().eq("product_type_id", producttypeid).eq("type", 2l));


        return specifications;
        }

        //product/product/skuProperties
    @RequestMapping(value = "/skuProperties",method = RequestMethod.POST)
    public AjaxResult addskuProperties( @RequestBody Map<String,Object> map){
        try {
            //  let params = {"productId": productId, "skuProperties": this.skuProperties,"skuDatas":this.skuDatas};
            Object productId = map.get("productId");
            List<Map<String,Object>> skuProperties = ( List<Map<String,Object>>)map.get("skuProperties");
            List<Map<String,Object>> skuDatas =( List<Map<String,Object>>) map.get("skuDatas");
            System.out.println("productId:"+productId);
            System.out.println("skuProperties:"+skuProperties);
            System.out.println("skuDatas:"+skuDatas);
            productService.addskuPropertise(productId,skuProperties,skuDatas);
            return AjaxResult.me().setMsg("ssku属性保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("sku属性保存失败:"+e.getMessage()).setSuccess(false);
        }



    }

    //product/product/productEs 商品上架 let params=[{"ids":ids},{"i":i}];
    @RequestMapping(value = "/productEs",method = RequestMethod.POST)
    public AjaxResult putAway( @RequestBody Map<String,Object>  map) {
        try {
            Object ids = map.get("ids");
            Object i = map.get("i");
            if (ids != null && i != null) {
                String id = ids.toString();
                Long opt = Long.valueOf(i.toString());
                if (opt == 1) {//上架
                    productService.putaway(id, opt);
                    return AjaxResult.me().setMsg("上架成功");
                } else if (opt == 2) {//下架
                    productService.soldout(id, opt);
                    return AjaxResult.me().setMsg("下架成功");
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("操作失败:" + e.getMessage());
        }

        return AjaxResult.me().setSuccess(false).setMsg("请传入正确请求参数");
    }

}
