package com.team.devmanagement.utils;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class Utils {
    public static String pwdEncrypt(String username,String password){
        return DigestUtils.md5DigestAsHex((username+password).getBytes(StandardCharsets.UTF_8));
    }
}
