package services;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import java.awt.*;

import services.PerformanceService;
import services.ArtistService;
import services.employeeService;
import services.ScheduleService;
import services.Event_Information;

import function.framefunction;

public class MainFunction {

    public static JPanel taoKhung(String id,int chedo)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1,5,0));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel.setPreferredSize(new Dimension(80,200));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));

        if(chedo==1)
        {
            ArtistService.Danhsachnghesi artists = new ArtistService.Danhsachnghesi();
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

                JLabel labelvaitro_nghesi = new JLabel("Vai trò: "+ thongtinnghesi.getvaitro());
                labelvaitro_nghesi.setName("role_artist");
                panel.add(labelvaitro_nghesi);


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
            PerformanceService.Danhsachtietmuc performances = new PerformanceService.Danhsachtietmuc();
            Map<String,PerformanceService.tietmuc> ds_tietmuc = performances.xuat();
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
        else if(chedo==3)
        {
            employeeService.Danhsachnhanvien employees = new employeeService.Danhsachnhanvien();
            Map<String,employeeService.nhanvien> ds_nhanvien = employees.xuat();
            employeeService.nhanvien thongtin_nhanvien = ds_nhanvien.get(id);

            if(thongtin_nhanvien==null)
            {
                panel.add(new JLabel("Không tìm thấy nhân viên có id: "+id));
                return panel;
            }
            else
            {
                panel.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

                JLabel id_nhanvien = new JLabel("ID nhân viên: "+thongtin_nhanvien.getId());
                id_nhanvien.setName("id_nhanvien");
                panel.add(id_nhanvien);

                JLabel ten_nhanvien = new JLabel("Tên nhân viên: "+thongtin_nhanvien.getName());
                ten_nhanvien.setName("ten_nhanvien");
                panel.add(ten_nhanvien);

                JLabel calamviec_nhanvien = new JLabel("Ca làm việc: "+thongtin_nhanvien.getca());
                ten_nhanvien.setName("calamviec_nhanvien");
                panel.add(calamviec_nhanvien);

                JLabel id_doi_nhanvien = new JLabel("ID đội phụ trách sự kiện: "+thongtin_nhanvien.getiddoi());
                id_doi_nhanvien.setName("id_doi_nhanvien");
                panel.add(id_doi_nhanvien);
            }
        }
        else if(chedo==4)
        {
            locationService.Danhsachdiadiem locations = new locationService.Danhsachdiadiem();
            Map<String,locationService.location> Mapdiadiem = locations.xuat();
            locationService.location thongtin_diadiem = Mapdiadiem.get(id);

            if(thongtin_diadiem==null)
            {
                panel.add(new JLabel("Không tìm thấy địa điểm có id: "+id));
                return panel;
            }
            else
            {
                panel.setBorder(BorderFactory.createTitledBorder("Thông tin địa điểm"));

                JLabel id_diadiem = new JLabel("ID địa điểm: "+thongtin_diadiem.getiddd());
                id_diadiem.setName("id_diadiem");
                panel.add(id_diadiem);

                JLabel ten_diadiem = new JLabel("Tên địa điểm: "+ thongtin_diadiem.getdiadiem());
                ten_diadiem.setName("ten_diadiem");
                panel.add(ten_diadiem);

                JLabel succhua_diadiem = new JLabel("Sức chứa: "+thongtin_diadiem.getsucchua() +" khách");
                succhua_diadiem.setName("succhua_diadiem");
                panel.add(succhua_diadiem);

            }
        }
        else if(chedo==5)
        {
            ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
            Map<String,ScheduleService.Schedule> ds_lichttrinh = schedules.xuat();
            ScheduleService.Schedule lichtrinh = ds_lichttrinh.get(id);

            JPanel bocuc = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=0;
            gbc.gridy=0;

            panel.setBorder(BorderFactory.createTitledBorder("Thông tin lịch trình"));


            JLabel id_lichtrinh = new JLabel("ID lịch trình: "+ lichtrinh.getIdLichTrinh());
            id_lichtrinh.setName("ID_lichtrinh");
            bocuc.add(id_lichtrinh,gbc);

            String lichtrinh_string = String.join(",",lichtrinh.getIdTietmuc());

            JLabel ds_id_lichtrinh = new JLabel("Danh sách ID tiết mục trong lịch trình: "+lichtrinh_string);
            ds_id_lichtrinh.setName("ds_id_lichtrinh");
            gbc.gridx=0;
            gbc.gridy=4;
            gbc.weightx=0;
            bocuc.add(ds_id_lichtrinh,gbc);

            JButton infor_Button = new JButton("Thông tin lịch trình");
            gbc.gridx=1;
            gbc.gridy=4;
            gbc.weightx=1.0;

    
            infor_Button.addActionListener(e->{
                function.Function_Dialog infor_dialog = new function.Function_Dialog(null,id,1);
                infor_dialog.setVisible(true);
            });

            bocuc.add(infor_Button,gbc);


            JButton changing_Button = new JButton("Sửa lịch trình");
            gbc.gridx=2;
            gbc.gridy=4;
            gbc.weightx=1.0;

            changing_Button.addActionListener(e->{
                function.Function_Dialog changing_dialog = new function.Function_Dialog(null, id, 2);
                changing_dialog.setVisible(true);
            });

            bocuc.add(changing_Button,gbc);


            panel.add(bocuc);

            
        }
        else if(chedo==0)
        {
            Event_Information.DanhsachThongtinSukien event_Information = new Event_Information.DanhsachThongtinSukien();
            Map<String,Event_Information.thongtin_sukien> Mapevent_information = event_Information.xuat();
            Event_Information.thongtin_sukien su_kien = Mapevent_information.get(id);

            if(su_kien==null)
            {
                panel.add(new JLabel("Không tìm thấy sự kiện có id: "+id));
                return panel;
            }
            else
            {
                 panel.setBorder(BorderFactory.createTitledBorder("Thông tin sự kiện"));

                 JLabel ID_sk = new JLabel("ID sự kiện: "+id);
                 ID_sk.setName("ID_sk");
                 panel.add(ID_sk);

                 JLabel Ten_sk = new JLabel("Tên sự kiện: "+su_kien.get_ten_sk());
                 Ten_sk.setName("Ten_sk");
                 panel.add(Ten_sk);


                 String ID_lichtrinh =  su_kien.get_id_lichtrinh();
                 ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
                 Map<String,ScheduleService.Schedule> ds_lichtrinh = schedules.xuat();

                 ScheduleService.Schedule lich_trinh = ds_lichtrinh.get(ID_lichtrinh);
                 List<String> ds_tietmuc = lich_trinh.getIdTietmuc();

                 String tietmuc_String= String.join(",",ds_tietmuc);
                 JLabel Ds_id_tietmuc = new JLabel("Danh sách tiết mục"+tietmuc_String);
                 Ds_id_tietmuc.setName("Ds_id_tietmuc");
                 panel.add(Ds_id_tietmuc);

                String ID_diadiem= su_kien.get_id_diadiem();
                locationService.Danhsachdiadiem locations = new locationService.Danhsachdiadiem();
                Map<String,locationService.location> map_location = locations.xuat();

                locationService.location dia_diem = map_location.get(ID_diadiem);
                
                String ten_dia_diem = dia_diem.getdiadiem();
                String suc_chua = String.valueOf(dia_diem.getsucchua());

                JLabel Ten_dia_diem = new JLabel("tên địa điểm: "+ ten_dia_diem);
                Ten_dia_diem.setName("Ten_dia_diem");
                panel.add(Ten_dia_diem);

                JLabel Suc_chua = new JLabel("sức chứa:"+suc_chua);
                Suc_chua.setName("Suc_chua");
                panel.add(Suc_chua);


                String ID_doi_phutrach = su_kien.get_id_doi_phutrach();
                teamService.DanhsachDoi team_service = new teamService.DanhsachDoi();
                Map<String,teamService.team> map_team_service= team_service.xuat();

                teamService.team Team_service = map_team_service.get(ID_doi_phutrach);
                
                String ID_leader = Team_service.getidleader();
                JLabel Id_leader = new JLabel("ID trưởng nhóm: "+ID_leader);
                Id_leader.setName("Id_leader ");
                panel.add(Id_leader);

                List<String> ds_id_doi= Team_service.getds();
                String ID_doi_String= String.join(",",ds_id_doi);
                JLabel Ds_id_doi = new JLabel("Danh sách ID đội: "+ID_doi_String);
                Ds_id_doi.setName("Ds_id_doi");
                panel.add(Ds_id_doi);
              
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
                    ArtistService.Danhsachnghesi nghesicanxoa = new ArtistService.Danhsachnghesi();
                    flag.put(id,nghesicanxoa.xoa(id));
                }
                else if(chedo==2)
                {
                    PerformanceService.Danhsachtietmuc tietmuccanxoa = new PerformanceService.Danhsachtietmuc();
                    flag.put(id,tietmuccanxoa.xoa(id));
                }
                else if(chedo==3)
                {
                    employeeService.Danhsachnhanvien nhanviencanxoa = new employeeService.Danhsachnhanvien();
                    flag.put(id,nhanviencanxoa.xoa(id));
                }
                else if(chedo==4)
                {
                    locationService.Danhsachdiadiem diadiemcanxoa = new locationService.Danhsachdiadiem();
                    flag.put(id,diadiemcanxoa.xoa(id));
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
                else if(chedo==3)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy nhân viên hoặc lỗi\n");
                }
                else if(chedo==4)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy địa điểm hoặc lỗi\n");
                }
            }

            JOptionPane.showMessageDialog(null, message, "Kết quả xóa", JOptionPane.INFORMATION_MESSAGE);
        }


        public static Map<String,Integer> configurer(JPanel paneltong,int chedo)
        {
            Map<String,Integer>flag= new HashMap<>();
            if(chedo==1)
            {
                ArtistService.Danhsachnghesi artist = new ArtistService.Danhsachnghesi();
                for(Component c: paneltong.getComponents())
                {
                    if(c instanceof JPanel)
                    {
                        JPanel paneltam = (JPanel) c;
                        Component comp[] = paneltam.getComponents();

                        JLabel id_ = (JLabel) comp[0];
                        JTextField ten_ = (JTextField) comp[2];
                        JTextField vaitro_ = (JTextField) comp[4];
                        JTextField congty_ = (JTextField) comp[6];
                        JTextField gia_ = (JTextField) comp[9];
                        JTextField listtietmuc_ = (JTextField) comp[11];


                        String id = id_.getText().trim();
                        if (id.startsWith("id:")) {
                            id = id.substring(3).trim();
                        }
                        String ten = ten_.getText().trim();
                        String vaitro = vaitro_.getText().trim();
                        String congty = congty_.getText().trim();
                        String giastring = gia_.getText().trim();
                        int gia;
                        try
                        {
                            gia = Integer.parseInt(giastring);
                        }
                        catch(NumberFormatException e)
                        {
                            flag.put(id,102);
                            break;
                        }   

                        String listtietmuc_string = listtietmuc_.getText().trim();
                        String[] partlist = listtietmuc_string.split(",");
                        List<String> dstietmuc =new ArrayList<>();
                        for(String p: partlist)
                        {
                            dstietmuc.add(p.trim());
                        }

                        ArtistService.nghesi nghesi = new ArtistService.nghesi(id,ten,vaitro,congty,gia,dstietmuc);

                        
                        
                        Boolean checked = artist.sua(nghesi);
                        if(!checked)
                        {
                            flag.put(id,0);
                        }
                    }
                }
            }
            else if(chedo==2)
            {
            
                PerformanceService.Danhsachtietmuc performances = new PerformanceService.Danhsachtietmuc();

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
                        int thoiluong;
                        try
                        {
                            thoiluong = Integer.parseInt(thoiluongstring);
                        }
                        catch(NumberFormatException e)
                        {
                            flag.put(id,202);
                            break;
                        }

                        PerformanceService.tietmuc tietmuc = new PerformanceService.tietmuc(id,ten,thoiluong);

                        
                        
                        Boolean checked = performances.sua(tietmuc);
                        if(!checked)
                        {
                            flag.put(id,0);
                        }
                    }
                }
            
            }
            else if(chedo==3)
            {
                employeeService employee = new employeeService();
                for(Component c: paneltong.getComponents())
                {
                    if(c instanceof JPanel)
                    {
                        JPanel paneltam = (JPanel) c;
                        Component[] comp = paneltam.getComponents();

                        JLabel id_ = (JLabel) comp[0];
                        JTextField ten_ = (JTextField) comp[2];
                        JTextField succhua_ = (JTextField) comp[4];

                    }
                }
            }
            else if(chedo==4)
            {
                locationService.Danhsachdiadiem location = new locationService.Danhsachdiadiem();
                for(Component c: paneltong.getComponents())
                {
                    if(c instanceof JPanel)
                    {
                        JPanel paneltam = (JPanel) c;
                        Component[] comp = paneltam.getComponents();

                        JLabel id_ = (JLabel) comp[0];
                        JTextField ten_ = (JTextField) comp[2];
                        JTextField suc_chua_ = (JTextField) comp[4];

                        String id = id_.getText().trim();
                        if(id.startsWith("id:"))
                        {
                            id = id.substring(3).trim();
                        }

                        String ten = ten_.getText().trim();

                        String suc_chua_string = suc_chua_.getText().trim();
                        int suc_chua;
                        try
                        {
                            suc_chua = Integer.parseInt(suc_chua_string);
                        }
                        catch(NumberFormatException e)
                        {
                            flag.put(id, 402);
                            break;
                        }

                        locationService.location diadiem = new locationService.location(ten, suc_chua, id);
                        Boolean checked = location.sua(diadiem);
                        if(!checked)
                        {
                            flag.put(id,0);
                        }
                    }
                }
            }
            return flag;
        }



        public static int artist_adding(String id,String ten,String vaitro,String congty,String giathanhstring,String listtietmuc_string,List<String>ds_idnghesi)
        {
            ArtistService.Danhsachnghesi artist = new ArtistService.Danhsachnghesi();
            for(String idxet :ds_idnghesi)
            {
                if(id.equalsIgnoreCase(idxet))
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

            ArtistService.nghesi nghesi = new ArtistService.nghesi(id,ten,vaitro,congty,gia,dstietmuc);
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

        public static int performance_adding(String id,String ten,String thoi_luong_,List<String>ds_idtietmuc)
        {
            PerformanceService.Danhsachtietmuc performances = new PerformanceService.Danhsachtietmuc();
            for(String id_tietmuc: ds_idtietmuc)
            {
                if(id_tietmuc.equalsIgnoreCase(id))
                {
                    return 201;
                }
            }
            int thoiluong;
            try
            {
                thoiluong=Integer.parseInt(thoi_luong_);
            }
            catch(NumberFormatException e)
            {
                return 202;
            }

            PerformanceService.tietmuc tietmuc = new PerformanceService.tietmuc(id,ten,thoiluong);
            Boolean checked = performances.them(tietmuc);
            if(!checked)
            {
                return 0;
            }
            return 1;
        }

        public static int location_adding(String id,String ten,String suc_chua_,List<String>ds_id_diadiem)
        {
            locationService.Danhsachdiadiem location = new locationService.Danhsachdiadiem();
            for(String id_diadiem: ds_id_diadiem)
            {
                if(id_diadiem.equalsIgnoreCase(id))
                {
                    return 401;
                }
            }
            int suc_chua;
            try
            {
                suc_chua=Integer.parseInt(suc_chua_);
            }
            catch(NumberFormatException e)
            {
                return 402;
            }

            locationService.location diadiem = new locationService.location(ten, suc_chua, id);
            Boolean checked = location.them(diadiem);
            if(!checked)
            {
                return 0;
            }
            return 1;
        }


        public static int employee_adding(String idnv, String ten, String ca_lam_viec, String id_doi, List<String> dsnv)
        {
            employeeService.Danhsachnhanvien em= new employeeService.Danhsachnhanvien();
            
            for(String ds: dsnv )
            {
               if(idnv.equals(ds))
               {
                return 301;
               }
            }
            employeeService.nhanvien nv= new employeeService.nhanvien(ca_lam_viec,idnv, id_doi,ten);
            boolean checked = em.them(nv);
            if( !checked)
            {
                return 0;
            }
            return 1;
            
        }


        public static class Function_Dialog extends JDialog
        {
            public Function_Dialog(JFrame parent,String id,int chedo)
            {
                super(parent,chonchedo(chedo),true);

                getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

                JPanel panelTong = veDialog(id, chedo);
                getContentPane().add(panelTong);

                setSize(800, 600);
                setLocationRelativeTo(parent);

            }

            private JPanel veDialog(String id,int chedo)
            {
                JPanel panel_tong = new JPanel();
                panel_tong.setLayout(new BoxLayout(panel_tong, BoxLayout.Y_AXIS));

                if(chedo==1 || chedo==2)
                {
                    JPanel panel_thongtin = new JPanel();
                    panel_thongtin.setLayout(new BoxLayout(panel_thongtin,BoxLayout.Y_AXIS));


                    panel_thongtin.setPreferredSize(new Dimension(800,50));
                    panel_thongtin.setMaximumSize(new Dimension(800,50));


                    ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
                    Map<String,ScheduleService.Schedule> ds_lichttrinh = schedules.xuat();
                    ScheduleService.Schedule lichtrinh = ds_lichttrinh.get(id);



                    JLabel id_lichtrinh = new JLabel("ID lịch trình: "+ lichtrinh.getIdLichTrinh());
                    id_lichtrinh.setName("ID_lichtrinh");
                    id_lichtrinh.setAlignmentX(Component.CENTER_ALIGNMENT);
                    id_lichtrinh.setFont(new Font("Arial",Font.BOLD,20));
                    panel_thongtin.add(id_lichtrinh);


                    int count=0;
                    int row=0;
                    List<String> ds_id_tietmuc = lichtrinh.getIdTietmuc();

                    PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                    Map<String,PerformanceService.tietmuc> MapTietmuc = danhsachtietmuc.xuat();


                    JPanel panel_lichtrinh = new JPanel(new GridBagLayout());
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridy=row;

                    ArtistService.Danhsachnghesi danhsachnghesi = new ArtistService.Danhsachnghesi();
                    Map<String,ArtistService.nghesi> MapNghesi = danhsachnghesi.xuat();


                    for(String id_tietmuc : ds_id_tietmuc)
                    {
                        if(id_tietmuc.equals("0"))
                        {

                            int index=row;


                            gbc.gridy=row;
                            gbc.gridx = 0;
                            gbc.gridwidth = 2;
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            gbc.weightx = 1.0;
                            gbc.insets = new Insets(5,5,5,5);

                            JPanel ngancach = new JPanel();
                            ngancach.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                            ngancach.setPreferredSize(new Dimension(0,40));
                            ngancach.setBackground(new Color(245,245,245));
                            ngancach.add(new JLabel("Khoảng nghỉ giữa giờ"));

                            if(chedo==2)
                            {
                                if(row!=0)
                                {
                                    JButton up = new JButton("⬆️ Lên");

                                    up.addActionListener(e->{
                                        index_swaper(id,index,1);
                                        refresh(id, chedo);
                                    });

                                        ngancach.add(up);
                                }
                                if(row<ds_id_tietmuc.size())
                                {
                                    JButton down = new JButton("⬇️ Xuống");

                                    down.addActionListener(e->{
                                        index_swaper(id, index, 2);
                                        refresh(id, chedo);
                                    });

                                    ngancach.add(down);
                                }
                                JButton delete = new JButton("🚮 Xóa");

                                delete.addActionListener(e->{
                                    index_deleter(id, index);
                                    refresh(id, chedo);
                                });
                                    
                                ngancach.add(delete);
                            }
                            
                            panel_lichtrinh.add(ngancach, gbc);
                            row+=1;

                        }
                        else
                        {
                            int index=row;



                            JPanel dulieu = new JPanel(new GridLayout(0,2,4,4));
                            gbc.gridwidth=1;
                            if(count%2!=0)
                            {
                                gbc.gridx=0;
                                dulieu.setBackground(new Color(230, 245, 255));
                            }
                            else
                            {
                                gbc.gridx=1;
                                dulieu.setBackground(new Color(255, 240, 245));
                            }

                            PerformanceService.tietmuc tietmuc = MapTietmuc.get(id_tietmuc);

                            gbc.gridy=row;
                            gbc.weightx = 1;
                            gbc.gridwidth=1;
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            gbc.insets = new Insets(5,5,5,5);
                        
                            dulieu.setPreferredSize(new Dimension(300, 150));
                            dulieu.setMinimumSize(new Dimension(300, 150));
                            dulieu.setMaximumSize(new Dimension(300, 150));

                            dulieu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

                            dulieu.add(new JLabel("ID tiết mục: "+id_tietmuc));

                            dulieu.add(new JLabel("Tên tiết mục: "+tietmuc.gettentietmuc()));

                            dulieu.add(new JLabel("Thời lượng: "+tietmuc.getthoiluong()+" Phút"));

                            dulieu.add(new JLabel(""));

                            String id_casi = danhsachtietmuc.timIdNgheSiTheoTietMuc(id_tietmuc);
                            ArtistService.nghesi nghesi = MapNghesi.get(id_casi);

                            dulieu.add(new JLabel("Biểu diễn bởi: "+nghesi.getName()));

                            dulieu.add(new JLabel("Với ID nghệ sĩ trong hệ thống: "+nghesi.getId()));
                            
                            if(chedo==2)
                            {
                                if(row!=0)
                                {
                                    JButton up = new JButton("⬆️ Lên");

                                    up.addActionListener(e->{
                                        index_swaper(id,index,1);
                                        refresh(id, chedo);
                                    });

                                    dulieu.add(up);
                                }
                                if(row<ds_id_tietmuc.size())
                                {
                                    JButton down = new JButton("⬇️ Xuống");

                                    down.addActionListener(e->{
                                        index_swaper(id, index, 2);
                                        refresh(id, chedo);
                                    });

                                    dulieu.add(down);
                                }
                                JButton delete = new JButton("🚮 Xóa");

                                delete.addActionListener(e->{
                                    index_deleter(id, index);
                                    refresh(id, chedo);
                                });
                                
                                dulieu.add(delete);
                            }

                            count+=1;
                            row+=1;
                            panel_lichtrinh.add(dulieu,gbc);
                        }
                    }

                    panel_tong.add(panel_thongtin);
                    JScrollPane scroll = new JScrollPane(panel_lichtrinh);
                    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                    panel_tong.add(panel_thongtin);
                    panel_tong.add(Box.createRigidArea(new Dimension(0, 10)));
                    panel_tong.add(scroll);
                }

                return panel_tong;

                
            }

            public static String chonchedo(int chedo)
            {
                if(chedo ==1)
                {
                    return "Thông tin lịch trình";
                }
                return null;
            }


            public static void index_swaper(String id,int index,int updown)
            {
                /*
                 * 1 up
                 * 2 down
                 */
                ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
                Map<String,ScheduleService.Schedule> ds_lichttrinh = schedules.xuat();
                ScheduleService.Schedule lichtrinh = ds_lichttrinh.get(id);

                List<String> ds_id_tietmuc = lichtrinh.getIdTietmuc();

                if(updown==1)
                {
                    Collections.swap(ds_id_tietmuc,index,index-1);
                    ScheduleService.Schedule schedule_moi = new ScheduleService.Schedule(lichtrinh.getIdLichTrinh(),ds_id_tietmuc);
                    schedules.sua(schedule_moi);
                }
                else if(updown==2)
                {
                    Collections.swap(ds_id_tietmuc,index,index+1);
                    ScheduleService.Schedule schedule_moi = new ScheduleService.Schedule(lichtrinh.getIdLichTrinh(),ds_id_tietmuc);
                    schedules.sua(schedule_moi);
                }
                
            }

            public static Boolean index_deleter(String id,int index)
            {
                ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
                Map<String,ScheduleService.Schedule> ds_lichttrinh = schedules.xuat();
                ScheduleService.Schedule lichtrinh = ds_lichttrinh.get(id);

                List<String> ds_id_tietmuc = lichtrinh.getIdTietmuc();

                try
                {
                    ds_id_tietmuc.remove(index);
                    ScheduleService.Schedule schedule_moi = new ScheduleService.Schedule(lichtrinh.getIdLichTrinh(),ds_id_tietmuc);
                    schedules.sua(schedule_moi);
                    return true;
                }
                catch(IndexOutOfBoundsException e)
                {
                    return false;
                }
                
            }

            public void refresh(String id, int chedo) {
                
                getContentPane().removeAll();

                JPanel panelTong = veDialog(id, chedo);
                getContentPane().add(panelTong);

                getContentPane().revalidate();
                getContentPane().repaint();
            }
        }

    }

}
