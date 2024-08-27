package com.example.testdemo;

import cn.hutool.core.date.DateTime;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.http.webservice.SoapClient;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import com.example.testdemo.controller.IfElseController;
import com.example.testdemo.controller.UserController;
import com.example.testdemo.model.dto.Student;
import com.example.testdemo.service.UserService;
import com.example.testdemo.service.threadpool.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StopWatch;


import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootTest
class TestdemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    IfElseController ifElseController;
    @Autowired
    DemoService demoService;
    @Autowired
    UserController userController;
    @Autowired
    StringRedisTemplate stringRedisTemplate;


    //工厂模式
//   @Test
//  public void factoryTest(){
////       ShapeFactory shapeFactory = new ShapeFactory();
////       Shape cricle = shapeFactory.getShape("CIRCLE");
////       cricle.draw();
//       String mobile = "111111";//移动手机号码
//       String mobileRegEx = "^1[3,4,5,6,7,8,9][0-9]{9}$";//正则表达式
//
//       Pattern pattern = Pattern.compile(mobileRegEx);//函数语法 匹配的正则表达式
//       Matcher matcher = pattern.matcher(mobile);//进行匹配
//
//       if (!matcher.matches()) {//校验手机号格式是否正确，若是匹配成功则返回true
//           System.out.println("移动手机号格式错误");
//       }else{
//           System.out.println("移动手机格式正确");
//       }
//
//   }
//
    @Test
    public void queryAll() throws IOException {
//        userService.queryAll().forEach(System.out::println);
//        User mao = User.builder().id(5).age("111").email("87985937@qq.com").name("毛要强").build();
//        int i = userService.inserUser(mao);


//        int a=1;
//        String s="1";
//        String ss="4a1ba832ba254fa2ac5b17a709b45588|8b9d06c8933c44d58edb9041969dcbcd";
//        String[] split = ss.split("\\|");
//        System.out.println(split);
//        for (String s1 : split) {
//            System.out.println(s1);
//        }
//        if(s.equals(a+"")){
//            System.out.println("11111");
//        }else{
//            System.out.println("22222");
//        }
//
//      List list=  new ArrayList<>();
//        list.add("1");
//        list.add("1");
//        list.add("3");
//        long count = list.stream().distinct().count();
//        System.out.println(count);
//        if(count<list.size()){
//            System.out.println("111111111111111232323232");
//        }
//
//        String abc="1";
//        int abs = Math.abs(Integer.valueOf(abc));
//        String abcc=abs+"";
//        System.out.println(abs);
//        Lock lock=new ReentrantLock();

//        File file = new File("/Users/apple/Desktop/v_hsbdi_requestinfo.txt");
//        byte[] bytes = new byte[1024];
//        String name = file.getName();
//        StringBuilder stringBuilder = new StringBuilder();
//        InputStream in  = new FileInputStream(file);
//        int read = in.read(bytes);
//        System.out.println(read);
//        Path path = Paths.get("/Users/apple/Desktop/血液影响结果调用回传.txt");
//        byte[] bytes = Files.readAllBytes(path);
//        String s = new String(bytes);
//        System.out.println(s);
        Map map1=new HashMap<>();
        Map map2=new HashMap<>();
        Map map3=new HashMap<>();
        map1.put("aa","111");
        map1.put("bb","111");
        map1.put("cc","111");
        map1.put("dd","111");
        map1.put("ee","111");
        map1.put("isCharge","1");
        map2.put("aa","222");
        map2.put("hh","222");
        map2.put("jj","222");
        map2.put("kk","222");
        map2.put("ee","222");
        map2.put("isCharge","2");
        map3.put("aa","333");
        map3.put("hh","333");
        map3.put("jj","333");
        map3.put("kk","333");
        map3.put("ee","333");
        map3.put("isCharge","3");
        //map1.putAll(map2);
        List<Map<String,Object>> list=new ArrayList();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        List<Object> collect = list.stream().filter(map -> map.get("isCharge").equals("1")).map(k -> k.get("aa")).collect(Collectors.toList());
        List<Object> collect1 = list.stream().map(k -> k.get("aa")).collect(Collectors.toList());
        System.out.println("======"+collect.toString());
        System.out.println("======"+collect1.toString());
        List<Object> aa = list.stream().map(k -> k.get("aa")).collect(Collectors.toList());
        System.out.println(aa);
        String join = StringUtils.join(aa, "|");
        System.out.println(join);
        String roundMap="1111";
        roundMap=roundMap+"你好呀";
        System.out.println(roundMap);
        String roadRund="0.00";
        if(!"0.00".equals(roadRund)){
            System.out.println("我曹草窝草草草草");
        }
       // Map<String, Object> amountMap = userService.selectAmoutById("1");
        //String roadfund = amountMap.get("roadfund").toString();
       // System.out.println(roadfund);

    }
    @Test
    public void testssss() throws ExecutionException, InterruptedException {
          Boolean strategy = ifElseController.strategy(2);
         List<String> strings = demoService.useTaskPoolDemo();
         userController.testCompletableFuture();
        userService.getUserInfo();
        stringRedisTemplate.opsForValue().set("mao","zhanggaopei");

        String s="|1001:1|1002:1|1003:1|1004:1|";
        String[] split = s.split("\\|");
        for (String s1 : split) {
            System.out.println(s1);
        }
    }

    @Test
    public void hutoolTest() throws InterruptedException {
        String yyyyMMddHHmmss = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        System.out.println(yyyyMMddHHmmss);

        String year = new SimpleDateFormat("yy", Locale.CHINESE).format(new Date());
        System.out.println(year);
        StopWatch stopWatch = new StopWatch("测试");
        stopWatch.start("测试开始");
        Thread.sleep(2000);
        stopWatch.stop();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               System.out.println("你好");
                           }
                       },5
        );
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println("uuid"+s);
        System.out.println(stopWatch.prettyPrint());
        DateTime date = DateUtil.date();
        String yyyyMMddHHmmssSSS = DateUtil.format(date, "yyyyMMddHHmmssSSS");
        System.out.println(yyyyMMddHHmmssSSS);
    }


    public Student getadd(Map map){
        Student student = new Student();
        student.setUserId(map.get("a").toString());
        student.setUsername(map.get("b").toString());
        System.out.println(student.toString()
        );
        return student;
    }
    @Test
    public void testadd(){
        HashMap hashMap = new HashMap();
        HashMap hashMap1 = new HashMap();
        hashMap.put("a","aa0");
        hashMap.put("b","bb0");
        hashMap1.put("a","aa1");
        hashMap1.put("b","bb1");
        Student getadd = getadd(hashMap);
        System.out.println(getadd.toString()+"=========2");
        CompletableFuture.runAsync(()->{
            HashMap map = new HashMap();
            map.put("a","aa3");
            map.put("b","bb3");
            Student getadd1 = getadd(map);
            System.out.println(getadd1.toString()+"=======3");
        });
        Student add = getadd(hashMap1);
        System.out.println(add+"================11111");

        String a="33.33";
        String b="0.011";
        BigDecimal aa = new BigDecimal(a);
        BigDecimal bb = new BigDecimal(b);
        BigDecimal subtract = aa.subtract(bb);
        System.out.println("--------"+subtract.toString());
    }

    @Test
    public void webservice() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        String s="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<Request xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
