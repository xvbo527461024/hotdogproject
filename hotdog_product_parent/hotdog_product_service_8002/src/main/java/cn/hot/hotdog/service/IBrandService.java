package cn.hot.hotdog.service;

import cn.hot.hotdog.domain.Brand;
import cn.hot.hotdog.query.BrandQuery;
import cn.hot.hotdog.util.PageList;
import com.baomidou.mybatisplus.service.IService;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.util.List;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author xvbo
 * @since 2019-02-27
 */
public interface IBrandService extends IService<Brand> {
   PageList<Brand> getPage(BrandQuery query);
   String getFirstL(String s);

}
