package cn.hot.hotdog.respository;

import cn.hot.hotdog.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEsRespository extends ElasticsearchRepository<ProductDoc,Long > {
}
