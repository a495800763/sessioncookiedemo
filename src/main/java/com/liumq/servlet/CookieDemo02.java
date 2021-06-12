package com.liumq.servlet;

import org.apache.jasper.runtime.HttpJspBase;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通过同名cookie设置有效期为0，并且add到resp，
 * 将已存在的cookie目标立刻过期
 */
public class CookieDemo02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        //解决中文乱码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //创建于目标cookie 同名的cookie
        Cookie cookie = new Cookie("lastlogintime", "");
        // 设置有效期 为0 ，会直接过期
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        resp.getWriter().write("C1 中设置的cookie 已被删除");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
