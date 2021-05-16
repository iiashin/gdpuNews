package Controller;

import bean.News;
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

//通过新闻种类或者新闻开头关键词获取新闻

@WebServlet("/selNewsByAbbr")
public class selNewsByAbbr extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String abbr=request.getParameter("abbr");    //获取新闻种类
        String key=request.getParameter("key");     //搜索关键字
        PrintWriter out=response.getWriter();
        if(abbr!=null){
//            System.out.println(abbr);
            int id=doType.selIdByAbbrOrName(abbr);
            List<News> list= doNews.selNewsByType(1,id);
            JSONArray jsonArray = JSONArray.fromObject(list);   //将集合转换成json数组
            out.println(jsonArray);
        }else{       //当是搜索功能时
//            System.out.println(key);
            List<News> list= doNews.selNewsByKey("%"+key+"%");
            JSONArray jsonArray = JSONArray.fromObject(list);   //将集合转换成json数组
            out.println(jsonArray);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
