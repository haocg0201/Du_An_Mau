/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.dao;

import com.ltcity.entity.HocVien;
import com.ltcity.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haoca
 */
public class HocVienDAO extends EduSystemDAO<HocVien, Integer> {

    final String INSERT_SQL = "INSERT [dbo].[HocVien] ( [MaKH], [MaNH], [Diem]) VALUES ( ?, ?, ?)";
    final String UPDATE_SQL = "UPDATE HocVien SET MaKH = ?, MaNH = ?, Diem = ? where MaHV = ?";
    final String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM HocVien";
    final String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV = ?";
    final String SELECT_BY_MaKH_SQL = "SELECT * FROM HocVien WHERE MaKH = ?";
    
    @Override
    public void insert(HocVien entity) {
        JdbcHelper.UpDeInsert(INSERT_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.UpDeInsert(UPDATE_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem(), entity.getMaHV());
    }

    @Override
    public void delete(Integer id) {
        JdbcHelper.UpDeInsert(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> lst = selectBySql(SELECT_BY_ID_SQL, id);
        if(lst.isEmpty()){
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> lst = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()) {
                HocVien hv = new HocVien();
                hv.setMaHV(rs.getInt(1));
                hv.setMaKH(rs.getInt(2));
                hv.setMaNH(rs.getString(3));
                hv.setDiem(rs.getFloat(4));
                lst.add(hv);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return lst;
    }
    
    public List<HocVien> selectByKhoaHoc(int maKH) {
        return selectBySql(SELECT_BY_MaKH_SQL,maKH);
    }
    
}
