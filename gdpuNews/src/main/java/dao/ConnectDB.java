package dao;

import java.sql.*;

public class ConnectDB {
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection SqlCon() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/webnews?serverTimezone=Asia/Shanghai","root","root");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public static void SqlClose(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        if(pstmt!=null) {
            try {
                pstmt.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
    }
    public static void SqlClose(Connection conn,PreparedStatement pstmt) {
        if(pstmt!=null) {
            try {
                pstmt.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
    }
    public static void SqlClose(Connection conn,Statement statement,ResultSet rs) {
        if(statement!=null) {
            try {
                statement.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
        if(rs!=null) {
            try {
                rs.close();
            }
            catch(SQLException e){
                e.getStackTrace();
            }
        }
    }
}

