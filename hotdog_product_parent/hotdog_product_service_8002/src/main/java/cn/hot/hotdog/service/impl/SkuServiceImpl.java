package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.Sku;
import cn.hot.hotdog.mapper.SkuMapper;
import cn.hot.hotdog.service.ISkuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * SKU 服务实现类
 * </p>
 *
 * @author xvbo
 * @since 2019-03-07
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {

}
