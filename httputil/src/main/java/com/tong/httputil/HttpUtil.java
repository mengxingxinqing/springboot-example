package com.tong.httputil;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HttpUtil {
    private static CloseableHttpClient httpClient;
    private static final int socket_timeout = 1000;
    private static final int connect_timeout = 1000;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String get(String url) {
        CloseableHttpResponse response = null;
        String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
            result = getContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String post(String url, Map<String,String> params,Map<String,String> header){
        CloseableHttpResponse response = null;
        System.out.println(url);
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> formParams = new ArrayList<>(); //创建参数队列
            Set<Map.Entry<String, String>> paramSet = params.entrySet();

            if(paramSet.size() > 0){
                for(Map.Entry<String,String> entry : paramSet){
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socket_timeout).setConnectTimeout(connect_timeout).build();
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, HTTP.UTF_8));

            if(header!=null){
                for(Map.Entry<String,String> entry : header.entrySet()){
                    httpPost.setHeader(entry.getKey(),entry.getValue());
                }
            }
            response = httpClient.execute(httpPost);
            result = getContent(response);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (null != response) response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String addSignPost(String url,Map<String,String> param,Map<String,String> header,String seckey){
        header = HttpUtil.buildHeader(header,param,seckey);
        return HttpUtil.post(url,param,header);
    }

    public static String combineUrl(String url1,String url2){
        String sp = "/";
        if(url1.endsWith(sp)){
            return url1+url2;
        }
        return url1+sp+url2;
    }

//    往header中加入 signkey 加上trace
    public static Map<String,String> buildHeader(Map<String,String> header,Map<String,String> param,String seckey){
        if(header == null){
            header = new HashMap<>();
        }
        if(!header.containsKey("signkey")){
            header.put("signkey",SignKey.getSign(seckey,param,""));
        }
        if(!header.containsKey("traceid")){
            header.put("traceid",TraceIdUtil.getTraceId());
        }
        return header;
    }

    private static String getContent(CloseableHttpResponse response) throws IOException {
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            return "";
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer sb = new StringBuffer();
        String line = "";
        String NL = System.getProperty("line.separator");
        while ((line = in.readLine()) != null) {
            sb.append(line + NL);
        }
        in.close();
        return sb.toString();
    }

}
