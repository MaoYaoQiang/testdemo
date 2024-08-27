package com.example.testdemo.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName(value = "einvoice_message")
public class EinvoiceMessage  implements Serializable {
    public static final long serialVersionUID = -100L;
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value="create_time")
    private Date createTime;
    @TableField(value="update_time")
    private Date updateTime;
}
