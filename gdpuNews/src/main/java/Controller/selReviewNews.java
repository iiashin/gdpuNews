package Controller;

import bean.News;
import dao.doNews;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//获取未审核新闻

@WebServlet("/selReviewNews")
public class selReviewNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("begin");
        PrintWriter out=response.getWriter();
        List<News> list= doNews.selNewsAndTypeName(2);
        System.out.println(list);
        JSONArray jsonArray = JSONArray.fromObject(list);   //将集合转换成json数组
        out.println(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
