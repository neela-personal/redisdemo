package com.ingka.fli.common.gcp;

import java.time.LocalDate;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class LocalTest {

  public static void main(String[] args)  {
    System.out.println("Hello, World!");
    System.out.println("Hello, World!111111");
    System.out.println("before object for jedispool");



   GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
    poolConfig.setMaxTotal(128); // Maximum number of connections in the pool
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestOnReturn(true);
    System.out.println("object for jedispool");
    RedisProperties properties = new RedisProperties("10.92.137.163", 6379, 3600, 10);
    JedisPool jedisPool = new JedisPool(poolConfig, properties.getHost(), properties.getPort());

    Jedis jedis = jedisPool.getResource();
    String pong = jedis.ping();
    System.out.println("Connection successful: " + pong);

//    GcpRedisConnector con = new GcpRedisConnector(new RedisProperties("10.92.137.163", 6379, 3600, 10));
    IntegrationProperties intProperties = new IntegrationProperties("resource", "Sync Stock Adjustment Default",  "FUI_FUI_00017_LOAD",
        "1.0",  "IIP",  "3",  "sendingSystem");
    RedisManager RedisUtils =  new RedisManager(jedis,properties, intProperties);
    //RedisUtils.isRetryExhausted("1234567890", String.valueOf(LocalDate.now()));
    //  properties = new RedisProperties("localhost", 6379, 3600, 10);

    System.out.println("accesing the redis ..");
    RedisUtils.saveOrUpdateData("testKey1","neela");
    RedisUtils.saveOrUpdateData("testKey2", "neela2");

    //retrieve available keys and values
    for (String key : RedisUtils.retreiveAllKeys()) {
      System.out.println(key+"--  "+RedisUtils.getData(key));
    }
    //delete a key
    RedisUtils.deleteData("testKey2");
    //retrieve available keys and values after a key deletion
    for (String key : RedisUtils.retreiveAllKeys()) {
      System.out.println(key+"--  "+RedisUtils.getData(key));
    }
    //updating an already existing key
    RedisUtils.saveOrUpdateData("testKey1","neelasree");
    //print the above keys value
    System.out.println(RedisUtils.getData("testKey1"));
    //trying to get a unavailable key
    System.out.println(RedisUtils.getData("nokey"));


  }
}
