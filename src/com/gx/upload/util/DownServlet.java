package com.gx.upload.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载既可以是get请求也可以是post
 * @author Administrator
 *
 */
@WebServlet(name="DownServlet",urlPatterns="/DownServlet")
public class DownServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String name = req.getParameter("name");//获取要下载的文件名
    //第一步：设置响应类型
    resp.setContentType("application/force-download");//应用程序强制下载
    //第二读取文件
    String path = getServletContext().getRealPath("/up/"+name);
    InputStream in = new FileInputStream(path);
    //设置响应头，对文件进行url编码
    name = URLEncoder.encode(name, "UTF-8");
    resp.setHeader("Content-Disposition", "attachment;filename="+name);   
    resp.setContentLength(in.available());
    
    //第三步：老套路，开始copy
    OutputStream out = resp.getOutputStream();
    byte[] b = new byte[1024];
    int len = 0;
    while((len = in.read(b))!=-1){
      out.write(b, 0, len);
    }
    out.flush();
    out.close();
    in.close();
  }

}