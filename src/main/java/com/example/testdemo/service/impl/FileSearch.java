package com.example.testdemo.service.impl;

import com.example.testdemo.service.Search;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FileSearch implements Search {
    @Override
    public List<String> searchDoc(String keyword) {
        System.out.println("文件搜索 "+keyword);
        return null;
    }
}
