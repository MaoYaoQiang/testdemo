package com.example.testdemo.model.enuma;

public enum TestItemNameEnum {
    ITEM_ID_HCV_RNA("HCV-RNA定量","低于检测限","专用枚举类");
    private String value;
    private String name;
    private String desc;

    TestItemNameEnum(String value, String name, String desc) {
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
