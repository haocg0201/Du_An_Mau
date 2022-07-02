/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author haoca
 */
public class MessageBox {
    //hiển thị thông báo cho người dùng
    //<parent> là cửa sổ chứa thông báo
    //message là thông báo
    public static void aler(Component parent,String message){
        JOptionPane.showMessageDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    //hiển thị thông báo và yêu cầu người dùng xác nhận 
    //<parent> là của sổ chứa thông báo
    //<message> là câu hỏi yes/no
    //@return là kết quả nhân viên được true hoặc false
    public static boolean comfirm(Component parent,String message){
        int result = JOptionPane.showConfirmDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }
    
    // hiển thị thông báo yêu cầu nhập dữ liệu
    //<parent> là của sổ chứa thông báo
    //<message> là thông báo nhắc nhở nhập
    //@return là kết quả nhận được từ người sử dụng nhập vào
    public static String promt(Component parent,String message){
        return JOptionPane.showInputDialog(parent,message,"Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
}
