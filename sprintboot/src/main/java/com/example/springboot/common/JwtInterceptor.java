package com.example.springboot.common;

/**
 * @Classname JwtInterceptor
 * @Description TODO
 * @Author zjj
 * @Date 10/24/23 8:44PM
 */
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.UserMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //inside http header -> acquire token
        String token = request.getHeader("token");

        //url parameter ?token=xxx
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token");
        }

//        // 如果不是映射到方法直接通过
//        if (handler instanceof HandlerMethod) {
//            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
//            if (annotation != null) {
//                return true;
//            }
//        }

        // authentication if doesn't have token -> check if we have token
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401", "please log in");
        }

        // Get user id inside token -> check userId in database
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException("401", "please log in");
        }

        // use userId inside token to query from database
        User user = userMapper.selectById(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException("401", "please log in");
        }

        // encrypt user password token to form verifier -> verify by password
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // verify token(actually verigy here)
        } catch (JWTVerificationException e) {
            throw new ServiceException("401", "please log in");
        }
        return true;
    }
}
