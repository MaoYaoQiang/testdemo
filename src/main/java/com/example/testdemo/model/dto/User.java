package com.example.testdemo.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;


import java.io.Serializable;

@Data
@Builder
@TableName(value = "user")
public class User implements Serializable {
    public static final long serialVersionUID = -51L;
    @TableId(value = "id", type=IdType.AUTO)
    private Integer id;
    private String name;
    private String age;
    private String email;

}
