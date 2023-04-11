package com.example.testdemo.controller;

import com.example.testdemo.service.Search;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.ServiceLoader;
@RestController
@RequestMapping("/search")
public class searchController {
@RequestMapping("/mysearch")
    public void serch() {
    System.out.println("1111111");
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello world");
        }
    }
}
