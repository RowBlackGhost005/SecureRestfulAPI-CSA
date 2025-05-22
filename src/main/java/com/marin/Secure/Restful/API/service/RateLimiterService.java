package com.marin.Secure.Restful.API.service;

import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String , Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String identifier){
        return cache.computeIfAbsent(identifier , this::newBucket);
    }

    private Bucket newBucket(String identifier){

        return Bucket.builder().addLimit(RateLimit.CLIENT.getLimit()).build();
    }
}
