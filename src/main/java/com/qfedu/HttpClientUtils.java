package com.qfedu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * @auther: Zhangbo
 * @date: 2020/5/12 2:35
 * @Description:
 */
public class HttpClientUtils {

    /**
     * 获取指定baseURL链接所返回的字符串数据
     * @param baseURL
     * @return
     */
    public static String getDataFromURL(String baseURL){
        try {
            //  根据url创建一个URL对象
            URL url = new URL(baseURL);

            //  通过url对象的openConnection()方法并强转得到一个HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  设置请求的超时时间
            conn.setConnectTimeout(5000);
            //  设置请求的请求方式
            conn.setRequestMethod("GET");

            //  设置请求方式为输入请求
            conn.setDoInput(true);

            //  得到请求返回的状态码
            int code = conn.getResponseCode();

            //  如果状态码为HTTP_OK(200)
            if(code == HttpURLConnection.HTTP_OK){

                //  通过链接对象的getInputStream（）方法得到一个输入流对象
                InputStream is = conn.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] b = new byte[1024];

                int len;

                while ((len = is.read(b)) != -1){
                    baos.write(b, 0, len);
                }

                return new String(baos.toByteArray());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据指定的baseURL来获取该url对应的字节数组的值
     *
     * @param baseURL   要获取数据的url
     * @param type      请求类型 GET活着POST
     * @return 指定baseURL所对应的字节数组值
     */
    public static byte[] getByteDataByURL(String baseURL, String type){
        try {
            URL url = new URL(baseURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(5000);

            if("GET".equalsIgnoreCase(type)){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
            }

            conn.setDoInput(true);

            int code = conn.getResponseCode();

            if(code == HttpURLConnection.HTTP_OK){
                InputStream is = conn.getInputStream();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                int len;

                byte[] data = new byte[1024];

                while ((len = is.read(data)) != -1){
                    baos.write(data, 0, len);
                }

                return baos.toByteArray();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
