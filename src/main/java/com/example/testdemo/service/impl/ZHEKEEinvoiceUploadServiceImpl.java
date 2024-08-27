package com.example.testdemo.service.impl;

import com.example.testdemo.model.einvoice.UploadResultDto;
import com.example.testdemo.service.einvoice.EinvoiceUploadService;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class ZHEKEEinvoiceUploadServiceImpl implements EinvoiceUploadService {
    @Override
    public UploadResultDto uploadOutpatientEinvoice(Map<String, Object> map) {
        return null;
    }

    @Override
    public UploadResultDto uploadInpatientEinvoice(Map<String, Object> map) {
        return null;
    }
}
