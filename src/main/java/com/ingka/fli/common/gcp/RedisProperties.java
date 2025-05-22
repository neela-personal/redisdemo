package com.ingka.fli.common.gcp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RedisProperties implements NosqlCacheProperties {

   String host;
   Integer port;
   Integer keyExpiration;
   Integer poolMaxTotal;
}
