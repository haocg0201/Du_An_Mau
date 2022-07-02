/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.dao;

import com.ltcity.entity.NguoiHoc;
import com.ltcity.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haoca
 */
public class NguoiHocDAO extends EduSystemDAO<NguoiHoc, String>{
    
    final String INSERT_SQL = "INSERT [dbo].[NguoiHoc] ([MaNH], [HoTen], [NgaySinh], [GioiTinh], [DienThoai], [Email], [GhiChu], [MaNV], [NgayDK]) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?,GhiChu = ?, MaNV = ?,NgayDk = ? where MANH = ?";
    final String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    final String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH = ?";
    final String SELECT_NOT_IN_COURSE_SQL = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
    final String SELECT_BY_NAME_SQL = "SELECT * FROM NguoiHoc WHERE HoTen like ?";

    
    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.UpDeInsert(INSERT_SQL, entity.getMaNH(),entity.getHoTen(),entity.getNgaySinh(),entity.getGioiTinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.UpDeInsert(UPDATE_SQL, entity.getHoTen(),entity.getNgaySinh(),entity.getGioiTinh(),entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK(),entity.getMaNH());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.UpDeInsert(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> lst = selectBySql(SELECT_BY_ID_SQL,id);
        if(lst.isEmpty()){
            return null;
        }
        return lst.get(0);       
    }

    @Override
    public List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> lst = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString(1));
                nh.setHoTen(rs.getString(2));
                nh.setNgaySinh(rs.getDate(3));
                nh.setGioiTinh(rs.getBoolean(4));
                nh.setDienThoai(rs.getString(5));
                nh.setEmail(rs.getString(6));
                nh.setGhiChu(rs.getString(7));
                nh.setMaNV(rs.getString(8));
                nh.setNgayDK(rs.getDate(9));
                lst.add(nh);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }
  
    public List<NguoiHoc> selectNotInCourse(int maKH,String keyW) {
        return selectBySql(SELECT_NOT_IN_COURSE_SQL,"%" + keyW + "%",maKH);
    }
    
    public List<NguoiHoc> selectByName(String name) {
        List<NguoiHoc> lst = selectBySql(SELECT_BY_NAME_SQL,"%" + name + "%");
        if(lst.isEmpty()){
            return null;
        }
        return lst;       
    }
}
