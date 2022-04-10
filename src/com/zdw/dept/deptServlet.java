package com.zdw.dept;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.DbUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/dept/list",  "/dept/detail", "/dept/alter","/dept/add",
        "/dept/del", "/dept/modify"})
public class deptServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session!=null && session.getAttribute("account")!=null){
            //表示已经登录过
            String servletPath = request.getServletPath();
            //System.out.println(servletPath);   /dept/list
            if(servletPath.equals("/dept/list")){
                doList(request,response);
            }else if(servletPath.equals("/dept/detail")){
                doDtail(request,response);
                request.getRequestDispatcher("/detail.jsp").forward(request,response);
            }else if(servletPath.equals("/dept/del")){
                doDel(request,response);
            }else if(servletPath.equals("/dept/alter")){
                doAlter(request,response);
            }else if(servletPath.equals("/dept/add")){
                doAdd(request,response);
            }else if(servletPath.equals("/dept/modify")){
                doDtail(request,response);
                request.getRequestDispatcher("/alter.jsp").forward(request,response);
            }
        }else{
            //没有登录过
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deptno= request.getParameter("no");
        String dname = request.getParameter("name");
        String loc = request.getParameter("add");
        int i=0;
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=DbUtil.getConnection();
            String sql="insert into dept(deptno,dname,loc) values(?,?,?);" ;

            ps=con.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            i=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtil.close(con,ps,rs);
        }
        if(i==0){
            //修改失败
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }else{
            //修改成功
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }

    private void doAlter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deptno = request.getParameter("no");
        String dname = request.getParameter("name");
        String loc = request.getParameter("add");
        int i=0;
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=DbUtil.getConnection();
            String sql="update dept set dname=?,loc=? where deptno=?" ;
            ps=con.prepareStatement(sql);
            ps.setString(1,dname);
            ps.setString(2,loc);
            ps.setString(3,deptno);
            i=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtil.close(con,ps,rs);
        }
        if(i==0){
            //修改失败
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }else{
            //修改成功
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
        int i=0;
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=DbUtil.getConnection();
            String sql="delete from dept where deptno=?";
            ps=con.prepareStatement(sql);
            ps.setString(1,deptno);
            i=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtil.close(con,ps,rs);
        }
        if(i==0){
            //删除失败
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }else{
            //删除成功
            response.sendRedirect(request.getContextPath()+"/dept/list");
        }
    }

    private void doDtail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String deptno = request.getParameter("deptno");
        List<Dept> deptList=new ArrayList<>();
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=DbUtil.getConnection();
            String sql="select dname,loc from dept where deptno=?;";
            ps=con.prepareStatement(sql);
            ps.setString(1,deptno);
            rs=ps.executeQuery();
            if(rs.next()){
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                Dept dept=new Dept();
                dept.setEmptno(deptno);
                dept.setEname(dname);
                dept.setLoc(loc);
                deptList.add(dept);
            }
            request.setAttribute("deptList",deptList);
            //转发到JSP页面
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtil.close(con,ps,rs);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String contextPath = request.getContextPath();
        //连接数据库
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        //List<Dept> list=new ArrayList();
        List<Dept> list = new ArrayList<>();   //老师的代码
        try {
            con=DbUtil.getConnection();
            String sql="select deptno,dname,loc from dept; " ;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                String dept = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
               Dept dept1=new Dept();
               dept1.setEmptno(dept);
               dept1.setEname(dname);
               dept1.setLoc(loc);
               list.add(dept1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtil.close(con,ps,rs);
        }

        request.setAttribute("list",list);
        //转发到list.jsp中
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }
}
