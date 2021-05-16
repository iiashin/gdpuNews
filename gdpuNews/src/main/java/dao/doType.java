package dao;

import bean.newsTypes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class doType {
    static Connection connection=null;
    static PreparedStatement preparedStatement=null;
    static ResultSet resultSet=null;
    //获取所有类型
    public static List<newsTypes>selType() {
        connection = ConnectDB.SqlCon();
        String sql = "select * from types";
        PreparedStatement preparedStatement2=null;
        ResultSet resultSet2=null;
        List<newsTypes> list = new ArrayList<newsTypes>();
        try {
            preparedStatement2 = connection.prepareStatement(sql);
            resultSet2 = preparedStatement2.executeQuery();
            newsTypes t1 = null;
            while (resultSet2.next()) {
                t1 = new newsTypes();
                System.out.println("all"+resultSet2.getInt("id"));
                t1.setId(resultSet2.getInt("id"));
                t1.setTypeName(resultSet2.getString("typeName"));
                t1.setAbbr(resultSet2.getString("abbr"));
                list.add(t1);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet2);
        return null;
    }
    //根据缩写或者新闻名筛选新闻id
    public static int selIdByAbbrOrName(String str) {
        connection = ConnectDB.SqlCon();
        String sql = "select id from types where abbr=? or typeName=?";
        int id=0;
        try {
//            System.out.println(str);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,str);
            preparedStatement.setString(2,str);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
//                System.out.println(sql);
//                System.out.prntln("id"+id);
                id=resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return 0;
    }
     //根据名字筛选新闻id
//    public static int selIdByName(String name) {
//        connection = ConnectDB.SqlCon();
//        String sql = "select id from types where typeName=?";
//        int id=0;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1,name);
//            resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                id=resultSet.getInt("id");
//                System.out.println(id);
//            }
//            return id;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
//        return id;
//    }
}
