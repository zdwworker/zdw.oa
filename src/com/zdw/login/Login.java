package com.zdw.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import tool.DbUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/dept/login")
public class Login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();
        if(servletPath.equals("/dept/login")){
            doLogin(request,response);
        }
    }

    protected void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String f = request.getParameter("f");

        //把账号传到session对象中
        HttpSession session = request.getSession();
        session.setAttribute("account",account);
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Boolean ok=false;
        try {
            con= DbUtil.getConnection();
            /*select * from account where account='ww'and password='4444';*/
            String sql="select * from account where account=? and password=?;";
            ps=con.prepareStatement(sql);
            ps.setString(1,account);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                ok=true;
                }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtil.close(con,ps,rs);
        }
        if(ok){
            if("1".equals(f)){
                //表示用户勾选了十天免登录
                Cookie c1=new Cookie("account",account);
                Cookie c2=new Cookie("password",password);
                c1.setMaxAge(60*60*24*10);
                c2.setMaxAge(60*60*24*10);
                c1.setPath(request.getContextPath());
                c2.setPath(request.getContextPath());
                response.addCookie(c1);
                response.addCookie(c2);
            }
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            //密码错误，登录失败
            response.sendRedirect(request.getContextPath()+"/loginError.jsp");
        }
    }
    }

