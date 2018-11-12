package com.gx.web.sendMess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpPostDemo {
    public static void main(String[] args) throws Exception {

        String host = "112.74.179.106:8080";
        String userCode = "�û���";    // �滻�������û���
        String userPwd = "����";      // �滻����������
        String numbers = "13800009999,13900009999";
        String msgContent = "��������";
        String charset = "GBK";

        StringBuffer urlSb = new StringBuffer();
        urlSb.append("http://").append(host).append("/Message.sv?method=sendMsg");
        urlSb.append("&userCode=").append(userCode);
        urlSb.append("&userPwd=").append(userPwd);
        urlSb.append("&numbers=").append(numbers);
        urlSb.append("&msgContent=").append(URLEncoder.encode(msgContent, charset));
        urlSb.append("&charset=").append(charset);

        String fullUrlStr = urlSb.toString();
        System.out.println("http��ʼ���ͣ�" + fullUrlStr);
        String result = "-1";
        try {
            int idx = fullUrlStr.indexOf("?");
            String str = fullUrlStr.substring(0, idx);
            String param = fullUrlStr.substring(idx + 1);
            URL url = new URL(str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            // Ĭ���� GET��ʽ
            con.setRequestMethod("POST");
            // Post ������ʹ�û���
            con.setUseCaches(false); 
            // ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded
            // ��˼��������urlencoded�������form����
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            
            con.connect();
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), charset);
            out.write(param);
            out.flush();
            out.close();

            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = reader.readLine();
            System.out.println("http���ͷ��ؽ����" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}