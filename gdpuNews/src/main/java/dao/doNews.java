package dao;

import bean.News;
import bean.newsTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class doNews {
    static Connection connection=null;
    static PreparedStatement preparedStatement=null;
    static ResultSet resultSet=null;

    public static News setAll(News n1) throws SQLException {
//        n1 = new News();
        n1.setNewsId(resultSet.getInt("newsId"));
        n1.setTitle(resultSet.getString("title"));
        n1.setAuthor(resultSet.getString("author"));
        n1.setDay(resultSet.getString("day"));
        n1.setCount(resultSet.getInt("count"));
        n1.setContent(resultSet.getString("content"));
        n1.setType_id(resultSet.getInt("type_id"));
        n1.setImg_address(resultSet.getString("img_address"));
        n1.setTypeName(resultSet.getString("typeName"));
        return n1;
    }

    //筛选新闻或者待审核 包括类别名
    public static List<News> selNewsAndTypeName(int i) {
        connection = ConnectDB.SqlCon();
        String sql;
        if(i==1)
            sql = "select news.*,types.* from news left join types on news.type_id=types.id";
        else
            sql = "select review.*,types.* from review left join types on review.type_id=types.id";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<News> list = new ArrayList<News>();
            News n1 = null;
//            System.out.println(resultSet.getString('*'));
            while (resultSet.next()) {
                n1 = new News();
//                n1.setNewsId(resultSet.getInt("newsId"));
//                n1.setTitle(resultSet.getString("title"));
//                n1.setAuthor(resultSet.getString("author"));
//                n1.setDay(resultSet.getString("day"));
//                n1.setCount(resultSet.getInt("count"));
//                n1.setContent(resultSet.getString("content"));
//                n1.setType_id(resultSet.getInt("type_id"));
//                n1.setImg_address(resultSet.getString("img_address"));
//                n1.setTypeName(resultSet.getString("typeName"));
                setAll(n1);
                list.add(n1);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return null;
    }
    //根据类别或者新闻id筛选新闻或者待审核
    public static List<News> selNewsByType(int i,int id) {
        connection = ConnectDB.SqlCon();
        String sql;
        if(i==1)
            sql = "select news.*,types.* from news left join types on news.type_id=types.id where news.type_id=? or news.newsId=?";
        else
            sql="select * from review where type_id=? or newsId=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,id);
            resultSet = preparedStatement.executeQuery();
            List<News> list = new ArrayList<News>();
            News n1 = null;
            while (resultSet.next()) {
                n1 = new News();
                n1.setNewsId(resultSet.getInt("newsId"));
                n1.setTitle(resultSet.getString("title"));
                n1.setAuthor(resultSet.getString("author"));
                n1.setDay(resultSet.getString("day"));
                n1.setCount(resultSet.getInt("count"));
                n1.setContent(resultSet.getString("content"));
                n1.setImg_address(resultSet.getString("img_address"));
                n1.setType_id(resultSet.getInt("type_id"));
                if(i==1){
                    n1.setTypeName(resultSet.getString("typeName"));
                    n1.setAbbr(resultSet.getString("abbr"));
                }
//                n1.setNewsId(id);
                list.add(n1);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return null;
    }
    //添加新闻
    public static int addNews(News news){
        connection = ConnectDB.SqlCon();
        int count1=0;
        String sql = "insert into review (title,author,day,count,content,type_id,img_address) values (?,?,?,?,?,?,?)";
        System.out.println(sql);
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,news.getTitle());
            preparedStatement.setString(2,news.getAuthor());
            preparedStatement.setString(3,news.getDay());
            preparedStatement.setInt(4,news.getCount());
            preparedStatement.setString(5,news.getContent());
            preparedStatement.setInt(6,news.getType_id());
            preparedStatement.setString(7,news.getImg_address());
            count1=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return count1;
    }
    //添加新闻到新闻表
    public static int addNews1(News news){
        connection = ConnectDB.SqlCon();
        int count1=0;
        String sql = "insert into news (title,author,day,count,content,type_id,img_address) values (?,?,?,?,?,?,?)";
        System.out.println(sql);
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,news.getTitle());
            preparedStatement.setString(2,news.getAuthor());
            preparedStatement.setString(3,news.getDay());
            preparedStatement.setInt(4,news.getCount());
            preparedStatement.setString(5,news.getContent());
            preparedStatement.setInt(6,news.getType_id());
            preparedStatement.setString(7,news.getImg_address());
            count1=preparedStatement.executeUpdate();
            System.out.println(count1+"change！！！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return count1;
    }
    //搜索已审核信息
    public static List<News> searchNews(String id,String title,String type_id,String author) {
        connection = ConnectDB.SqlCon();
        String sql = "select news.*,types.* from news left join types on news.type_id=types.id " +
                "where news.newsId like ? and news.title like ? and news.type_id like ? and author LIKE ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.setString(2,title);
            preparedStatement.setString(3,type_id);
            preparedStatement.setString(4,author);
            resultSet = preparedStatement.executeQuery();
            List<News> list = new ArrayList<News>();
            News n1 = null;
//            System.out.println(resultSet.getString('*'));
            while (resultSet.next()) {
                n1 = new News();
//                n1.setNewsId(resultSet.getInt("newsId"));
//                n1.setTitle(resultSet.getString("title"));
//                n1.setAuthor(resultSet.getString("author"));
//                n1.setDay(resultSet.getString("day"));
//                n1.setCount(resultSet.getInt("count"));
//                n1.setContent(resultSet.getString("content"));
//                n1.setType_id(resultSet.getInt("type_id"));
//                n1.setImg_address(resultSet.getString("img_address"));
//                n1.setTypeName(resultSet.getString("typeName"));
                setAll(n1);
                list.add(n1);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return null;
    }

//    删除已审核或未审核的新闻
    public static int delNews(int i,int id){
        connection = ConnectDB.SqlCon();
        int count1=0;
        String sql;
        if(i==1)
            sql="delete from news where newsId=?";
        else
            sql="delete from review where newsId=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            count1=preparedStatement.executeUpdate();
            System.out.println(count1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return count1;
    }
    //更新数据库
    public static void updateNews(int id){
        connection = ConnectDB.SqlCon();
        String sql="update news set count=count+1 where newsId=?";
        System.out.println(sql);
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement);
    }
    //搜索新闻
    public static List<News> selNewsByKey(String key){
        connection = ConnectDB.SqlCon();
        String sql="select * from news where title like ?";
        List<News> list =null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,key);
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<News>();
            News n1 = null;
            while (resultSet.next()) {
                n1 = new News();
                n1.setNewsId(resultSet.getInt("newsId"));
                n1.setTitle(resultSet.getString("title"));
                n1.setDay(resultSet.getString("day"));
                list.add(n1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement);
        return list;
    }
}
