package com.liumq.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 保存用户上一次访问的时间
 */
public class CookieDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();
        //Cookie,服务端从客户端获取，从req中拿
        //cookies数组，说明数组可以有多个
        Cookie[] cookies = req.getCookies();
        // 判断cookie是否存在
        if (cookies != null) {
            // 如果已经存在cookie
            out.write("你上一次访问的时间是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie 的名字
                if (cookie.getName().equals("lastlogintime")) {
                    String value = cookie.getValue();
                    long l = Long.parseLong(value);
                    Date date = new Date(l);
                    out.write(date.toLocaleString());
                }
            }
        } else {
            out.write("这是您第一访问本站");
        }

        //服务器给客户端响应一个cookie
        Cookie cookie = new Cookie("lastlogintime", System.currentTimeMillis() + "");
        //设置cookie 的有效期为一天
        cookie.setMaxAge(24 * 60 * 60);
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
