package com.example.testdemo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice("com.example")
public class BaseControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleValidationException(Exception e){
        e.printStackTrace();
        Map<String,Object> success =new HashMap();
        if(e instanceof MethodArgumentNotValidException){
            Map<String, Object> error = handleValidExcetion((MethodArgumentNotValidException) e);
             return error;
        }
        if(e instanceof HttpMessageNotReadableException){
            Map<String,Object> httpError =new HashMap();
            httpError.put("msg","请求参数不能为空!");
            return httpError;
        }
        if(e instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) e).getConstraintViolations();
            StringBuilder stringBuilder = new StringBuilder();
            constraintViolations.forEach(error->stringBuilder.append(error.getMessage()));
            success.put("msg",stringBuilder.toString());
        }
        if (e.getCause() != null) {
            success.put("msg",e.getCause().getCause());
        }else{
            success.put("msg",e.getMessage());
        }
        return success;
    }

    private Map<String,Object> handleValidExcetion(MethodArgumentNotValidException mrne){
        BindingResult bindingResult = mrne.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        Map errors=new HashMap();
        fieldErrors.forEach(fileError->{
            String errorField = fileError.getField();
            String defaultMessage = fileError.getDefaultMessage();
            errors.put(errorField,defaultMessage);
        });
        return errors;
    }

}