//                "  <Head>\n" +
//                "    <CommandID>CriticalValueNotice</CommandID>\n" +
//                "    <SysID>UIS</SysID>\n" +
//                "  </Head>\n" +
//                "  <CriticalValueNoticeInfo>\n" +
//                "    <PlacerOrderNO>Z231128US379</PlacerOrderNO>\n" +
//                "    <PlacerOrderDetailNO />\n" +
//                "    <FillerOrderNO>1101916</FillerOrderNO>\n" +
//                "    <ServiceSectID>US</ServiceSectID>\n" +
//                "    <PatientClass>住院</PatientClass>\n" +
//                "    <VisitID>383557</VisitID>\n" +
//                "    <MedRecNO>8518004</MedRecNO>\n" +
//                "    <OrganizationID />\n" +
//                "    <TransactionTime>2023-11-29 16:37:03</TransactionTime>\n" +
//                "    <CriticalValue>1.左室收缩功能减弱（左室EF为＜55％）\n" +
//                "  左室壁弥漫性运动减弱\n" +
//                "  主动脉瓣、二尖瓣、三尖瓣轻度反流\n" +
//                "2.右胸腔积液\n" +
//                "3.腹腔积液\n" +
//                "4.双下肢深静脉血栓形成</CriticalValue>\n" +
//                "    <CriticalDesc>接受电话者工号：2411</CriticalDesc>\n" +
//                "    <InitiateUserID>0131</InitiateUserID>\n" +
//                "    <InitiateWorkNO>zyn</InitiateWorkNO>\n" +
//                "    <InitiateUserName>章宜娜</InitiateUserName>\n" +
//                "    <InitiateUserPhoneNO />\n" +
//                "    <ReceiveUserID />\n" +
//                "    <ReceiveWorkNO />\n" +
//                "    <ReceiveUserName>裴菱花</ReceiveUserName>\n" +
//                "    <ReceiveUserPhoneNO>8230</ReceiveUserPhoneNO>\n" +
//                "    <AccessionNumber>US1101916</AccessionNumber>\n" +
//                "    <Name>陈凤梅</Name>\n" +
//                "    <OutPatientNO />\n" +
//                "    <InPatientNO>00213917</InPatientNO>\n" +
//                "    <ObservationDeptID />\n" +
//                "    <ObservationDeptName />\n" +
//                "    <PointOfCare>十五病区</PointOfCare>\n" +
//                "    <Bed>21525C</Bed>\n" +
//                "    <ServiceText>门诊彩超</ServiceText>\n" +
//                "  </CriticalValueNoticeInfo>\n" +
//                "</Request>";
//       // String s1 = WebServiceClient.toWebService("acceptMessage","http://192.168.191.20:8000/ExamInterface.svc?wsdl", s);
//       // System.out.println(s1);
//        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
//        Client client = factory.createClient("http://192.168.191.20:8000/ExamInterface.svc?wsdl");
//        Object[] acceptMessages = client.invoke("acceptMessage", s);
//        String s1 = acceptMessages[0].toString();
//        System.out.println(s1);

