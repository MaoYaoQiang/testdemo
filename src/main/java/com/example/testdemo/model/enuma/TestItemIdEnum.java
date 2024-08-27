package com.example.testdemo.model.enuma;

public enum TestItemIdEnum {
    ITEM_ID_5442("5442","阳性","血液"),
    ITEM_ID_6731("6731","阳性","尿常规"),
    ITEM_ID_5430("5430","阳性","腺病毒"),
    ITEM_ID_5445("5445","阳性","有病毒"),
    ITEM_ID_5431("5431","阳性","涛哥的病毒"),
    ITEM_ID_5444("5444","阳性","坤哥的病毒"),
    ITEM_ID_5500("5500","阳性","你的病毒");
    private String value;
    private String name;
    private String desc;

    TestItemIdEnum(String value, String name, String desc) {
        this.value = value;
        this.name = name;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
