/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author haoca
 */
public class JdbcHelper {
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost:1433;database=LTCITY";
    private static String un = "sa";
    private static String pw = "12345";
    /*
    * nạp driver nè ))
    */
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    
 /**
 * Xây dựng PreparedStatement
 * @param sql là câu lệnh SQL chứa có thể chứa tham số. Nó có thể là một lời gọi thủ tục lưu
 * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
 * @return PreparedStatement tạo được
 * @throws java.sql.SQLException lỗi sai cú pháp
 */
    public static PreparedStatement getSttm(String sql,Object... args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, un, pw);
        PreparedStatement ps = null;
        if(sql.trim().startsWith("{")){
            ps = connection.prepareCall(sql);
        }else{
            ps = connection.prepareStatement(sql);
        }
        for(int i = 0;i<args.length;i++){
            ps.setObject(i+1, args[i]);
        }
        return ps;
    }
    
 /**
 * Thực hiện câu lệnh SQL thao tác (INSERT, UPDATE, DELETE) hoặc thủ tục lưu thao tác dữ liệu
 * @param sql là câu lệnh SQL chứa có thể chứa tham số. Nó có thể là một lời gọi thủ tục lưu
 * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql * 
 */
    public static int UpDeInsert(String sql,Object... args){
        try {
            PreparedStatement stmt = getSttm(sql, args);
            try {
                return stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
 /**
 * Thực hiện câu lệnh SQL truy vấn (SELECT) hoặc thủ tục lưu truy vấn dữ liệu
 * @param sql là câu lệnh SQL chứa có thể chứa tham số. Nó có thể là một lời gọi thủ tục lưu
 * @param args là danh sách các giá trị được cung cấp cho các tham số trong câu lệnh sql
 */
    
    public static ResultSet query(String sql,Object... args){
        try {
            PreparedStatement pstm = getSttm(sql, args);
            return pstm.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }        
    }
    
    /*
    * trả về một đối tượng bất kỳ 
    */
    public static Object value(String sql,Object... args){
        try {
            ResultSet rs = query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


