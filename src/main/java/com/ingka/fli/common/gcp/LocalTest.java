package com.ingka.fli.common.gcp;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalTest {

  public static void main(String[] args)  {
    System.out.println("Hello, World!");
    GcpRedisConnector con = new GcpRedisConnector(new RedisProperties("10.92.137.163", 6379, 3600, 10));
    IntegrationProperties intProperties = new IntegrationProperties("resource", "Sync Stock Adjustment Default",  "FUI_FUI_00017_LOAD",
        "1.0",  "IIP",  "3",  "sendingSystem");
    con.createJedisConnection();
    RedisManager RedisUtils =  new RedisManager(con, intProperties);
    //RedisUtils.isRetryExhausted("1234567890", String.valueOf(LocalDate.now()));
    //  properties = new RedisProperties("localhost", 6379, 3600, 10);


    RedisUtils.saveOrUpdateData("testKey1","neela");
    RedisUtils.saveOrUpdateData("testKey2", "neela2");

    //retrieve available keys and values
    for (String key : RedisUtils.retreiveAllKeys()) {
      log.info(key+"--  "+RedisUtils.getData(key));
    }
    //delete a key
    RedisUtils.deleteData("testKey2");
    //retrieve available keys and values after a key deletion
    for (String key : RedisUtils.retreiveAllKeys()) {
      log.info(key+"--  "+RedisUtils.getData(key));
    }
    //updating an already existing key
    RedisUtils.saveOrUpdateData("testKey1","neelasree");
    //print the above keys value
    log.info(RedisUtils.getData("testKey1"));
    //trying to get a unavailable key
    log.info(RedisUtils.getData("nokey"));


  }
}
