/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.dao;

import com.ltcity.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haoca
 */
public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql,String[] cols,Object...args){
        try {
            List<Object[]> lst = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0;i<cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                lst.add(vals);
            }
            rs.getStatement().getConnection().close();
            return lst;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
    public List<Object[]> getBangDiem(Integer maKH){
        String sql = "{CALL sp_BangDiem(?)}";
        String cols[] = {"MaNH","HoTen","Diem"};
        return getListOfArray(sql, cols, maKH);
    }
    
    public List<Object[]> getThongKeDiem(){
        String sql = "{CALL sp_ThongKeDiem}";
        String cols[] = {"ChuyenDe","SoHV","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(sql, cols);
    }
    
    public List<Object[]> getThongKeDoanhThu(Integer year){
        String sql = "{CALL sp_ThongKeDoanhThu(?)}";
        String cols[] = {"ChuyenDE","SoKH","SoHV","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(sql, cols, year);
    }
    
    public List<Object[]> getThongKeNguoiHoc(){
        String sql = "{CALL sp_ThongKeNguoiHoc}";
        String cols[] = {"Nam","SoLuong","DauTien","CuoiCung"};
        return getListOfArray(sql, cols);
    }
}
