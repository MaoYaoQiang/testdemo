package com.example.testdemo.model.vo;

import javax.validation.constraints.NotNull;
public class LoginVo {
    // 邮箱
    @NotNull(message="邮箱不能为空")
    private String email;
    // 密码
    @NotNull(message = "密码不能为空")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginVo{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
