package com.example.testdemo.controller.result;

import com.example.testdemo.model.vo.LoginVo;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;
@Validated
@RestController
@RequestMapping("/result")
public class ResultController {
    @RequestMapping("/queryResult")
    public Map<String,Object> queryResult(@Valid @RequestBody LoginVo loginVo) throws Exception {
        if(!"87985937@qq.com".equals(loginVo.getEmail())){
            throw new Exception("请输入正确的邮箱地址");
        }
        Map map=new HashMap();
        map.put("msg","登录成功!");
        return map;
    }


    @RequestMapping("/queryResult1")
    public Map<String,Object> queryResult1(@Valid@NotBlank(message = "参数不能为空")@RequestParam(name="detailId") String detailId) throws Exception {
        Map map=new HashMap();
        if(StringUtils.isEmpty(detailId)){
            throw new Exception("参数为空");
        }
        return map;
    }
}
