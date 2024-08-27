package com.example.testdemo.service.einvoice.Impl;

import com.example.testdemo.mapper.EinvoiceMessageMapper;
import com.example.testdemo.model.dto.EinvoiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EinvoiceMessageService {
    @Autowired
    EinvoiceMessageMapper einvoiceMessageMapper;

    public EinvoiceMessage getEinvoiceMessage(Integer id){
        //获取电子发票信息
        return  einvoiceMessageMapper.selectById(id);
    }
}
