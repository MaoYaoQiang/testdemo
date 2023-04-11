package com.example.testdemo.exception;

import com.example.testdemo.model.result.ResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@ControllerAdvice
public class MyExceptionHandler {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public ResultDto parameterMissingExceptionHandler(RuntimeException e) {
////        System.out.println(e.getParameter().getExecutable().getAnnotations().toString());
////        System.out.println(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
////        System.out.println(e);
//        return new ResultDto(500, "aaa");
}
