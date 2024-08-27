package com.example.testdemo.util;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
public class WebServiceClient {
    public static String  toWebService(String methed,String url, Object ... objects){
        //创建动态客户端
        JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
        Client client = factory.createClient(url);
//        Client client = factory.createClient("http://localhost:8080/demo/api?wsdl");
        // 需要密码的情况需要加上用户名和密码
        //client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        HTTPConduit conduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(2000);  //连接超时
        httpClientPolicy.setAllowChunking(false);    //取消块编码
        httpClientPolicy.setReceiveTimeout(120000);     //响应超时
        conduit.setClient(httpClientPolicy);
        //client.getOutInterceptors().addAll(interceptors);//设置拦截器
        try {
//            Object[] objects2 = new Object[]{"张三"};
            // invoke("方法名",参数1,参数2,参数3....);
            Object[] sayHellos  = client.invoke(methed, objects);
            System.out.println(methed+"：返回数据:" + sayHellos[0]);
            return sayHellos[0].toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
