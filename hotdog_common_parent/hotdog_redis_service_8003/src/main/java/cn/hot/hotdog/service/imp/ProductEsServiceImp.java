package cn.hot.hotdog.service.imp;

import cn.hot.hotdog.doc.ProductDoc;
import cn.hot.hotdog.respository.ProductEsRespository;
import cn.hot.hotdog.service.IProductEsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductEsServiceImp implements IProductEsService{
    @Autowired
    private ProductEsRespository productEsRespository;

    @Override
    public void addOne(ProductDoc productDoc) {
        productEsRespository.save(productDoc);
    }

    @Override
    public void batchAdd(List<ProductDoc> list) {
productEsRespository.saveAll(list);
    }

    @Override
    public void deleteOne(Long id) {
productEsRespository.existsById(id);
    }

    @Override
    public void bacthdel(List<Long> ids) {
        for (Long id : ids) {
            productEsRespository.deleteById(id);
        }
    }

    @Override
    public ProductDoc findOne(Long id) {
        return productEsRespository.findById(id).get();
    }
}
