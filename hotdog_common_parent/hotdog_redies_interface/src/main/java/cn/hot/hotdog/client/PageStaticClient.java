package cn.hot.hotdog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "COMMON-PROVIDER",fallbackFactory =PageStaticClientFactory.class )
@RequestMapping("/common")
public interface PageStaticClient {
    //根据给定的数据和模板生成指定的文件
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    void getPageStatic(@RequestBody Map<String,Object> map);
}

