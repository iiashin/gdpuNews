package Controller;

import bean.News;
import bean.newsTypes;
import dao.doNews;
import dao.doType;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/newsServlet")
public class newsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newsId=request.getParameter("newsId");
        PrintWriter out=response.getWriter();
        JSONArray jsonArray = null;
        if(newsId==null){
            List<News> list= doNews.selNewsAndTypeName(1);   //获取全部新闻内容
            jsonArray = JSONArray.fromObject(list);   //将集合转换成json数组
        }else{
            //更新阅读量
            doNews.updateNews(Integer.parseInt(newsId));
            List<News> list= doNews.selNewsByType(1,Integer.parseInt(newsId));   //展示新闻详细内容
            jsonArray = JSONArray.fromObject(list);
        }

        out.println(jsonArray);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
