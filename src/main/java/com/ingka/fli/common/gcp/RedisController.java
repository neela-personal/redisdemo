//package com.ingka.fli.common.gcp;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RedisController {
//
//  private final RedisManager redisCache;
//
//  public RedisController(RedisManager redisCache){
//    this.redisCache = redisCache;
//  }
//
//  @PostMapping(path = "/test-redis")
//  public ResponseEntity<Object> testRedis() {
//
//    //create new key and value pairs
//    redisCache.saveOrUpdateData("testKey1","neela");
//    redisCache.saveOrUpdateData("testKey2", "neela2");
//
//    //retrieve available keys and values
//    for (String key : redisCache.retreiveAllKeys()) {
//      System.out.println(key+"--  "+redisCache.getData(key));
//    }
//    //delete a key
//    redisCache.deleteData("testKey2");
//    //retrieve available keys and values after a key deletion
//    for (String key : redisCache.retreiveAllKeys()) {
//      System.out.println(key+"--  "+redisCache.getData(key));
//    }
//    //updating an already existing key
//    redisCache.saveOrUpdateData("testKey1","neelasree");
//    //print the above keys value
//    System.out.println(redisCache.getData("testKey1"));
//    //trying to get a unavailable key
//    System.out.println(redisCache.getData("nokey"));
//
//    return ResponseEntity.ok("Redis test completed successfully");
//  }
//
//  }
