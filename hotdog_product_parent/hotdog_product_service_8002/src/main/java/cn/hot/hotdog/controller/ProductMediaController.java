package cn.hot.hotdog.controller;


import cn.hot.hotdog.service.IProductMediaService;
import cn.hot.hotdog.domain.ProductMedia;
import cn.hot.hotdog.query.ProductMediaQuery;
import cn.hot.hotdog.util.AjaxResult;
import cn.hot.hotdog.util.PageList;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productMedia")
public class ProductMediaController {
    @Autowired
    public IProductMediaService productMediaService;

    /**
     * 保存和修改公用的
     *
     * @param productMedia 传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult save(@RequestBody ProductMedia productMedia) {
        try {
            if (productMedia.getId() != null) {
                productMediaService.updateById(productMedia);
            } else {
                productMediaService.insert(productMedia);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("保存对象失败！" + e.getMessage());
        }
    }

    /**
     * 删除对象信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            productMediaService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("删除对象失败！" + e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProductMedia get(@PathVariable("id") Long id) {
        return productMediaService.selectById(id);
    }


    /**
     * 查看所有的员工信息
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ProductMedia> list() {

        return productMediaService.selectList(null);
    }


    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json", method = RequestMethod.POST)
    public PageList<ProductMedia> json(@RequestBody ProductMediaQuery query) {
        Page<ProductMedia> page = new Page<ProductMedia>(query.getPage(), query.getRows());
        page = productMediaService.selectPage(page);
        return new PageList<ProductMedia>(page.getTotal(), page.getRecords());
    }

    @RequestMapping(value = "/addmedia", method = RequestMethod.POST)
    public List<ProductMedia> add(@RequestBody Map<String, Object> map)

    {
        List<ProductMedia> productMedia = productMediaService.selectList(new EntityWrapper<ProductMedia>().eq("productid", map.get("productid")));
        ArrayList<ProductMedia> list = new ArrayList<>();
        for (ProductMedia media : productMedia) {
            String resource = media.getResource();
            String string = "http://192.168.31.174/"+resource;
            media.setResource(string);
            list.add(media);
        }
        return list;


    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public List<ProductMedia> addmedia(@RequestBody Map<String, Object> map){
        Object productid = map.get("productid");
       String  resource = (String)map.get("resource");
      //  productMediaService.update(new EntityWrapper<ProductMedia>().eq("productid", productid).eq(, ), new ProductMedia().setResource(resource));
        return null;
    }




}
