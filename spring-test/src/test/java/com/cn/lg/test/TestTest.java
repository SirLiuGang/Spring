package com.cn.lg.test;

import org.junit.Test;

import java.io.*;

/**
 * @author: 刘钢
 * @Date: 2019/4/9 22:45
 * @Description:
 */
public class TestTest {

    @Test
    public void test() {
        // 本地
        // 本地修改
//        JSONObject jsonObject = new JSONObject(true);
//        jsonObject.put("a", "1");
//        jsonObject.toJSONString();
//        System.out.println("value : " + jsonObject.toJSONString());
//
//
//        if (Objects.equals(1,2)) {
//            System.out.println("1");
//        } else {
//            System.out.println("2");
//        }
//
//        Map<String, String> map = new HashMap<>();
//        map.put("1","1");
//        for(Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//        for(int i = 0; i < 20; i++) {
//            System.out.println(ThreadLocalRandom.current().nextInt());
//        }

        String fileName = "C:\\Users\\liugang\\Desktop\\新建文本文档 (2).txt";
        try(InputStream  inputstream  = new  FileInputStream(fileName);
            InputStreamReader isr = new InputStreamReader(inputstream);
            BufferedReader bufr = new BufferedReader(isr)) {

            String line;
            while((line = bufr.readLine())!=null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