//        try {
//            // 创建 SOAP 连接和消息工厂
//            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
//            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
//            MessageFactory messageFactory = MessageFactory.newInstance();
//
//            // 创建 SOAP 消息
//            SOAPMessage soapMessage = messageFactory.createMessage();
//
//            // 创建 SOAP Part
//            SOAPPart soapPart = soapMessage.getSOAPPart();
//
//            // 创建 SOAP Envelope
//            SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//            soapEnvelope.addNamespaceDeclaration("example", "http://www.example.com");
//
//            // 创建 SOAP Body
//            SOAPBody soapBody = soapEnvelope.getBody();
//
//            // 创建 SOAP 请求消息体
//            SOAPElement soapElement = soapBody.addChildElement("HelloWorldRequest", "example");
//            SOAPElement messageElement = soapElement.addChildElement("Message", "example");
//            messageElement.addTextNode("Hello, World!");
//
//            // 打印 SOAP 请求消息
//            System.out.println("SOAP Request:");
//            soapMessage.writeTo(System.out);
//            System.out.println();
//
//            // 发送 SOAP 请求并获取响应
//            String endpointUrl = "http://192.168.191.20:8000/ExamInterface.svc?wsdl";
//            SOAPMessage soapResponse = soapConnection.call(soapMessage, endpointUrl);
//
//            // 打印 SOAP 响应消息
//            System.out.println("SOAP Response:");
//            soapResponse.writeTo(System.out);
//            System.out.println();
//
//            // 关闭 SOAP 连接
//            soapConnection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Request>\n" +
                "\t<Head>\n" +
                "\t\t<CommandID>CriticalValueProcess</CommandID>\n" +
                "\t\t<SysID>HIS</SysID>\n" +
                "\t\t<Timestamp></Timestamp>\n" +
                "\t\t<Token></Token>\n" +
                "\t</Head>\n" +
                "\t<CriticalValueProcessInfo>\n" +
                "\t\t<PlacerOrderNO>申请单号，电子申请单时必须</PlacerOrderNO>\n" +
                "\t\t<PlacerOrderDetailNO>申请单明细号，多个以,分隔</PlacerOrderDetailNO>\n" +
                "\t\t<FillerOrderNO>检查唯一号</FillerOrderNO>\n" +
                "\t\t<ServiceSectID>检查类型，如CR、CT、US、ES</ServiceSectID>\n" +
                "\t\t<PatientClass>病人类型，门诊、急诊、住院、体检</PatientClass>\n" +
                "\t\t<VisitID>就诊唯一号</VisitID>\n" +
                "\t\t<MedRecNO>病历号 </MedRecNO>\n" +
                "\t\t<OrganizationID>机构ID</OrganizationID>\n" +
                "\t\t<TransactionTime>危急值处理时间，默认为当前时间</TransactionTime>\n" +
                "\t\t<ProcessMethod>处理措施</ProcessMethod>\n" +
                "\t\t<ProcessUserID>处理用户ID</ProcessUserID>\n" +
                "\t\t<ProcessWorkNO>处理用户工号</ProcessWorkNO>\n" +
                "\t\t<ProcessUserName>处理用户姓名</ProcessUserName>\n" +
                "\t\t<ProcessUserPhoneNO>处理用户手机号码</ProcessUserPhoneNO>\n" +
                "\t</CriticalValueProcessInfo>\n" +
                "</Request>";


        // 新建客户端
        SoapClient client = SoapClient.create("http://192.168.191.20:8000/ExamInterface.svc?wsdl")
                .header("SOAPAction", "http://tempuri.org/IExamInterface/acceptMessage")
                // 设置要请求的方法，此接口方法前缀为web，传入对应的命名空间
                .setMethod("acceptMessage", "http://tempuri.org/")
                // 设置参数，此处自动添加方法的前缀：web
                .setParam("message", requestXml);

        System.out.println(client.getMsgStr(true));
        // 发送请求，参数true表示返回一个格式化后的XML内容
        // 返回内容为XML字符串，可以配合XmlUtil解析这个响应
    }

    @Test
    public void testDemo(){
   // List<String> strings = RuntimeUtil.execForLines("mysql -u root -p");
//        String s="<RESPONSE><RESULT_CODE>1</RESULT_CODE><RESULT_MSG></RESULT_MSG><SAMPLING_INFO><SAMPLING><ID>14484</ID><COUNT>1</COUNT><ORDER_ID>60521</ORDER_ID><CHARGE_ITEM_ID>83702</CHARGE_ITEM_ID></SAMPLING></SAMPLING_INFO><TUBE_INFO><TUBE><ID>14906</ID><COUNT>1</COUNT><ORDER_ID>60521</ORDER_ID><CHARGE_ITEM_ID>83702</CHARGE_ITEM_ID></TUBE></TUBE_INFO></RESPONSE>";
//        JSONObject entries = XML.toJSONObject(s);
//        System.out.println(entries.getJSONObject("RESPONSE").get("RESULT_CODE"));
//        System.out.println(entries.getJSONObject("RESPONSE").get("RESULT_MSG"));
//        System.out.println(entries.getJSONObject("RESPONSE").getJSONObject("TUBE_INFO"));
//        System.out.println(System.currentTimeMillis());
//
//        new BigDecimal(300).multiply(new BigDecimal(10)).toString();
//        System.out.println(new BigDecimal("300").multiply(new BigDecimal("10")).toString());
//        String s = UUID.randomUUID().toString().replaceAll("\\-","");
//        System.out.println(s);
//        String a="0.00";
//        BigDecimal bigDecimal = new BigDecimal(a);
//        System.out.println(bigDecimal);
//        BigDecimal bigDecimal1 = new BigDecimal("1.11");
//        Map objectObjectHashMap = new HashMap<>();
//        objectObjectHashMap.put("ok",bigDecimal1);
//        String ok = objectObjectHashMap.get("ok").toString();
//        System.out.println(ok);
//
//        try {
//            String url = "http://183.129.200.100:18088/DztMain/Authentication/auth/etaxzjgovauth"; // 替换为实际URL
//            Process process = Runtime.getRuntime().exec("curl " + url);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Integer i=-1;
        if(-1 ==i){
            System.out.println("测试测试测试");
        }else{
            System.out.println("失败失败失败");
        }
        String aa="aaaaaa,bbbbbb";
        String s1 = aa.split(",")[0];
        System.out.println(s1);

        String a="a";
        String split = a.split("\\+")[0].toString();
        System.out.println("======="+split);
        Student student = new Student();
        student.setUsername("a+b+c");
        String[] split1 = student.getUsername().split("\\+");
        for (String s : split1) {
            System.out.println("s======"+s);
        }
    }

}


