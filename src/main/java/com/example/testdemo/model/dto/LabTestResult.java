package com.example.testdemo.model.dto;

import com.example.testdemo.annotation.Fields;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class LabTestResult {
    private String testItemId;
    private String testResultId;
    private String englishName;
    @Fields
    private String chineseName;
    @Fields(index = 1)
    private String result;
    private String unit = "";
    @Fields(index = 3)
    private String refValue;
    @Fields(index = 2)
    private String hint;
    private Integer emergency = 0;
    private Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
