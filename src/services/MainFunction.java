package services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

import services.PerformanceService;
import services.ArtistService.nghesi;
import services.ArtistService;

public class MainFunction {


    //======================== INFOR HANDLER ==========================

    public static class Tietmuc
    {
        private Map<String,Integer> Maptietmuc = new HashMap<>();

        public Tietmuc()
        {

        }
        public Tietmuc(String id_tietmuc,int thoi_luong_tiet_muc)
        {
            Maptietmuc.put(id_tietmuc,thoi_luong_tiet_muc);
        }
        public void settietmuc(String id_tietmuc,int thoi_luong_tiet_muc)
        {
            Maptietmuc.put(id_tietmuc,thoi_luong_tiet_muc);
        }
        public int getthoiluong(String id_tietmuc)
        {
            return Maptietmuc.getOrDefault(id_tietmuc, -1);
        }

        public Map<String,Integer> getmaptietmuc()
        {
            return Maptietmuc;
        }
    }

    public static class Information
    {
        private int id_doiphutrach,so_tietmuc,thoi_luong_ct;
        private String ten_ct,ten_diadiem;
        private List<String> id_casi;

        private Map<String,Tietmuc> Maplichtrinh = new HashMap<>();

        public Information(List<String> id_casi,int id_doiphutrach,int thoi_luong_ct,String ten_ct,String ten_diadiem)
        {
            this.id_casi=id_casi;
            this.id_doiphutrach=id_doiphutrach;
            this.thoi_luong_ct=thoi_luong_ct;
            this.ten_ct=ten_ct;
            this.ten_diadiem=ten_diadiem;
        }


        public Information()
        {

        }



        public void setlichtrinh(String id_sk,String id_tietmuc,int thoi_luong_tiet_muc)
        {
            Maplichtrinh.put(id_sk,new Tietmuc(id_tietmuc,thoi_luong_tiet_muc));
        }
        public Map<String,Tietmuc> getmaplichtrinh()
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

        public Map<String,Tietmuc> getMaplichtrinh()
        {
            return Maplichtrinh;
        }

        public List<String> getIdcasi()
        {
            return id_casi;
        }

        
        public int getSotietmuc()
        {
            so_tietmuc=0;
            for(Tietmuc tietmuc : Maplichtrinh.values())
            {
                so_tietmuc+=1;
            }
            return so_tietmuc;
        }

        public String get_Tenct()
        {
            return ten_ct;
        }

        public String get_Tendiadiem()
        {
            return ten_diadiem;
        }
    }


   /* public static class loadfile
    {
        public static Map<String,Information> loaddataMap()
        {
            Map<String,PerformanceService.tietmuc> dataMapLoaded = PerformanceService.hienthitatcatietmuc();

            List<String> Listidtietmuc = new ArrayList<>(dataMapLoaded.keySet());

            Map<String,Information> MapInfor = new HashMap<>();

            for(String id : Listidtietmuc)
            {
                PerformanceService.tietmuc tietmuc = dataMapLoaded.get(id);
                Information infor = new Information(new ArrayList<>(), 0, 0, "0", "0");
                MapInfor.put(id,infor);
            }

        }
    }*/

    //======================== FRAME PRODUCT ==========================


    public static JPanel Taokhung(int id_sk,Map<Integer,Information> dssukien)
    {
        JPanel mainframe = new JPanel(new BorderLayout());
        mainframe.setBorder(BorderFactory.createLineBorder(Color.black));
        
        Information infor = dssukien.get(id_sk);
        if(infor != null)
        {

            GridBagConstraints infor_bgc = new GridBagConstraints();
            infor_bgc.anchor = GridBagConstraints.WEST;
            infor_bgc.fill = GridBagConstraints.HORIZONTAL;
            infor_bgc.insets = new Insets(5, 5, 5, 5);


            JPanel inforpanel = new JPanel(new GridBagLayout());

            JLabel id_label = new JLabel("ID sự kiện: " + id_sk,SwingConstants.CENTER);
            id_label.setFont(new Font("Arial",Font.BOLD,14));
            infor_bgc.gridx=0;
            infor_bgc.gridy=0;
            inforpanel.add(id_label,infor_bgc);



            JPanel space1 = new JPanel();
            infor_bgc.gridy+=2;
            inforpanel.add(space1,infor_bgc);

            JLabel name_label = new JLabel("Tên sự kiện: " + infor.get_Tenct(),SwingConstants.CENTER);
            name_label.setFont(new Font("Arial",Font.BOLD,14));
            infor_bgc.gridy+=2;
            inforpanel.add(name_label,infor_bgc);



            mainframe.add(inforpanel,BorderLayout.CENTER);

        }


        return mainframe;
    }


    public static JPanel Artist_taoKhung(String id_nghesi)
    {
        ArtistService artists = new ArtistService();
        Map<String,ArtistService.nghesi> dsnghesi = artists.hienthitatcanghesi();
        ArtistService.nghesi thongtinnghesi = dsnghesi.get(id_nghesi);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1,10,0));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel.setPreferredSize(new Dimension(80,200));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));



        if(thongtinnghesi==null)
        {
            panel.add(new JLabel("Không tìm thấy nghệ sĩ có id: "+id_nghesi));
            return panel;
        }
        else
        {
            panel.setBorder(BorderFactory.createTitledBorder("Thông tin nghệ sĩ"));
            panel.add(new JLabel("ID nghệ sĩ: "+ thongtinnghesi.getId()));
            panel.add(new JLabel("Tên: "+ thongtinnghesi.getName()));
            panel.add(new JLabel("Công ty: "+ thongtinnghesi.getcongty()));
            panel.add(new JLabel("Giá thành trên 1 lần diễn: "+ thongtinnghesi.getgiathanh()));

            List<String> dstietmuc = thongtinnghesi.getidtietmuc();
            String text = String.join(", ", dstietmuc).replace("[", "").replace("]", "").trim();
            panel.add(new JLabel("ID các tiết mục có thể diễn đã có trong dữ liệu: "+ text));
        }
        return panel;
    }


    public static class Artist_function
    {
        public static void artistdeleter(List<String> idtruyen)
        {
            Map<String,Boolean> flag = new HashMap<>();
            for(String id:idtruyen)
            {
                ArtistService nghesicanxoa = new ArtistService();
                flag.put(id,nghesicanxoa.xoanghesi(id));
            }


            StringBuilder message = new StringBuilder();
            for(Map.Entry<String,Boolean> entry : flag.entrySet())
            {
                message.append(entry.getKey());
                message.append("===>");
                message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy nghệ sĩ hoặc lỗi\n");
            }

            JOptionPane.showMessageDialog(null, message, "Kết quả xóa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
