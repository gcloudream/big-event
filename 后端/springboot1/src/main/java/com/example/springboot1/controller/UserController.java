package com.example.springboot1.controller;

import com.example.springboot1.pojo.Result;
import com.example.springboot1.pojo.User;
import com.example.springboot1.service.UserService;
import com.example.springboot1.utils.JwtUtil;
import com.example.springboot1.utils.Md5Util;
import com.example.springboot1.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$") String username, @Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$") String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            userService.register(username, password);
            System.out.println("注册成功");
            return Result.success();
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$") String username, @Pattern(regexp = "^[a-zA-Z0-9_-]{5,15}$") String password,@RequestHeader(value = "Authorization",required = false) String token) {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        if (token!=null){
            stringStringValueOperations.getOperations().delete(token);
        }
        User login=userService.findByUserName(username);
        if (login==null){
            return Result.error("用户名不存在");
        }
        if (Md5Util.getMD5String(password).equals(login.getPassword())){
            Map<String,Object> map=new HashMap<>();
            map.put("username",username);
            map.put("id",login.getId());
            String Jwt = JwtUtil.genToken(map);


            stringStringValueOperations.set(Jwt,Jwt,1, TimeUnit.HOURS);
            return Result.success(Jwt);
        }

        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result userInfo(){
//        Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
//        String username = (String) stringObjectMap.get("username");
        Map<String, Object> stringObjectMap = ThreadLocalUtil.get();
        String username = (String) stringObjectMap.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        String old_pwd=params.get("old_pwd");
        String new_pwd=params.get("new_pwd");
        String re_pwd=params.get("re_pwd");
        if (!StringUtils.hasLength(old_pwd)||!StringUtils.hasLength(new_pwd)||!StringUtils.hasLength(re_pwd)){
            return Result.error("参数错误");
        }

        Map<String, Object> stringObjectMap=ThreadLocalUtil.get();
        String username = (String) stringObjectMap.get("username");
        User user = userService.findByUserName(username);
        if (Md5Util.getMD5String(old_pwd).equals(user.getPassword())&&new_pwd.equals(re_pwd)){
            userService.updatePwd(new_pwd);
        }else {
            return Result.error("密码错误");
        }
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.getOperations().delete(token);
        return Result.success();
    }
}


