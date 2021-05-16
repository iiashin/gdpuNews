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

//搜索新闻

@WebServlet("/searchMsg")
public class searchMsg extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] arr= new String[4];
        arr[0]=request.getParameter("id");
        arr[1]=request.getParameter("title");
        arr[2] =request.getParameter("type_id");
        arr[3]=request.getParameter("author");
        for (int i=0;i<4;i++){
            System.out.println(arr[i]);
            if(arr[i]==null)
                arr[i]="%";
            else
                arr[i]="%"+arr[i]+"%";
        }

        List<News> list= doNews.searchNews(arr[0],arr[1],arr[2],arr[3]);
        JSONArray jsonArray = JSONArray.fromObject(list);   //将集合转换成json数组
        PrintWriter out=response.getWriter();
        out.println(jsonArray);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
