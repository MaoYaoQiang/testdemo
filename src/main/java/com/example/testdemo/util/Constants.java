package com.example.testdemo.util;

public class Constants {

    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    public static final String EXCEPTION_HEAD = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
    /**
     * 修改就诊状态
     */
    public static final String UPDATE_TREAT_STATUS = "ocs.updateTreatStatus";
    /**
     * 同步就诊信息
     */
    public static final String SAVE_TREAT = "ocs.saveTreat";
    /**
     * 同步入院证
     */
    public static final String SAVE_ADMISSION = "ocs.saveAdmission";
    /**
     * 消息
     */
    public static final String MESSAGE = "ocs.messages";

    public static final String NULL = "null";
    /**
     * 同步挂号信息
     */
    public static final String SYNC_REGISTRATION = "sync:registration";
    /**
     * 用户信息
     */
    public static final String OCS_USER = "ocs:user:";
    public static final String PARAM_ERROR = "入参异常，param is null";

    /**
     * 是否删除
     */
    public static final Integer DELETED_YES = 1;
    public static final Integer DELETED_NO = 0;
    /**
     * 是否药品判别
     */
    public static final Integer DRUG_FLAG_YES = 1;
    public static final Integer DRUG_FLAG_NO = 0;

    /**
     * 常量
     */
    public static final Integer INT_NEGATIVE_ONE = -1;
    public static final Integer INT_NEGATIVE_TWO = -2;
    public static final Integer INT_ZERO = 0;
    public static final Integer INT_ONE = 1;
    public static final Integer INT_TWO = 2;
    public static final Integer INT_THREE = 3;
    public static final Integer INT_FOUR = 4;
    public static final Integer INT_FIVE = 5;
    public static final Integer INT_EIGHT = 8;
    public static final Integer INT_NINE = 9;
    public static final Integer INT_ELEVEN = 11;
    public static final Integer INT_TWELVE = 12;
    public static final Integer INT_THIRTEEN = 13;
    public static final Integer INT_FOURTEEN = 14;
    public static final Integer INT_FIFTEEN = 15;
    public static final Integer INT_NINETY = 90;

    /**
     * 预交款计算分类id
     */
    public static final String YJKCLASSID = "3007";

    /**
     * 医保预结算返回的个人现金支付金额
     */
    public static final String GRZFJE = "GRZFJE";
    public static final String QZZFJE = "QZZFJE";

    /**
     * 自助机系统模块代码
     */
    public static final String SELF_SYSTEMID = "SELF";

    /**
     * 7天毫秒数
     */
    public static final Integer ONEWEEK_MILLISECOND = 7 * 24 * 60 * 60 * 1000;
    /**
     * 1天毫秒数
     */
    public static final Integer ONEDAY_MILLISECOND = 24 * 60 * 60 * 1000;
    /**
     * 1小时毫秒数
     */
    public static final Integer ONEHOUR_MILLISECOND = 60 * 60 * 1000;
    public static final String STRING_ZERO = "0";
    public static final String STRING_ONE = "1";
    public static final String STRING_TWO = "2";
    public static final String STRING_THREE = "3";
    public static final String STRING_FOUR = "4";
    public static final String CHARGE_TYPE_ONE = "01";
    public static final String CHARGE_TYPE_TWO = "02";

    /**
     * 默认每页数量
     */
    public static final String PAGESIZE = "20";
    public static final int PAGESIZE_INT = 20;

    public static final int MAX_ORDERBY = 9999;

    /**
     * 分隔符
     */
    public static final String SPLIT_REGEX = "\\^";
    public static final String LINE_FEED = "\n";
    public static final String LINE_TABLE = "\t";
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String SPACE = "";
    public static final String BIGSPACE = "　";

    /**
     * 外部接口字典key
     */
    public static final String INTERFACE_KEYTYPE = "OCSExtInterface";
    public static final String APPOINTMENT_KEY = "复诊预约";
    public static final String OPSAPPLY_INTERFACE_KEYTYPE = "OCSExtInterface";
    public static final String OPSAPPLY_KEY = "手术申请";

    /**
     * 单位，如金额单元
     */
    public static final String MONEY_UNIT = "元";
    public static final String SEPARATOR = "\\^";

    public static final String PAGE_NO = "pageNo";
    public static final String PAGE_SIZE = "pageSize";


    /**
     * 统计查询相关常量
     */
    public static final String TOTAL_SETTLEUSERID = "settleUserId";
    public static final String TOTAL_COLNAME = "colName";
    public static final String TOTAL_STAFF = "staff";
    public static final String TOTAL_STAFFNAME = "staffName";
    public static final String TOTAL_DOUBLE = "double";
    public static final String TOTAL_TABLETYPE_DETAIL = "收费项目名称";
    public static final String TOTAL_DOCTORNAME = "doctorName";
    public static final String TOTAL_DEPARTMENTNAME = "departmentName";
    public static final String TOTAL_EXECDEPTNAME = "execDeptName";
    public static final String TOTAL_DAYDATE = "dayDate";
    public static final String TOTAL_MONTHDATE = "monthDate";
    public static final String TOTAL_YEARDATE = "yearDate";
    public static final String TOTAL_PATITYPENAME = "patiTypeName";
}
