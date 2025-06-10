package com.ingka.fli.common.gcp;

import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
@Slf4j
public class GcpRedisConnector {


  private final RedisProperties properties;
  private Jedis jedis;
  private JedisPool jedisPool;

  public GcpRedisConnector(RedisProperties properties) {
    log.info("info log");
    log.debug("debug log");
    System.out.println("innnnnnnnnnnnnn");
    this.properties = properties;
    System.out.println("coming inside the redisconnector");

    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(128); // Maximum number of connections in the pool
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestOnReturn(true);
    System.out.println("object for jedispool");

    jedisPool = new JedisPool(poolConfig, properties.getHost(), properties.getPort());
    System.out.println("object for jedispool created");

  }

  public Jedis createJedisConnection() {
    // Configuration for the connection pool
    // Creating the connection pool
    // Getting a connection from the pool
    //try (Jedis jedis = jedisPool.getResource()) {
    System.out.println("creating connection");
      Jedis jedis = jedisPool.getResource();
      this.jedis = jedis;
      String pong = jedis.ping();
      System.out.println("Connection successful: " + pong);
      return this.jedis;
//    } catch (Exception e) {
//      NoSqlCacheError.getException(e.getMessage(), e);
//    }
//    return null;
  }
    @PreDestroy
    public void close() {
        // Close the connection pool
        if (jedis != null) {
          jedis.close();
        }
        System.out.println("connection closed");
    }

  public RedisProperties getProperties() {
    return properties;
  }

  public Jedis getJedis() {
    return this.jedis;
  }
}
