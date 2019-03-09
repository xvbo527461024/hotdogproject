package cn.hot.hotdog.controller;

import cn.hot.hotdog.client.ProductEs;
import cn.hot.hotdog.client.ProductEsClientFactory;
import cn.hot.hotdog.doc.ProductDoc;
import cn.hot.hotdog.respository.ProductEsRespository;
import cn.hot.hotdog.service.IProductEsService;
import cn.hot.hotdog.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/common/es")
public class ProductEsController implements ProductEs{
@Autowired
private IProductEsService iProductEsService;
    @RequestMapping(value = "/addone",method = RequestMethod.POST)
    @Override
    public AjaxResult addOne(@RequestBody ProductDoc productDoc) {
        try {
            iProductEsService.addOne(productDoc);
            return AjaxResult.me().setMsg("上架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败:"+e.getMessage()).setSuccess(false);
        }

    }

    @RequestMapping(value = "/batchadd",method = RequestMethod.POST)
    @Override
    public AjaxResult batchAdd(@RequestBody List<ProductDoc> list) {
        try {
            iProductEsService.batchAdd(list);
            return AjaxResult.me().setMsg("上架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("上架失败:"+e.getMessage()).setSuccess(false);
        }

    }
    @RequestMapping(value = "/deleteone/{id}",method = RequestMethod.POST)
    @Override
    public AjaxResult deleteOne(@PathVariable("id")Long id) {
        try {
            iProductEsService.deleteOne(id);
            return AjaxResult.me().setMsg("下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("下架失败:"+e.getMessage()).setSuccess(false);
        }

    }
    @RequestMapping(value = "/bacthdel",method = RequestMethod.POST)
    @Override
    public AjaxResult bacthdel(@RequestBody List<Long> ids) {
        try {
            iProductEsService.bacthdel(ids);
            return AjaxResult.me().setMsg("下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMsg("下架失败:"+e.getMessage()).setSuccess(false);
        }

    }


    @RequestMapping(value = "/findone/{id}",method = RequestMethod.POST)
    @Override
    public ProductDoc findOne(@PathVariable("id") Long id) {

        return iProductEsService.findOne(id);
    }
}
