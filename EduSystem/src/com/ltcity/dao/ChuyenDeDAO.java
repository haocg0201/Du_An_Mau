/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.dao;

import com.ltcity.entity.ChuyenDe;
import com.ltcity.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haoca
 */
public class ChuyenDeDAO extends EduSystemDAO<ChuyenDe, String>
{
    final String INSERT_SQL = "INSERT [dbo].[ChuyenDe] ([MaCD], [TenCD], [HocPhi], [ThoiLuong], [Hinh], [MoTa]) VALUES (?, ?, ?, ?,?,?)";
    final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?,Hinh = ?,MoTa = ? where MaCD = ?";
    final String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD = ?";
    final String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    final String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD = ?";
    
    @Override
    public void insert(ChuyenDe entity) {
        JdbcHelper.UpDeInsert(INSERT_SQL, entity.getMaCD(),entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.UpDeInsert(UPDATE_SQL,entity.getTenCD(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.UpDeInsert(DELETE_SQL, id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> lst = selectBySql(SELECT_BY_ID_SQL, id);
        if(lst.isEmpty()){
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> lst = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString(1));
                cd.setTenCD(rs.getString(2));
                cd.setHocPhi(rs.getFloat(3));
                cd.setThoiLuong(rs.getInt(4));
                cd.setHinh(rs.getString(5));
                cd.setMoTa(rs.getString(6));
                lst.add(cd);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return lst;
    }
    
}
