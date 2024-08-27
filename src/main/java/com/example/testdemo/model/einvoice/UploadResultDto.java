package com.example.testdemo.model.einvoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDto {
    private String billCode;
    private String billNum;
    private String random;
    private String pdfUrl;
}
