package com.example.testdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testdemo.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT round(ifnull(sum(deposi_amount),'0'),2) as roadfund from user where id = 1")
   Map<String,Object> selectAmoutById(String id);

    @Select("SELECT * from user")
    List<User> selectUserInfo();
}
