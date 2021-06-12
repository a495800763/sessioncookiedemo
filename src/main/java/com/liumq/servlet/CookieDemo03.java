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
 * 中文数据怎么传递
 */
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = req.getCookies();

        PrintWriter out = resp.getWriter();
        // 判断cookie是否存在
        if (cookies != null) {
            // 如果已经存在cookie
            out.write("你上一次访问的时间是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie 的名字
                if (cookie.getName().equals("name")) {
                    String name = cookie.getValue();
                    System.out.println(name);
                    out.write("名称是:" + name);
                }
            }
        } else {
            out.write("这是您第一访问本站");
        }


        Cookie cookie = new Cookie("name", "柳梦琦");
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
