package cn.hot.hotdog.service;

import cn.hot.hotdog.doc.ProductDoc;
import cn.hot.hotdog.util.AjaxResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IProductEsService {
    //添加一个

   void addOne(@RequestBody ProductDoc productDoc);
    //批量添加

    void batchAdd(@RequestBody List<ProductDoc> list);
    //删除一个

    void deleteOne(@PathVariable("id")Long id);

    //批量删除

    void bacthdel(@RequestBody List<Long> ids);
    //查询一个

    ProductDoc findOne(@PathVariable("id") Long id);
}
