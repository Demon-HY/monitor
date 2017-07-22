package com.monitor.baseservice.utils.threadsafety;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 线程安全的 HTTP 请求
 *
 * Created by Administrator on 2017/7/22 0022.
 */
public class HttpRequestSimple {

    Logger log = LogManager.getLogger(HttpRequestSimple.class);

    private static HttpRequestSimple instance;

    public static HttpRequestSimple getInstance() {
        if (instance == null) {
            instance = new HttpRequestSimple();
        }
        return instance;
    }

    private HttpRequestSimple() {
    }

    /**
     * 功能描述：发送序列化对象
     *
     * @param url
     * @param inputObj
     * @return
     */
    public Object postSendHttp(String url, Object inputObj) {
        long start = System.currentTimeMillis();
        if (url == null || "".equals(url)) {
            log.info("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/octet-stream");
        java.io.ByteArrayOutputStream bOut = new java.io.ByteArrayOutputStream(1024);
        java.io.InputStream bInput;
        java.io.ObjectOutputStream out;
        Serializable returnObj;
        try {
            out = new java.io.ObjectOutputStream(bOut);
            out.writeObject(inputObj);
            out.flush();
            out.close();

            bInput = new java.io.ByteArrayInputStream(bOut.toByteArray());
            InputStreamEntity inputStreamEntity = new InputStreamEntity(bInput, bOut.size(), null);
            inputStreamEntity.setContentEncoding(new BasicHeader(
                    HTTP.CONTENT_ENCODING, "UTF-8"));
            // 设置请求主体
            post.setEntity(inputStreamEntity);
            // 发起请求
            HttpResponse resp = httpClient.execute(post);
            log.info("请求[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {

                // 响应分析
                HttpEntity entity = resp.getEntity();

                java.io.InputStream in = entity.getContent();
                java.io.ObjectInputStream oInput = new java.io.ObjectInputStream(
                        in);
                returnObj = (Serializable) oInput.readObject();
                oInput.close();

                long end = System.currentTimeMillis();
                log.info("请求[" + url + "]消耗时间 " + (end - start) + "毫秒");
                return returnObj;
            }
            return null;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    /**
     * 发送 POST 请求
     * @param url
     * @param body
     * @return
     */
    public String postSendHttp(String url, String body) {
        long start = System.currentTimeMillis();
        if (url == null || "".equals(url)) {
            log.info("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        try {
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            stringEntity.setContentEncoding(new BasicHeader(
                    HTTP.CONTENT_ENCODING, "UTF-8"));
            // 设置请求主体
            post.setEntity(stringEntity);
            // 发起请求
            HttpResponse resp = httpClient.execute(post);
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        entity.getContent(), "UTF-8"));
                StringBuilder responseString = new StringBuilder();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }
                long end = System.currentTimeMillis();
                log.info("请求[" + url + "]消耗时间 " + (end - start) + "毫秒");
                return responseString.toString();
            }
            return null;
        } catch (Exception e) {
            log.error("HttpRequestSimple.postSendHttp send failed.", e);
            return null;
        }
    }

    public String getSendHttp(String url) {
        if (url == null || "".equals(url)) {
            log.info("request url is empty.");
            return null;
        }
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpGet get = new HttpGet(url);
        get.setHeader("Content-Type", "text/html;charset=UTF-8");
        try {
            // 发起请求
            HttpResponse resp = httpClient.execute(get);
            log.info("请求[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        entity.getContent()));
                StringBuilder responseString = new StringBuilder();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }

                return responseString.toString();
            }
            return null;
        } catch (Exception e) {
            log.error("HttpRequestSimple.getSendHttp send failed.", e);
            return null;
        }
    }

    public String postPramaList(String url, NameValuePair[] list) {
        List<NameValuePair> nvList = new ArrayList<>();
        nvList.addAll(Arrays.asList(list));
        return postPramaList(nvList, url);
    }

    private String postPramaList(List<NameValuePair> list, String url) {
        HttpClient httpClient = CustomHttpClient.GetHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type",
                "application/x-www-form-urlencoded;charset=utf-8");
        BufferedReader br = null;
        try {
            UrlEncodedFormEntity formEntiry = new UrlEncodedFormEntity(list,
                    HTTP.UTF_8);
            // 设置请求参数
            post.setEntity(formEntiry);
            // 发起请求
            HttpResponse resp = httpClient.execute(post);
            log.info("请求[" + url + "] " + resp.getStatusLine());
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();
                br = new BufferedReader(new InputStreamReader(entity
                        .getContent(), "UTF-8"));
                StringBuilder responseString = new StringBuilder();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }
                return responseString.toString();
            } else {
                log.info("retcode:" + ret);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
