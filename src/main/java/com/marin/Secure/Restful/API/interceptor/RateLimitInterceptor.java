package com.marin.Secure.Restful.API.interceptor;

import com.marin.Secure.Restful.API.service.RateLimiterService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimiterService limiterService;

    @Autowired
    public RateLimitInterceptor(RateLimiterService limiter){
        this.limiterService = limiter;
    }

    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception {

        String ipAddress = request.getRemoteAddr();

        Bucket tokenBucket = limiterService.resolveBucket(ipAddress);
        ConsumptionProbe probe = tokenBucket.tryConsumeAndReturnRemaining(1);

        if(probe.isConsumed()){
            return true;
        }else{
            System.err.println("Interceptor");
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("You've tried too many times, wait one minute and try again");
            return false;
        }
    }
}
