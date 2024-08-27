package com.example.testdemo.model.dto;

import com.example.testdemo.annotation.Fields;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
public class IntestinalInfectIonLis {

    /**
     * 申请明细Id
     */
    @Fields(name = "申请明细ID")
    private String detailId;
    private String testPurpose = "肠道细菌感染检测";
    private String sampleName = "粪便";
    /**
     * 报告id(确保每条数据唯一)
     */
    @Fields(name = "样本编号")
    private String sampleId;
    /**
     * 报告地址
     */
    private String reportUrl = "";
    /**
     * 急诊标志
     */
    private Integer emergencyFlag = 0;
    /**
     * 采样人
     */
    @Fields(name = "采样者")
    private String sampler;
    /**
     * 采样时间
     */
    @Fields(name = "送检时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date samplingTime;
    /**
     * 检验人姓名
     */
    @Fields(name = "检验者")
    private String tester;
    /**
     * 检验时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Fields(name = "检测时间")
    private Date testTime;
    /**
     * 报告人姓名
     */
    @Fields(name = "报告者")
    private String reporter;
    /**
     * 报告时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Fields(name = "报告时间")
    private Date reportTime;

    /**
     * 审核人姓名
     */
    @Fields(name = "审核者")
    private String auditor;
    /**
     * 审核时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Fields(name = "审核时间")
    private Date auditTime;

    private List<LabTestResult> labTestResults;
}
