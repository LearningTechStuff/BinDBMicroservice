package org.soaring.bindb.service;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.soaring.bindb.config.RedisConfiguration;
import org.soaring.bindb.data.CreditCardBin;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static final Logger logger = Logger.getLogger(RedisService.class);
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private RedisConfiguration redisConfiguration;

    public void put(CreditCardBin bin) {
        logger.info("Inserting a new bin into redis");
        Gson gson = new Gson();
        redisTemplate.opsForValue().set(bin.getNumber(), gson.toJson(bin), redisConfiguration.getTtl(), TimeUnit.MINUTES);
    }

    public CreditCardBin get(String bin) {
        Gson gson = new Gson();
        if(bin != null) {
            String value = redisTemplate.opsForValue().get(bin);
            if(value != null) {
                CreditCardBin creditCardBin = gson.fromJson(value, CreditCardBin.class);
                return creditCardBin;
            }
        }
        return null;
    }
}
