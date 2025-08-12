package com.formkio.formfio.services;


import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.UsersTable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.args.ExpiryOption;

import java.util.Calendar;
import java.util.Date;


/**
 * This is the rate limiter. The format for the string would look like:
 * submission:`form_endpoint`:`month`
 * ---
 * an example would be:
 * > submission:832-324-32kj-2zc-sadf9:3
 */
@Service
public class JedisLimiterService {

    private final int TEST_USER = 5;
    private final int FREE_TIER = 50;
    private final int SOLO_TIER = 500;
    private final int SMALL_TEAM_TIER = 5000;
    private final int BUSINESS_TIER = 100000;

    private final int DURATION = 12960000;
    private final JedisPool jedisPool;
    private final UsersTable usersTable;

    public JedisLimiterService(JedisPool jedisPool, UsersTable usersTable) {
        this.jedisPool = jedisPool;
        this.usersTable = usersTable;
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

        boolean isAllowed = count < getLimit(endpoint);
        if (isAllowed) {
            try (Jedis jedis = jedisPool.getResource()) {
                Transaction trans = jedis.multi();
                trans.incr(key);
                trans.expire(key, getDuration(), ExpiryOption.NX);
                trans.exec();
            }
        }

        return isAllowed;
    }

    /**
     * Calculates the duration from the current time until the start of next month.
     * It rounds the time diff and returns it as a int afterwards.
     *
     * @return `int` duration
     */
    private int getDuration() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date today = Calendar.getInstance().getTime();
        Date next = cal.getTime();

        long diff = next.getTime() - today.getTime();
        return (int) Math.ceil(diff / (float) (1000));
    }

    /**
     * It calculates the limit of submission a form is allowed based on the user's
     * account plan.
     * 0 -> 100     monthly submissions (free tier)
     * 1 -> 1,000   monthly submissions (free trial)
     * 2 -> 10,000  monthly submissions
     * 3 -> 100,000 monthly submissions
     * 4 -> Unlimited
     *
     * @return `int` the limit
     */
    private int getLimit(String endpoint) {
        // this is in the limiter service, so we ned the plan of the owner. I'm thinking it owuld be overal limit.
        // so like they would not be able ot have more than X submissions in total per month across all forms.
        UsersModel user = usersTable.getUserByEndpoint(endpoint);
        switch (user.getAccountPlan()){
            case 0:
                return FREE_TIER;
            case 1:
                return SOLO_TIER;
            case 2:
                return SMALL_TEAM_TIER;
            case 3:
                return BUSINESS_TIER;
            case 7:
                return TEST_USER;
            default:
                return 10;
        }
    }

}
