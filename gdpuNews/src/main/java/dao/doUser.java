package dao;


import bean.News;
import bean.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class doUser {
    static Connection connection=null;
    static PreparedStatement preparedStatement=null;
    static ResultSet resultSet=null;

    //查找是否有符合要求的用户
    public static int selUserByNameAndPassword(String user_name, String password) {
        String sql = "select * from user where user_name=? and password=?";
        connection = ConnectDB.SqlCon();
        try {
            preparedStatement = connection.prepareStatement(sql);
//            System.out.println("1");
            preparedStatement.setString(1, user_name);
//            System.out.println("2");
            preparedStatement.setString(2, password);
//            System.out.println("3");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return 1;
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return 0;
    }

    //查找所有用户
    public static List<user> selUser() {
        String sql = "select * from user";
        connection = ConnectDB.SqlCon();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            List<user> list=new ArrayList<user>();
            user u1=null;
            while(resultSet.next()){
                u1=new user();
                u1.setUser_name(resultSet.getString("user_name"));
                u1.setPassword(resultSet.getString("password"));
                u1.setUser_id(resultSet.getInt("user_id"));
                list.add(u1);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return null;
    }
    //添加用户
    public static int addUser(String name,String password){
        connection = ConnectDB.SqlCon();
        int count1=0;
        String sql = "insert into user (user_name,password) values (?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            count1=preparedStatement.executeUpdate();
            System.out.println(count1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectDB.SqlClose(connection,preparedStatement,resultSet);
        return count1;
    }
    //删除用户
    public static int delUser(int id){
        connection = ConnectDB.SqlCon();
        int count1=0;
        String sql="delete from user where user_id=?";
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
}


