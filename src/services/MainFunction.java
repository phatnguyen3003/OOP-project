package services;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

import services.PerformanceService;
import services.ArtistService;

import function.framefunction;

public class MainFunction {

    public static JPanel taoKhung(String id,int chedo)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1,5,0));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel.setPreferredSize(new Dimension(80,200));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));

        if(chedo==1)
        {
            ArtistService artists = new ArtistService();
            Map<String,ArtistService.nghesi> dsnghesi = artists.xuat();
            ArtistService.nghesi thongtinnghesi = dsnghesi.get(id);



            if(thongtinnghesi==null)
            {
                panel.add(new JLabel("Không tìm thấy nghệ sĩ có id: "+id));
                return panel;
            }
            else
            {
                panel.setBorder(BorderFactory.createTitledBorder("Thông tin nghệ sĩ"));

                JLabel labelID_nghesi = new JLabel("ID nghệ sĩ: "+ thongtinnghesi.getId());
                labelID_nghesi.setName("id_artist");
                panel.add(labelID_nghesi);

                JLabel labelname_nghesi = new JLabel("Tên: "+ thongtinnghesi.getName());
                labelname_nghesi.setName("name_artist");
                panel.add(labelname_nghesi);


                JLabel labelcongty_nghesi = new JLabel("Công ty: "+ thongtinnghesi.getcongty());
                labelcongty_nghesi.setName("congty");
                panel.add(labelcongty_nghesi);


                JLabel labelgiathanh_nghesi = new JLabel("Giá thành trên 1 lần diễn: "+ thongtinnghesi.getgiathanh());
                labelgiathanh_nghesi.setName("giathanh");
                panel.add(labelgiathanh_nghesi);

                List<String> dstietmuc = thongtinnghesi.getidtietmuc();
                String text = String.join(", ", dstietmuc).replace("[", "").replace("]", "").trim();
                JLabel labelidtietmuc_nghesi = new JLabel("ID các tiết mục có thể diễn đã có trong dữ liệu: "+ text);
                labelidtietmuc_nghesi.setName("idtietmuc");
                panel.add(labelidtietmuc_nghesi);
            }
        }
        else if(chedo==2)
        {
            PerformanceService performance = new PerformanceService();
            Map<String,PerformanceService.tietmuc> ds_tietmuc = performance.xuat();
            PerformanceService.tietmuc thongtin_tietmuc = ds_tietmuc.get(id);

            if(thongtin_tietmuc == null)
            {
                panel.add(new JLabel("Không tìm thấy tiết mục có id: "+id));
                return panel;
            }
            else
            {
                panel.setBorder(BorderFactory.createTitledBorder("Thông tin tiết mục"));

                JLabel ID_tietmuc = new JLabel("ID tiết mục: "+thongtin_tietmuc.getidtietmuc());
                ID_tietmuc.setName("id_tietmuc");
                panel.add(ID_tietmuc);

                JLabel Ten_tietmuc = new JLabel("Tên: "+ thongtin_tietmuc.gettentietmuc());
                Ten_tietmuc.setName("ten_tietmuc");
                panel.add(Ten_tietmuc);

                JLabel Thoi_luong_tietmuc = new JLabel("Thời lượng "+thongtin_tietmuc.getthoiluong()+" phút");
                Thoi_luong_tietmuc.setName("thoi_luong_tietmuc");
                panel.add(Thoi_luong_tietmuc);
            }
        }
        return panel;
    }


    public static class function
    {
        public static void deleter(List<String> idtruyen,int chedo)
        {
            Map<String,Boolean> flag = new HashMap<>();
            for(String id:idtruyen)
            {
                if(chedo==1)
                {
                    ArtistService nghesicanxoa = new ArtistService();
                    flag.put(id,nghesicanxoa.xoa(id));
                }
                else if(chedo==2)
                {
                    PerformanceService tietmuccanxoa = new PerformanceService();
                    flag.put(id,tietmuccanxoa.xoa(id));
                }
            }


            StringBuilder message = new StringBuilder();
            for(Map.Entry<String,Boolean> entry : flag.entrySet())
            {
                message.append("ID: ");
                message.append(entry.getKey());
                message.append(" ===> ");
                if(chedo==1)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy nghệ sĩ hoặc lỗi\n");
                }
                else if(chedo==2)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy tiết mục hoặc lỗi\n");
                }
            }

            JOptionPane.showMessageDialog(null, message, "Kết quả xóa", JOptionPane.INFORMATION_MESSAGE);
        }


        public static void configurer(JPanel paneltong,int chedo)
        {
            if(chedo==1)
            {
                ArtistService artist = new ArtistService();
                for(Component c: paneltong.getComponents())
                {
                    if(c instanceof JPanel)
                    {
                        JPanel paneltam = (JPanel) c;
                        Component comp[] = paneltam.getComponents();

                        JLabel id_ = (JLabel) comp[0];
                        JTextField ten_ = (JTextField) comp[2];
                        JTextField congty_ = (JTextField) comp[4];
                        JTextField gia_ = (JTextField) comp[6];
                        JTextField listtietmuc_ = (JTextField) comp[9];


                        String id = id_.getText().trim();
                        if (id.startsWith("id:")) {
                            id = id.substring(3).trim();
                        }
                        String ten = ten_.getText().trim();
                        String congty = congty_.getText().trim();
                        String giastring = gia_.getText().trim();
                        int gia = Integer.parseInt(giastring);

                        String listtietmuc_string = listtietmuc_.getText().trim();
                        String[] partlist = listtietmuc_string.split(",");
                        List<String> dstietmuc =new ArrayList<>();
                        for(String p: partlist)
                        {
                            dstietmuc.add(p.trim());
                        }

                        ArtistService.nghesi nghesi = new ArtistService.nghesi(id,ten,congty,gia,dstietmuc);

                        
                        
                        artist.sua(nghesi);
                    }
                }
            }
            else if(chedo==2)
            {
            
                PerformanceService performance = new PerformanceService();
                for(Component c: paneltong.getComponents())
                {
                    if(c instanceof JPanel)
                    {
                        JPanel paneltam = (JPanel) c;
                        Component comp[] = paneltam.getComponents();

                        JLabel id_ = (JLabel) comp[0];
                        JTextField ten_ = (JTextField) comp[2];
                        JTextField thoiluong_ = (JTextField) comp[4];


                        String id = id_.getText().trim();
                        if (id.startsWith("id:")) {
                            id = id.substring(3).trim();
                        }
                        String ten = ten_.getText().trim();
                        String thoiluongstring = thoiluong_.getText().trim();
                        int thoiluong = Integer.parseInt(thoiluongstring);

                        PerformanceService.tietmuc tietmuc = new PerformanceService.tietmuc(id,ten,thoiluong);

                        
                        
                        performance.sua(tietmuc);
                    }
                }
            
            }
        }



        public static int artist_adding(String id,String ten,String congty,String giathanhstring,String listtietmuc_string,List<String>ds_idnghesi)
        {
            ArtistService artist = new ArtistService();
            for(String idxet :ds_idnghesi)
            {
                if(id.equals(idxet))
                {
                    return 101;
                }
            }

            int gia;
            try{
                gia = Integer.parseInt(giathanhstring);
            }
            catch(NumberFormatException e)
            {
                return 102;
            }

            String[] partlist = listtietmuc_string.split(",");
            List<String> dstietmuc =new ArrayList<>();
            for(String p: partlist)
            {
                dstietmuc.add(p.trim());
            }

            ArtistService.nghesi nghesi = new ArtistService.nghesi(id,ten,congty,gia,dstietmuc);
            Boolean checked = artist.them(nghesi);
            if(checked)
            {
                return 1;
            }
            else
            {
                return 0;
            }
            
        }

        public static int performance_adding(String id,String ten,String thoi_luong,List<String>ds_idtietmuc)
        {
            PerformanceService performance = new PerformanceService();
            for(String id_tietmuc: ds_idtietmuc)
            {
                if(id_tietmuc.equals(id))
                {
                    return 201;
                }
            }
            int thoiluong;
            try
            {
                thoiluong=Integer.parseInt(thoi_luong);
            }
            catch(NumberFormatException e)
            {
                return 202;
            }

            PerformanceService.tietmuc tietmuc = new PerformanceService.tietmuc(id,ten,thoiluong);
            Boolean checked = performance.them(tietmuc);
            if(!checked)
            {
                return 0;
            }
            return 1;
        }
    }

}
