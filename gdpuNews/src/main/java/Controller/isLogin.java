package Controller;

import dao.doNews;
import dao.doUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//登录判断
@WebServlet("/isLogin")
public class isLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name =request.getParameter("name");
        String password=request.getParameter("password");
        PrintWriter out=response.getWriter();
        int flag;
        if(name.equals("ashin")&&password.equals("ashin"))   //判断是不是管理员
            flag=2;
        else flag= doUser.selUserByNameAndPassword(name,password);
        out.println(flag);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
