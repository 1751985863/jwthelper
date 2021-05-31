package com.csa.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class AdminService {

    public String LoginByUsernameAndPassword(String username, String password) {
        //这里我就省略写了。不写dao层了。规定假设账号密码是admin,admin
        if ("admin".equals(username)&&"admin".equals(password)) {
            return "1234";
        }
        return null;

    }
}
