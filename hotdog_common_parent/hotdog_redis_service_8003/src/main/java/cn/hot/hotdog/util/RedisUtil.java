package cn.hot.hotdog.util;



import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil{
static JedisPool jedisPool=null;
    static { String host="127.0.1.1";
        GenericObjectPoolConfig poolConfig=new JedisPoolConfig();
        //参数设置:
        poolConfig.setMaxTotal(30);//最大连接数
        poolConfig.setMaxIdle(10);//最大空闲数
        poolConfig.setMaxWaitMillis(3*1000);//超时时间
        poolConfig.setTestOnBorrow(true);//借的时候进行测试
        int timeout=5*1000;
        String password="root";
        jedisPool =new JedisPool(poolConfig, host, 6379, timeout, password);

    }
    public static void set(String key, String value) {
        // 1:获取jedis实例
        Jedis jedis = jedisPool.getResource();
        try {
            //2:api操作
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3:资源换回
            jedis.close();
        }
    }

    /**
     * 获取
     * @param key
     * @return
     */
    public static String get(String key) {
        // 1:获取jedis实例
        Jedis jedis = jedisPool.getResource();
        try {
            //2:api操作
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //3:关闭资源
            jedis.close();
        }
    }


}
