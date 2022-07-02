/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.utils;

import com.ltcity.entity.NhanVien;

/**
 *
 * @author haoca
 */
public class Authentication {

    // đối tượng này chứa thông tin người sử dụng sau khi đăng nhập
    public static NhanVien user = null;
    
    // xóa thông tin của người sử dụng khi có yêu cầu đăng nhập
    public static void clear(){
        Authentication.user = null;
    }
    
    // kiểm tra xem đăng nhập hay chưa
    //@return đăng nhập hay chưa
    public static boolean isLogin(){
        return Authentication.user != null;
    }
    
    public static boolean isVaiTro(){
        return Authentication.isLogin() && user.isVaiTro();
    }
    
}
