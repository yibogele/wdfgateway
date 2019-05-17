package com.example.wdfgatewayservice.httpclient;

import com.example.wdfgatewayservice.config.ServerConfig;
import com.example.wdfgatewayservice.constant.Constants;
import com.example.wdfgatewayservice.constant.Protocol;
import com.example.wdfgatewayservice.model.CardNumRequestData;
import com.example.wdfgatewayservice.model.DumpRecordRequestData;
import com.example.wdfgatewayservice.model.ShakeHandRequestData;
import com.example.wdfgatewayservice.util.JsonUtil;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Will Fan
 * Description:
 * Date: Created in 11:35 2018/8/16
 * Modified By:
 */
public class HttpBizClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpBizClient.class);

    private HttpBizClient(){}



    /**
     * 向业务平台请求数据
     * @param shakeHandRequestData
     * @return
     */
    public static String getShakeHands(ShakeHandRequestData shakeHandRequestData){

        String strUrlParam = HttpParamBuilder.getByProtocol(Protocol.SHAKE_HAND)
                .getParamFrom(shakeHandRequestData);

        String result = "";
        try {
            // GET, 只穿devid
            if(Constants.Test.isTest)
            {
                String strUrlTest = ServerConfig.getInstance().getShakeHandsURL()
                        + Constants.Test.TEST_DEV_ID;
                result = HttpClientUtil.get(strUrlTest);
            }else
            {

                String strUrl = ServerConfig.getInstance().getShakeHandsURL() + strUrlParam;

                result = HttpClientUtil.get(strUrl);

                // 不使用POST
//            result = HttpClientUtil.postJson(strUrl, strUrlParam);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return result;
    }

    /**
     *
     * @param dumpRecordRequestData
     * @return
     */
    public static String getDumpRecord(DumpRecordRequestData dumpRecordRequestData){
        String strUrlParam = HttpParamBuilder.getByProtocol(Protocol.DUMP_RECORD)
                .getParamFrom(dumpRecordRequestData);

        logger.info("UrlParam: {}", strUrlParam);
        String result = "";

        try{
            // POST
            if(Constants.Test.isTest)
            {
                // test
                String strUrlTest = ServerConfig.getInstance().getDumpRecordURL()
                        + Constants.Test.TEST_DEV_ID;
                Map<String, String> map = new HashMap<>();
                map.put("machineCode", Constants.Test.TEST_DEV_ID);
                map.put("cardNumber", Constants.Test.TEST_CARD_NUM);
                map.put("weight0", "0");
                String strUrlParamTest = JsonUtil.obj2json(map);

                result = HttpClientUtil.postJson(strUrlTest, strUrlParamTest);

            }else
            {

                String strUrl = ServerConfig.getInstance().getDumpRecordURL() + dumpRecordRequestData.getDeviceId();

                result = HttpClientUtil.postJson(strUrl, strUrlParam);
//            result = httpPostWithJson(strUrl, strUrlParam);

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    /**
     *
     * @param cardNumRequestData
     * @return
     */
    public static String getCardNum(CardNumRequestData cardNumRequestData){
        String strUrlParam = HttpParamBuilder.getByProtocol(Protocol.VALIDATE_CARDNUM)
                .getParamFrom(cardNumRequestData);

        String result = "";

        try{
            // GET
            if(Constants.Test.isTest)
            {
                String strUrlTest = ServerConfig.getInstance().getCardNumURL() +
//                        Constants.Test.TEST_DEV_ID;
                        Constants.Test.TEST_CARD_NUM;
                result = HttpClientUtil.get(strUrlTest);

            }else
            {

                String strUrl = ServerConfig.getInstance().getCardNumURL()+strUrlParam;
                result = HttpClientUtil.get(strUrl);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    private static String httpPostWithJson(String url, String json) {
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json,"utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost,responseHandler); //调接口获取返回值时，必须用此方法
	       CloseableHttpResponse httpResonse = httpClient.execute(httpPost);
	       int statusCode = httpResonse.getStatusLine().getStatusCode();
	       if(statusCode!=200)
	       {
	        	System.out.println("请求发送失败，失败的返回参数为："+httpResonse.getStatusLine());
	        	returnValue = httpResonse.getStatusLine().toString();
	       }


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }
}
