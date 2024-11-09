package com.example.springboot1.Interceptors;

import com.example.springboot1.utils.JwtUtil;
import com.example.springboot1.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String Token = request.getHeader("Authorization");
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        try {
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(Token);
            String redisToken = stringStringValueOperations.get(Token);
//            System.out.println("token:"+redisToken);
            if (redisToken==null){
                throw new RuntimeException();
            }
            ThreadLocalUtil.set(stringObjectMap);
            return true;
        } catch (Exception exception) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
