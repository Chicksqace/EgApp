package com.example.administrator.egapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2022/11/18.
 */

public class NetUtil {
    public static String httpGet(String webUrl){
        //用于拼接字符串
        StringBuilder sb = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(webUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader streamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(streamReader);
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    sb.append(line);
                }
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //资源释放
            if (httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if (bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
}
