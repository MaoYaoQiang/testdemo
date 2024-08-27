package com.example.testdemo.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lt_test_imaga")
public class LtTestImaga {
    public static final long serialVersionUID = -521L;
    @TableId(value = "increment_id", type= IdType.AUTO)
    private int incrementId;
    @TableField(value = "sample_id")
    private String sampleId;
    @TableField(value = "item_name")
    private String itemName;
    @TableField(value = "image")
    private String image;
    @TableField(value = "receive_date")
    private Date receiveDate;
    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "update_time")
    private Date updateTime;
}
