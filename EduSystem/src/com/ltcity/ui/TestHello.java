/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.ui;

import com.ltcity.dao.NhanVienDAO;
import com.ltcity.dao.ThongKeDAO;
import com.ltcity.entity.NhanVien;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author haoca
 */
public class TestHello {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        // TODO code application logic here
//            NhanVienDAO nvd = new NhanVienDAO();
////            List<NhanVien> lst = nvd.selectAll();
////            for (NhanVien nv : lst) {
////                System.out.println("-->" + nv.toString());
////            }
//            nvd.insert(new NhanVien("HaoCG", "123", "Cao Giang Hào", true));
        ThongKeDAO tkd = new ThongKeDAO();
        List<Object[]> lst = tkd.getThongKeNguoiHoc();
        for(Object[] obj : lst){
            System.out.println("=>"+ obj[0] + "   " + obj[1] + "   " + obj[2]+ "   " + obj[3] );
        }

    }

}
