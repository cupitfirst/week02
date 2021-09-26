package com.bh1ofp.week02;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;


public class HttpClientRequest {

    public String httpClientGet(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
                response = httpclient.execute(httpget);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            return String.valueOf(response.getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String httpClientPost(String url){
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        //伪装浏览器请求
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        CloseableHttpResponse response = null;
        String content = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
                //内容写入文件
                FileUtils.writeStringToFile(new File("oschina.html"), content, "UTF-8");
                System.out.println("内容长度："+content.length());
            }else if(response.getStatusLine().getStatusCode() == 302){
                Header header = response.getFirstHeader("location"); // 跳转的目标地址是在 HTTP-HEAD 中的
                url = header.getValue(); // 这就是跳转后的地址，再向这个地址发出新申请，以便得到跳转后的信息是啥。
                System.out.println(url);
                System.out.println(response.getStatusLine().getStatusCode() );


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }


    public static void main(String[] args){
        HttpClientRequest hcr = new HttpClientRequest();
        System.out.println(hcr.httpClientGet("https://www.bh1ofp.com"));
        System.out.println("------------------------------");
//        System.out.println(hcr.httpClientPost("https://p.yuetuvip.com/"));
    }

}
