package com.qfedu;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther: Zhangbo
 * @date: 2020/5/12 2:38
 * @Description:
 */
public class TestHttpClientUtils {

    //测试获取json
    @Test
    public void testGetData(){
        String data = HttpClientUtils.getDataFromURL("http://127.0.0.1:8080/api/abc");

        System.out.println(data);
    }

    /**
     *
     * http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4
     * https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4
     */
    //测试获取文件
    @Test
    public void testGetByteData(){

        //byte[] datas = HttpClientUtils.getByteDataByURL("http://t9.baidu.com/it/u=1307125826,3433407105&fm=79&app=86&f=JPEG?w=5760&h=3240", "get");
        byte[] datas = HttpClientUtils.getByteDataByURL("http://q9jappp2m.bkt.clouddn.com/1.mp4", "get");

        FileOutputStream fos = null;

        try {
            //fos = new FileOutputStream("girl.jpg");
            fos = new FileOutputStream("girl.mp4");

            fos.write(datas);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fos != null){
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("download success.");
    }

}
