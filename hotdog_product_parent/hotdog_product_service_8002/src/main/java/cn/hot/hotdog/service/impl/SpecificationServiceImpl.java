package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.Specification;
import cn.hot.hotdog.mapper.SpecificationMapper;
import cn.hot.hotdog.service.ISpecificationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author xvbo
 * @since 2019-03-05
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {

}
