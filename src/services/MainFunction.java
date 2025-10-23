package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import javax.swing.*;
import java.awt.*;

public class MainFunction {


    //======================== INFOR HANDLER ==========================

    public static class Tietmuc
    {
        private Map<Integer,Integer> Maptietmuc = new HashMap<>();

        public Tietmuc()
        {

        }
        public Tietmuc(int id_tietmuc,int thoi_luong_tiet_muc)
        {
            Maptietmuc.put(id_tietmuc,thoi_luong_tiet_muc);
        }
        public void settietmuc(int id_tietmuc,int thoi_luong_tiet_muc)
        {
            Maptietmuc.put(id_tietmuc,thoi_luong_tiet_muc);
        }
        public int getthoiluong(int id_tietmuc)
        {
            return Maptietmuc.getOrDefault(id_tietmuc, -1);
        }

        public Map<Integer,Integer> getmaptietmuc()
        {
            return Maptietmuc;
        }
    }

    public static class Information
    {
        private int id_sk,id_casi,id_doiphutrach,so_tietmuc,thoi_luong_ct;
        private String ten_ct,ten_diadiem;
        private Map<Integer,Tietmuc> Maplichtrinh = new HashMap<>();

        public Information(int id_sk,int id_casi,int id_doiphutrach,int so_tietmuc,int thoi_luong_ct,String ten_ct,String ten_diadiem)
        {
            this.id_sk=id_sk;
            this.id_casi=id_casi;
            this.id_doiphutrach=id_doiphutrach;
            this.so_tietmuc=so_tietmuc;
            this.thoi_luong_ct=thoi_luong_ct;
            this.ten_ct=ten_ct;
            this.ten_diadiem=ten_diadiem;
        }
        public void setlichtrinh(int id_sk,int id_tietmuc,int thoi_luong_tiet_muc)
        {
            Maplichtrinh.put(id_sk,new Tietmuc(id_tietmuc,thoi_luong_tiet_muc));
        }
        public Map<Integer,Tietmuc> getmaplichtrinh()
        {
            return Maplichtrinh;
        }
        public int getsotietmuctronglichtrinh()
        {
            return Maplichtrinh.size();
        }

        public int gettongthoiluong()
        {
            int tong=0;
            for(Tietmuc tietmuc: Maplichtrinh.values())
            {
                for(int thoiluong: tietmuc.getmaptietmuc().values())
                {
                    if(thoiluong!=-1)
                    {
                        tong+=thoiluong;
                    }
                }
            }
            return tong;
        }
    }

    //======================== FRAME PRODUCT ==========================


    public static JPanel Taokhung(int id_sk,Map<Integer,Integer> dssukien)
    {
        JPanel mainframe = new JPanel(new BorderLayout());
        mainframe.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel id_label = new JLabel("ID sự kiện: " + id_sk,SwingConstants.CENTER);
        id_label.setFont(new Font("Arial",Font.BOLD,14));
        mainframe.add(id_label,BorderLayout.WEST);

        return mainframe;
    }



}
