package com.newkeshe.interceptor;

import com.newkeshe.util.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
        Map m = Optional.ofNullable(tokenService.decrypt(request.getHeader("Authorization")))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"未登录"));
        if(m.get("uPerm").equals(0)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"权限不足");
        }
        request.setAttribute("uId",m.get("uId"));
        request.setAttribute("uPerm",m.get("uPerm"));
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
