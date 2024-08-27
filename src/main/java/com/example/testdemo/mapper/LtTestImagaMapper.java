package com.example.testdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testdemo.model.dto.LtTestImaga;
import com.example.testdemo.model.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface LtTestImagaMapper extends BaseMapper<LtTestImaga> {
    @Select("SELECT * from lt_test_imaga")
     List<LtTestImaga> getLtTestImaga();
}
