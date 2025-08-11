package com.formkio.formfio.services;

import com.formkio.formfio.dto.RateDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RateLimiterService {

    static HashMap<String, RateDTO> redis;
    private final int LIMIT = 1;

    public RateLimiterService() {
        redis = new HashMap<>();
    }

    public void addNewForm(String endpoint) {
        redis.put(endpoint, new RateDTO());
    }

    public boolean isAllowed(String endpoint) {
        RateDTO rateDTO = redis.get(endpoint);
        rateDTO.incCurrSubmission();
        return rateDTO.getCurrSubmission() <= LIMIT;
    }

}
