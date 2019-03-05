package cn.hot.hotdog.client;

import feign.hystrix.FallbackFactory;

import java.util.Map;

public class PageStaticClientFactory implements FallbackFactory<PageStaticClient> {
    @Override
    public PageStaticClient create(Throwable throwable) {
        return new PageStaticClient() {
            @Override
            public void getPageStatic(Map<String, Object> map) {

            }
        };
    }
}
