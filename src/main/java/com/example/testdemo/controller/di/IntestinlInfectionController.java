package com.example.testdemo.controller.di;

import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.testdemo.annotation.Fields;
import com.example.testdemo.model.dto.IntestinalInfectIonLis;
import com.example.testdemo.model.dto.LabTestResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/uoloadResult")
public class IntestinlInfectionController {

    public IntestinlInfectionController() {
    }

    @RequestMapping("/uploadResult")
    public void  uploadLabResult(@RequestParam(name="data")MultipartFile multipartFile) throws Exception {
        if (multipartFile == null || multipartFile.isEmpty())
            throw new Exception("文件不能为空!");
        String originalFilename = multipartFile.getOriginalFilename();
        assert originalFilename != null : "文件后缀名不应该为空";
        if (!originalFilename.endsWith(".xls") && !originalFilename.endsWith(".xlsx")) {
            throw new Exception("文件格式错误");
        }
        ExcelReader reader;
        try{
           reader = ExcelUtil.getReader(multipartFile.getInputStream());
        }catch(Exception e){
            throw  new Exception("获取文件流异常");
        }
        List<List<Object>> read = reader.read();
        HashMap<Object, Object> fieldMap = new HashMap<>();
        Field[] declaredFields = LabTestResult.class.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Fields annotation = declaredFields[i].getAnnotation(Fields.class);
            if(annotation!=null){
                declaredFields[i].setAccessible(true);
                fieldMap.put(String.valueOf(annotation.index()),declaredFields[i]);
            }
        }
        Field[] lisFields = IntestinalInfectIonLis.class.getDeclaredFields();
        for (int i = 0; i < lisFields.length; i++) {
            Fields annotation = lisFields[i].getAnnotation(Fields.class);
            if(annotation!=null){
                lisFields[i].setAccessible(true);
                fieldMap.put(annotation.name(),lisFields[i]);
            }
        }
        List<IntestinalInfectIonLis> intestinalInfectIonLis = new ArrayList<>();
        IntestinalInfectIonLis infectionLis=null;
        List<LabTestResult> labTestResults=null;
        Boolean flag=false;
        for (int i = 0; i < read.size(); i++) {
            List<Object> objects = read.get(i);
            if("申请明细ID".equals(objects.get(0))){
                infectionLis = new IntestinalInfectIonLis();
                infectionLis.setDetailId(objects.get(1).toString());
                infectionLis.setSampleId(objects.get(3).toString());
                intestinalInfectIonLis.add(infectionLis);
                labTestResults=null;
                continue;
            }
            if(infectionLis==null){
                throw new Exception("格式异常，没有以【申请明细】开头");
            }
            if ("检测项目".equals(objects.get(0))){
                labTestResults=new ArrayList<>();
                infectionLis.setLabTestResults(labTestResults);
                continue;
            }
            if (labTestResults != null) {
                LabTestResult labTestResult = new LabTestResult();
                labTestResults.add(labTestResult);
                labTestResult.setSort(labTestResults.size());
                labTestResult.setUpdateTime(new Date());
                int j = 4;
                for (int i1 = 0; i1 < j; i1++) {
                    Field field = (Field) fieldMap.get(String.valueOf(i1));
                    if (field != null) {
                        String name = field.getName();
                        if ("result".equals(name)) {
                            BigDecimal bd = new BigDecimal(objects.get(i1).toString());
                            labTestResult.setResult(bd.stripTrailingZeros().toString());
                            continue;
                        }
                        Class<?> type = field.getType();
                        if (type == String.class) {
                            field.set(labTestResult, objects.get(i1).toString());
                        }
                    }
                }
            } else {
                for (int j = 0; j < objects.size() / 2; j++) {
                    Field field = (Field) fieldMap.get(objects.get(2*j));
                    if (field != null) {
                        Class<?> type = field.getType();
                        if (type == String.class) {
                            field.set(infectionLis, objects.get(2*j+1).toString());
                        } else if (type == Date.class){
                            String s = objects.get(2*j+1).toString().replaceAll("/", "-");
                            field.set(infectionLis, DateUtil.parse(s));
                        }
                    }
                }
            }

        }
        System.out.println(intestinalInfectIonLis.toString());
    }
}

