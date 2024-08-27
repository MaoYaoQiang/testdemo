package com.example.testdemo.service.impl;

import com.example.testdemo.mapper.UserMapper;
import com.example.testdemo.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;
    //查询全部
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }

    public int inserUser(User user) {
        return userMapper.insert(user);
    }

    public Map<String, Object> selectAmoutById(String Id) {
        return userMapper.selectAmoutById(Id);
    }
}
