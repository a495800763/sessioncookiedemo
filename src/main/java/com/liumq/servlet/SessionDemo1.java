package com.liumq.servlet;

import com.liumq.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决乱码问题
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        // 得到session
        HttpSession session = req.getSession();


        //给session中存东西
        session.setAttribute("name", new Person("柳梦琦", 26));

        String id = session.getId();

        //判断session是不是新创建的
        boolean aNew = session.isNew();
        if (aNew) {
            resp.getWriter().write("session  创建成功 id:" + id);
        } else {
            resp.getWriter().write("session  已经存在了 id:" + id);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
