package Controller;

import dao.doNews;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//审核通过或者驳回的接口

@WebServlet("/delReviewNews")
public class delReviewNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isPublish=request.getParameter("isPublish");   //获取是否审核通过
        String newsId=request.getParameter("newsId");
        PrintWriter out=response.getWriter();
        //审核通过
        if(isPublish.equals("1")){
            //sel reviews表！！
            doNews.addNews1(doNews.selNewsByType(2,Integer.parseInt(newsId)).get(0));  //新闻表添加新闻
            doNews.delNews(2,Integer.parseInt(newsId));      //审核表删除新闻
            out.println("1");
        }else{
            //驳回
            doNews.delNews(2,Integer.parseInt(newsId));     //审核表删除新闻
            out.println("1");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
