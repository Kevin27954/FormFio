package com.formkio.formfio.services;


import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.args.ExpiryOption;

import java.util.Calendar;


/**
 * This is the rate limiter. The format for the string would look like:
 * submission:`form_endpoint`:`month`
 * ---
 * an example would be:
 * > submission:832-324-32kj-2zc-sadf9:3
 */
@Component
public class JedisLimiterService {

    private final int DURATION = 12960000;
    private final JedisPool jedisPool;

    public JedisLimiterService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public boolean isAllowed(String endpoint) {
        // month is 0-indexed
        int month = Calendar.getInstance().get(Calendar.MONTH);
        String key = "submission:" + endpoint + ":" + month;

        String countStr;
        try (Jedis jedis = jedisPool.getResource()) {
            countStr = jedis.get(key);
        }
        int count = countStr != null ? Integer.parseInt(countStr) : 0;

        boolean isAllowed = count < getLimit();

        if (isAllowed) {
            try (Jedis jedis = jedisPool.getResource()) {
                Transaction trans = jedis.multi();
                trans.incr(key);
                trans.expire(key, DURATION, ExpiryOption.NX);
                trans.exec();
            }
        }

        return isAllowed;
    }


    private int getLimit() {
        return 10;
    }

}
