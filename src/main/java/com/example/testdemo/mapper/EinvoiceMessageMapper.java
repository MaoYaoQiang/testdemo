package com.example.testdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.testdemo.model.dto.EinvoiceMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EinvoiceMessageMapper extends BaseMapper<EinvoiceMessage> {
}
