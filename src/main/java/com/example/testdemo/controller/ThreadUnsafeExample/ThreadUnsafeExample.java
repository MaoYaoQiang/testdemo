package com.example.testdemo.controller.ThreadUnsafeExample;

public class ThreadUnsafeExample {

    private volatile  int cnt = 0;

    public void add() {
            cnt++;
    }

    public int get() {
        return cnt;
    }

}
