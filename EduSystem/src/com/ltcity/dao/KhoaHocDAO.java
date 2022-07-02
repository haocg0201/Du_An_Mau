/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.dao;

import com.ltcity.entity.KhoaHoc;
import com.ltcity.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haoca
 */
public class KhoaHocDAO extends EduSystemDAO<KhoaHoc, Integer> {

    final String INSERT_SQL = "INSERT [dbo].[KhoaHoc] ( [MaCD], [HocPhi], [ThoiLuong], [NgayKG], [GhiChu], [MaNV]) VALUES ( ?, ?, ?, ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD = ?, HocPhi = ?, ThoiLuong = ? ,NgayKG = ?,GhiChu = ?,MaNV = ?,NgayTao = ? where MaKH = ?";
    final String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    final String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH = ?";
    final String SELECT_BY_MACD_SQL = "SELECT * FROM KhoaHoc WHERE MaCD = ?";
    
    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.UpDeInsert(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.UpDeInsert(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(),entity.getNgayTao(), entity.getMaKH());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.UpDeInsert(DELETE_SQL, id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> lst = selectBySql(SELECT_BY_ID_SQL,id);
        if(lst.isEmpty()){
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> lst = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt(1));
                kh.setMaCD(rs.getString(2));
                kh.setHocPhi(rs.getFloat(3));
                kh.setThoiLuong(rs.getInt(4));
                kh.setNgayKG(rs.getDate(5));
                kh.setGhiChu(rs.getString(6));
                kh.setMaNV(rs.getString(7));
                lst.add(kh);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lst;
    }

    public List<KhoaHoc> selectKhoaHocByChuyenDe(String maCD) {
        return selectBySql(SELECT_BY_MACD_SQL,maCD);
    }
    
    public List<Integer> getYear() {
        String sql = "SELECT DISTINCT year(NgayKG) Year from KhoaHoc ORDER BY Year DESC";
        List<Integer> lst = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql);
            while(rs.next()){
                lst.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return lst;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
