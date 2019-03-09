package cn.hot.hotdog.client;
import cn.hot.hotdog.doc.ProductDoc;
import cn.hot.hotdog.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "COMMON-PROVIDER",fallbackFactory =ProductEsClientFactory.class )
@RequestMapping("/common/es")
public interface ProductEs {
    //添加一个
    @RequestMapping(value = "/addone",method = RequestMethod.POST)
    AjaxResult addOne(@RequestBody ProductDoc productDoc);
    //批量添加
    @RequestMapping(value = "/batchadd",method = RequestMethod.POST)
    AjaxResult batchAdd(@RequestBody List<ProductDoc> list);
    //删除一个
    @RequestMapping(value = "/deleteone/{id}",method = RequestMethod.POST)
    AjaxResult deleteOne(@PathVariable("id")Long id);

    //批量删除
    @RequestMapping(value = "/bacthdel",method = RequestMethod.POST)
    AjaxResult bacthdel(@RequestBody List<Long> ids);
    //查询一个
    @RequestMapping(value = "/findone/{id}",method = RequestMethod.POST)
   ProductDoc findOne(@PathVariable("id") Long id);

   /* @RequestMapping(value = "/productEsaddOne",method = RequestMethod.POST)
    AjaxResult addOne(@RequestBody ProductDoc productDoc);*/
}
