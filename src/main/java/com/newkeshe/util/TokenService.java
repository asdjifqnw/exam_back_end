package com.newkeshe.util;

import com.newkeshe.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class TokenService {
    @Value("${my.secretkey}")
    private String secretkey;
    @Value("${my.salt}")
    private String salt;
    @Autowired
    MapToString mapToString;

    public String encrypt(Map m) {
        return Encryptors.text(secretkey, salt).encrypt(mapToString.MapToString(m));
    }

    public Map decrypt(String s) {
        return mapToString.StringToMap(Encryptors.text(secretkey, salt).decrypt(s));
    }
}
