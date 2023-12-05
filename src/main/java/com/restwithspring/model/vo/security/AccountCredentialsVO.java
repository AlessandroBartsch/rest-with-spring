package com.restwithspring.model.vo.security;

import java.io.Serializable;

public class AccountCredentialsVO implements Serializable {

    private String userName;
    private String password;

    public AccountCredentialsVO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
