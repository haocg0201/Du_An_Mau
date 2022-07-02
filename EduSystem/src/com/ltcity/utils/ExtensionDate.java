/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.utils;

import com.ltcity.entity.NhanVien;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author haoca
 */
public class ExtensionDate {
    static SimpleDateFormat formater = new SimpleDateFormat();
    
    // chuyển đổi string sang date
    //<date> là string cần chuyển
    //<patterm> là định dạng thời gian
    //<return Date> -> kết quả
    public static Date toDate(String date,String patterm){
        try {
            formater.applyPattern(patterm);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException();
        }      
    }
    
    //chuyển đổi từ date sang String 
    //<date> là date cần chuyển đổi
    //<patterm> là định dạng thời gian
    //<return String > -> kết quả
    
    public static String toString(Date date,String patterm){
        formater.applyPattern(patterm);
        return formater.format(date);
    }
    
    public static Date now(){
        return new Date();
    }
    
    //bổ sung ngày vào thời gian
    //<date> thời gian hiện có
    //<days> số ngày cần bổ sung vào date
    //<return date> -> kết quả
    
    public static Date addDays(Date date,long days){
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
}
