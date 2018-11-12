package com.gx.web.sendMess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpDemo {
    public static void main(String[] args) throws Exception {

        String host = "112.74.179.106:8080";
        String userCode = "chuangmai";
        String userPwd = "2018cm";
        String numbers = "13222100055,13585835040";
        String msgContent = "有想过您的孩子也许是下一位乔布斯，比尔盖茨或扎克伯格吗？那请为您的孩子提供更开阔的思维想象力和创造力，来免费体验我们的妙小程试听课程吧！\r\n" + 
        		"http://cm.anlai.com:8080/study/mxc";
        String charset = "GBK";

        StringBuffer urlSb = new StringBuffer();
        urlSb.append("http://").append(host).append("/Message.sv?method=sendMsg");
        urlSb.append("&userCode=").append(userCode);
        urlSb.append("&userPwd=").append(userPwd);
        urlSb.append("&numbers=").append(numbers);
        urlSb.append("&msgContent=").append(URLEncoder.encode(msgContent, charset));
        urlSb.append("&charset=").append(charset);

        String fullUrlStr = urlSb.toString();
        System.out.println("http开始发送：" + fullUrlStr);
        String result = "-1";
        try {
            int idx = fullUrlStr.indexOf("?");
            String str = fullUrlStr.substring(0, idx);
            String param = fullUrlStr.substring(idx + 1);
            URL url = new URL(str);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), charset);
            out.write(param);
            out.flush();
            out.close();

            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = reader.readLine();
            System.out.println("http发送返回结果：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}