package cn.hot.hotdog.client;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RedisClientFactory implements FallbackFactory<ResClient> {
    @Override
    public ResClient create(Throwable throwable) {
        return new ResClient() {
            @Override
            public void set(String key, String value) {

            }

            @Override
            public String get(String key) {
                return null;
            }
        };
    }
}
