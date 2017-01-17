package com.example;

import com.example.model.BaseJsonBean;
import com.example.model.DemoBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MyClass {

    public static void main(String args[]) {
        System.out.println("请输入数字选择：1.解析demo.json，2.解析demo2.json");
        Scanner in = new Scanner(System.in);
        int mode = Integer.valueOf(in.next());
        do {
            if (mode == 1 || mode == 2) {
            } else {
                System.out.println("输入格式不正确!");
                System.out.println("请输入数字选择：1.解析demo.json，2.解析demo2.json");
                mode = Integer.valueOf(in.next());
            }
        } while (!(mode == 1 || mode == 2));
        String filePath = MyClass.class.getClassLoader().getResource("").getPath();
        filePath = filePath.substring(1, filePath.length() - 1);
        String buildPath = filePath.substring(0, filePath.indexOf("build") + 5);
        String resourcesPath = buildPath + "/resources/main";
        if (mode == 1) { //解析demo.json
            File file = new File(resourcesPath + "/demo.json");
            parseJson(file2String(file));
        }
        if (mode == 2) {//解析demo2.json
            File file = new File(resourcesPath + "/demo2.json");
            parseJson(file2String(file));
        }
    }

    /**
     * file转字符串
     *
     * @param file
     * @return
     */
    private static String file2String(File file) {
        String str;
        try {
            FileInputStream in = new FileInputStream(file);
            // size  为字串的长度 ，这里一次性读完
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            str = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return str;
    }

    /**
     * 解析json
     * @param json
     */
    private static void parseJson(String json) {
        Gson gson = new GsonBuilder().registerTypeAdapter(BaseJsonBean.class,
                new BaseJsonBeanDeserializer<>(DemoBean.class)).create();
        BaseJsonBean<DemoBean> baseJsonBean = gson.fromJson(json,
                new TypeToken<BaseJsonBean<DemoBean>>() {
                }.getType());
        System.out.println(new Gson().toJson(baseJsonBean));
        ;
    }
}
