package com.newkeshe.interceptor;

import com.newkeshe.util.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception{
        String token = request.getHeader("Authorization");
        log.info("开始检查是否有管理员权限.");
        if(token == null){
            throw new RuntimeException("无token,请登录");
        }
        Map<String,String> map = tokenService.decrypt(token);
        log.info("管理员id:{} 通过验证",map.get("uId"));
        request.setAttribute("uId",map.get("uId"));
        request.setAttribute("uPerm",map.get("uPerm"));
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
