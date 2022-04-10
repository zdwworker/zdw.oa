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

@WebServlet("/welcome")
public class AvoidLogin extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取cookie
        Cookie[] cookies = request.getCookies();
        String account=null;
        String password=null;
        boolean ok=false;
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("account".equals(name)){
                    account = cookie.getValue();
                }
                if("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        }
        if(account!=null&&password!=null){
            //判断账号和密码是否正确
            Connection con=null;
            PreparedStatement ps=null;
            ResultSet rs=null;
            try {
                con= DbUtil.getConnection();
                String sql="select * from account where account=? and password=? ;";
                ps=con.prepareStatement(sql);
                ps.setString(1,account);
                ps.setString(2,password);
                rs= ps.executeQuery();
                if(rs.next()){
                    //说明账户密码正确
                    ok=true;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                DbUtil.close(con,ps,rs);
            }
        }
        if(ok){
            HttpSession session = request.getSession();
            session.setAttribute("account",account);
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }else {
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }
}
