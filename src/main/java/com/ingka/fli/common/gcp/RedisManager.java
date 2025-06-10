package com.ingka.fli.common.gcp;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;

@RequiredArgsConstructor
public class RedisManager {

  private final Jedis redisConnector;
  private final RedisProperties properties;
  private final IntegrationProperties integrationProperties;

  public Jedis getJedis() {
    return redisConnector;
  }
  public String getRetryCountFromCache(String cacheKey) {
    String ctr = "1";
    getJedis().exists("cacheKey");
    if (Boolean.TRUE.equals(getJedis().exists(cacheKey))) {
      getJedis().incr(cacheKey);
    } else {
      getJedis().set(cacheKey, ctr);
      getJedis().expire(cacheKey, properties.getKeyExpiration());
    }

    return getJedis().get(cacheKey);
  }

  public boolean isRetryExhausted(String messageId, String dateTime) {
    String retryCount = "";
    String cacheKey = integrationProperties.getAdapterName().concat("_").concat(messageId).concat("_").concat(dateTime);
    retryCount = getRetryCountFromCache(cacheKey);
    System.out.println("Retry to send the message with cacheKey: {} for the {} time " + cacheKey+"  --" + retryCount);
    return Integer.parseInt(retryCount) >= Integer.parseInt(integrationProperties.getRetryLimit());
  }
  public String getData(String key) {
      return getJedis().get(key);
  }
  public void saveOrUpdateData(String key, String value) {
    getJedis().set(key, value);
  }
  public void deleteData(String key) {
    getJedis().del(key);
  }

  public Set<String> retreiveAllKeys() {
    Set<String> keys = getJedis().keys("*");
    return keys;
  }

}
