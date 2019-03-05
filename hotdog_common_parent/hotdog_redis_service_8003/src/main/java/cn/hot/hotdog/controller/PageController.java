package cn.hot.hotdog.controller;

import cn.hot.hotdog.client.PageStaticClient;
import cn.hot.hotdog.constans.GlobeConstans;
import cn.hot.hotdog.util.VelocityUtils;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/common")
public class PageController implements PageStaticClient{
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    @Override
    public void getPageStatic(@RequestBody Map<String, Object> map) {
        Object o = map.get(GlobeConstans.PAGE_MODE);//数据
        String o1 = (String)map.get(GlobeConstans.PAGE_TEMPLATE);//模板路径
        String o2 = (String)map.get(GlobeConstans.PAGE_PATH_HTML);//输出路径
        VelocityUtils.staticByTemplate(o, o1,o2);
    }

}
