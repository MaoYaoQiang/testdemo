package com.example.testdemo.service.einvoice;


import com.example.testdemo.model.einvoice.UploadResultDto;

import java.util.Map;

public interface EinvoiceUploadService {

    UploadResultDto uploadOutpatientEinvoice(Map<String,Object> map);

    UploadResultDto uploadInpatientEinvoice(Map<String,Object> map);
}
