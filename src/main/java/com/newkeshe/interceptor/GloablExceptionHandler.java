package com.newkeshe.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author jinbin
 * @date 2018-07-08 22:37
 */
@RestControllerAdvice
public class GloablExceptionHandler {
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    public Object handleException(Exception e) {
//        String msg = e.getMessage();
//        if (msg == null || msg.equals("")) {
//            msg = "服务器出错";
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("msg", msg);
//        return jsonObject;
//    }
}
