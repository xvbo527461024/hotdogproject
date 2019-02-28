package cn.hot.hotdog.service.impl;

import cn.hot.hotdog.domain.Brand;
import cn.hot.hotdog.mapper.BrandMapper;
import cn.hot.hotdog.query.BrandQuery;
import cn.hot.hotdog.service.IBrandService;
import cn.hot.hotdog.util.LetterUtil;
import cn.hot.hotdog.util.PageList;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务实现类
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {
@Autowired
private BrandMapper brandMapper;


    @Override
    public PageList<Brand> getPage(BrandQuery query) {
        List<Brand> page = brandMapper.getPage(query);
        Long getcount = brandMapper.getcount(query);
        PageList<Brand> pageList = new PageList<>();
if(getcount>0){
    pageList.setTotal(getcount);
}
pageList.setRows(page);
        return pageList;
    }

    @Override
    public String getFirstL(String s) {
        return LetterUtil.getFirstLetter(s);
    }


}
