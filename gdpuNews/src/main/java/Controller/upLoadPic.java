package Controller;

import bean.News;
import dao.doNews;
import dao.doType;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet("/upLoadPic")
public class upLoadPic extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //new一个news实例
        News news=new News();
        //获取当前系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        news.setDay(df.format(new Date()));     //发布时间
        news.setCount(0);                    //阅读量
        try {
            if (ServletFileUpload.isMultipartContent(request)) {   //是带文件上传的表单，form表单固定有了type=file
                //1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
                DiskFileItemFactory factory = new DiskFileItemFactory();

                //2. 创建ServletFileUpload对象，并设置上传文件的大小限制。
                ServletFileUpload sfu = new ServletFileUpload(factory);
                sfu.setSizeMax(10 * 1024 * 1024);//以byte为单位    不能超过10M  1024byte = 1kb  1024kb=1M 1024M = 1G
                sfu.setHeaderEncoding("utf-8");

                //3. 调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
                List<FileItem> fileItemList = sfu.parseRequest(request);
                Iterator<FileItem> fileItems = fileItemList.iterator();
                //4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
                while (fileItems.hasNext()) {
                    FileItem fileItem = fileItems.next();
                    //普通表单元素
                    if (fileItem.isFormField()) {
                        String name = fileItem.getFieldName();
                        String value = fileItem.getString("utf-8");//name对应的value值
                        //根据传回的name名
                        switch (name)
                        {
                            case "title":
                                news.setTitle(value);
                                break;
                            case "type":
                                news.setType_id(doType.selIdByAbbrOrName(value));
                                break;
                            case "author":
                                news.setAuthor(value);
                                break;
                            case "content":
                                news.setContent(value);
                                break;
                            default:
                                System.out.println("未知属性");
                        }
                    }
                    //当是文件元素时
                    else {
                        String fileName = fileItem.getName();//文件名称
                        if (fileName != null && !"".equals(fileName)) {    //filename原文件名
//                    	System.out.println("原文件名：" + fileName);//Koala.jpg
//
                            String suffix = fileName.substring(fileName.lastIndexOf('.'));   //suffix是扩展名  .jpg .png
//                      System.out.println("扩展名：" + suffix);//.jpg
//
                            //新文件名（唯一）
                            String newFileName = UUID.randomUUID().toString() + suffix;

                            File file = new File(request.getSession().getServletContext().getRealPath("/")+ newFileName);

                            news.setImg_address("http://localhost:8976/gdpuNews/"+ newFileName);  //写入数据库

                            //调用FileItem的write()方法，写入文件
                            fileItem.write(file);

//                            //6. 调用FileItem的delete()方法，删除临时文件
//                            fileItem.delete();

                        }

                    }
                }
              doNews.addNews(news);   //调用生成新新闻
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("http://localhost:8080/home");   //跳转主页
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
