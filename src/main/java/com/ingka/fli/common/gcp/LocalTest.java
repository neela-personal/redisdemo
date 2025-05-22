package com.ingka.fli.common.gcp;

import java.time.LocalDate;

public class LocalTest {

  public static void main(String[] args)  {
    System.out.println("Hello, World!");
    GcpRedisConnector con = new GcpRedisConnector(new RedisProperties("127.0.0.1", 6379, 3600, 10));
    IntegrationProperties intProperties = new IntegrationProperties("resource", "Sync Stock Adjustment Default",  "FUI_FUI_00017_LOAD",
        "1.0",  "IIP",  "3",  "sendingSystem");
    con.createJedisConnection();
    RedisManager RedisUtils =  new RedisManager(con, intProperties);
    RedisUtils.isRetryExhausted("1234567890", String.valueOf(LocalDate.now()));
    //  properties = new RedisProperties("localhost", 6379, 3600, 10);
  }
}
