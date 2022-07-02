/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltcity.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author haoca
 */
public class SImage {

    public static Image getAppIcon() {
        URL url = SImage.class.getResource("/com/ltcity/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }

    public static boolean saveLogo(File src) {
        File dst = new File("src\\com\\ltcity\\icon",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs(); //tạo thư mục này           
        }
        try {
            Path from  = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from,to,StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            return false;           
        }
    }

    /**
     * Đọc hình ảnh logo chuyên đề
     *
     * @param fileName là tên file logo
     * @return ảnh đọc được
     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("src\\com\\ltcity\\icon", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

}
