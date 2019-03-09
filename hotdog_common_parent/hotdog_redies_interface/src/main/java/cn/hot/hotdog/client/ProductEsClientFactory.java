package cn.hot.hotdog.client;

import cn.hot.hotdog.doc.ProductDoc;
import cn.hot.hotdog.util.AjaxResult;
import feign.hystrix.FallbackFactory;

import java.util.List;
import java.util.Map;

public class ProductEsClientFactory implements FallbackFactory<ProductEs> {


    @Override
    public ProductEs create(Throwable throwable) {
        return new ProductEs() {
            @Override
            public AjaxResult addOne(ProductDoc productDoc) {
                return null;
            }

            @Override
            public AjaxResult batchAdd(List<ProductDoc> list) {
                return null;
            }

            @Override
            public AjaxResult deleteOne(Long id) {
                return null;
            }

            @Override
            public AjaxResult bacthdel(List<Long> ids) {
                return null;
            }

            @Override
            public ProductDoc findOne(Long id) {
                return null;
            }
        };
    }
}
