package services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import services.PerformanceService;
import services.ArtistService;
import services.employeeService;
import services.employeeService.nhanvien;
import services.teamService.team;
import services.ScheduleService;
import services.Event_Information;

import function.team_manage;

public class MainFunction {

    public static JPanel taoKhung(String id,int chedo, JPanel mainframe1, Map<String,JCheckBox> quanlyselect, Runnable refresh)
    {
        JPanel panel = new JPanel();
        
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        panel.setPreferredSize(new Dimension(80,200));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE,200));

        if(chedo==1)
        {
            panel.setLayout(new GridLayout(6,1,5,0));

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

                JLabel labelvaitro_nghesi = new JLabel("Vai trò: "+ thongtinnghesi.getVaitro());
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
            panel.setLayout(new GridLayout(3,1,5,0));

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
            panel.setLayout(new GridLayout(5,1,5,0));


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
                id_nhanvien.setName("ID_nhanvien");
                panel.add(id_nhanvien);

                JLabel ten_nhanvien = new JLabel("Tên nhân viên: "+thongtin_nhanvien.getName());
                ten_nhanvien.setName("ten_nhanvien");
                panel.add(ten_nhanvien);

                JLabel vaitro_nhanvien = new JLabel("Vai trò nhân viên: "+thongtin_nhanvien.getVaitro());
                vaitro_nhanvien.setName("vaitro_nhanvien");
                panel.add(vaitro_nhanvien);


                JLabel calamviec_nhanvien = new JLabel("Ca làm việc: "+thongtin_nhanvien.getca());
                calamviec_nhanvien.setName("calamviec_nhanvien");
                panel.add(calamviec_nhanvien);

                JLabel id_doi_nhanvien = new JLabel("ID đội phụ trách sự kiện: "+thongtin_nhanvien.getiddoi());
                id_doi_nhanvien.setName("id_doi_nhanvien");
                panel.add(id_doi_nhanvien);
            }
        }
        else if(chedo==4)
        {
            panel.setLayout(new GridLayout(3,1,5,0));

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

                JLabel succhua_diadiem= new JLabel("Sức chứa: "+thongtin_diadiem.getsucchua());
                succhua_diadiem.setName("succhua_diadiem");
                panel.add(succhua_diadiem);

            }
        }
        else if(chedo==5)
        {
            panel.setLayout(new GridLayout(6,1,5,0));

            ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
            Map<String,ScheduleService.Schedule> ds_lichttrinh = schedules.xuat();
            ScheduleService.Schedule lichtrinh = ds_lichttrinh.get(id);

            if(lichtrinh==null)
            {
                panel.add(new JLabel("Không tìm thấy lịch trình có id: "+id));
                return panel;
            }
            else
            {
                panel.setBorder(BorderFactory.createTitledBorder("Thông tin lịch trình"));


                JLabel id_lichtrinh = new JLabel("ID lịch trình: "+ lichtrinh.getIdLichTrinh());
                id_lichtrinh.setName("ID_lichtrinh");
                panel.add(id_lichtrinh);

                String lichtrinh_string = String.join(",",lichtrinh.getIdTietmuc());

                JLabel ds_id_lichtrinh = new JLabel("Danh sách ID tiết mục trong lịch trình: "+lichtrinh_string);
                ds_id_lichtrinh.setName("ds_id_lichtrinh");
                panel.add(ds_id_lichtrinh);

                JButton infor_Button = new JButton("Xem thông tin chi tiết lịch trình");

        
                infor_Button.addActionListener(e->{
                    function.Function_Dialog infor_dialog = new function.Function_Dialog(null,id,1);
                    infor_dialog.setVisible(true);
                });

                panel.add(infor_Button);


                JButton changing_Button = new JButton("Sửa lịch trình");

                changing_Button.addActionListener(e->{
                    function.Function_Dialog changing_dialog = new function.Function_Dialog(null, id, 2);
                    changing_dialog.setVisible(true);
                });

                panel.add(changing_Button);
            }

            
        }
        else if(chedo==6)
        {
            panel.setLayout(new GridLayout(5,1,5,0));

            teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
            Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
            teamService.team doi_dangxet = MapDoi.get(id);


            employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
            Map<String,employeeService.nhanvien> MapNhanvien = danhsachnhanvien.xuat();


            if(doi_dangxet==null)
            {
                panel.add(new JLabel("Không tìm thấy đội có id: "+id));
                return panel;
            }
            else
            {
                panel.setBorder(BorderFactory.createTitledBorder("Thông tin đội phụ trách sự kiện"));

                

                JLabel iddoi = new JLabel("ID đội: "+doi_dangxet.getiddoi());
                iddoi.setName("iddoi");
                panel.add(iddoi);

                String iddoitruong = doi_dangxet.getidleader();
                String iddoitruong_text;
                String tendoitruong_text;
                if(iddoitruong==null)
                {
                    iddoitruong_text="Chưa có";
                    tendoitruong_text="Chưa có";
                }
                else
                {
                    iddoitruong_text=iddoitruong;
                    employeeService.nhanvien doitruong = MapNhanvien.get(iddoitruong);
                    if(doitruong==null)
                    {
                        iddoitruong_text="Không tồn tại";
                        tendoitruong_text="Không tồn tại";
                    }
                    else
                    {
                        tendoitruong_text=doitruong.getName();
                    }
                }


                JLabel idleader = new JLabel("ID đội trưởng: "+iddoitruong_text);
                panel.add(idleader);


                JLabel tenleader = new JLabel("Tên đội trưởng: "+tendoitruong_text);
                panel.add(tenleader);

                JLabel ds_doivien = new JLabel("Danh sách ID của đội viên: "+String.join(",",doi_dangxet.getds()));
                panel.add(ds_doivien);

                JButton sua = new JButton("Sửa");
                sua.addActionListener(e->{
                    configure_schedule_dialog(id,refresh);
                });
                panel.add(sua);
            }

            

        }
        else if(chedo==0)
        {
            panel.setLayout(new GridLayout(6,2,5,5));

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


                JLabel Ngaytochuc_sk = new JLabel("Ngày tổ chức: "+su_kien.get_ngaytochuc_sk());
                Ngaytochuc_sk.setName("Ngaytochuc");
                panel.add(Ngaytochuc_sk);

                String ID_lichtrinh =  su_kien.get_id_lichtrinh();
                ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
                Map<String,ScheduleService.Schedule> ds_lichtrinh = schedules.xuat();

                ScheduleService.Schedule lich_trinh = ds_lichtrinh.get(ID_lichtrinh);
                List<String> ds_tietmuc = lich_trinh.getIdTietmuc();

                String tietmuc_String= String.join(",",ds_tietmuc);
                JLabel Ds_id_tietmuc = new JLabel("Danh sách tiết mục: "+tietmuc_String);
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


                teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                Map<String,teamService.team> MapTeam = danhsachdoi.xuat();
                teamService.team doi = MapTeam.get(su_kien.get_id_doi_phutrach());
                
                JLabel Ds_id_doi = new JLabel("ID Đội phụ trách: "+su_kien.get_id_doi_phutrach());
                Ds_id_doi.setName("id_doi");
                panel.add(Ds_id_doi);

                String ID_leader = doi.getidleader();
                JLabel Id_leader = new JLabel("ID trưởng nhóm: "+ID_leader);
                Id_leader.setName("Id_leader ");
                panel.add(Id_leader);

                JButton infor_Button = new JButton("Xem thông tin chi tiết sự kiện");

                infor_Button.addActionListener(e->{
                function.Function_Dialog infor_dialog = new function.Function_Dialog(null,id,4);
                infor_dialog.setVisible(true);
                });

                panel.add(infor_Button);


                JButton changing_Button = new JButton("Sửa sự kiện");

                changing_Button.addActionListener(e->{
                    function.Function_Dialog changing_dialog = new function.Function_Dialog(null, id, 5);
                    changing_dialog.setVisible(true);
                    refreshUI(mainframe1, quanlyselect);
                });

                panel.add(changing_Button);
              
            }

        }
        

        return panel;
    }

    public static void refreshUI(JPanel mainframe1, Map<String, JCheckBox> quanlyselect) 
        {
                mainframe1.removeAll();
                quanlyselect.clear();

                Event_Information.DanhsachThongtinSukien danhsachttsk = new Event_Information.DanhsachThongtinSukien();
                Map<String, Event_Information.thongtin_sukien> MapSukien = danhsachttsk.xuat();
                List<String> ds_id_sk = new ArrayList<>(MapSukien.keySet());

                if(ds_id_sk.isEmpty())
                {
                    mainframe1.add(new JLabel("Không có dữ liệu lịch trình"));
                }
                else
                {
                    int count = 1;
                    for (String id_sk : ds_id_sk) {

                        JPanel dangtao = MainFunction.taoKhung(id_sk, 0,mainframe1,quanlyselect,null);
                        dangtao.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
                        dangtao.setAlignmentX(Component.LEFT_ALIGNMENT);

                        JCheckBox checkbox = new JCheckBox();
                        checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                        quanlyselect.put(id_sk, checkbox);

                        JPanel khungChucNang = new JPanel(new BorderLayout());
                        khungChucNang.add(checkbox, BorderLayout.WEST);
                        khungChucNang.add(dangtao, BorderLayout.CENTER);

                        if (count % 2 != 0) {
                            khungChucNang.setBackground(new Color(230, 245, 255));
                        } else {
                            khungChucNang.setBackground(new Color(255, 240, 245));
                        }
                        mainframe1.add(khungChucNang);
                        count++;
                    }
                }

                

                mainframe1.revalidate();
                mainframe1.repaint();
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

                    ArtistService.Danhsachnghesi danhsachnghesi = new ArtistService.Danhsachnghesi();
                    Map<String,ArtistService.nghesi>MapNgheSi = danhsachnghesi.xuat();
                    List<String> ds_id_nghesi = new ArrayList<>(MapNgheSi.keySet());

                    for(String id_nghesi : ds_id_nghesi)
                    {
                        ArtistService.nghesi nghesixet = MapNgheSi.get(id_nghesi);
                        List<String> id_tietmuc_bieudien = nghesixet.getidtietmuc();
                        id_tietmuc_bieudien.remove(id);
                        nghesixet.setidtietmuc(id_tietmuc_bieudien);
                        danhsachnghesi.sua(nghesixet);
                    }
                }
                else if(chedo==3)
                {
                    employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                    Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();
                    employeeService.nhanvien nhanvienxet = MapNhanVien.get(id);

                    String iddoi = nhanvienxet.getiddoi();

                    teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                    Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                    teamService.team doixet = MapDoi.get(iddoi);

                    List<String>ds_doivien = new ArrayList<>(doixet.getds());

                    if(ds_doivien.size()>1)
                    {
                        ds_doivien.remove(id);
                        doixet.setds(ds_doivien);
                        danhsachdoi.sua(doixet);
                        
                        flag.put(id,danhsachnhanvien.xoa(id));
                    }
                    else
                    {
                        flag.put(id,false);
                    }
                    
                }
                else if(chedo==4)
                {
                    locationService.Danhsachdiadiem diadiemcanxoa = new locationService.Danhsachdiadiem();
                    flag.put(id,diadiemcanxoa.xoa(id));
                }
                else if(chedo==5)
                {
                    ScheduleService.DanhsachLichtrinh lichtrinhcanxoa = new ScheduleService.DanhsachLichtrinh();
                    flag.put(id,lichtrinhcanxoa.xoa(id));
                }
                else if(chedo==6)
                {
                    teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                    Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                    teamService.team doicanxoa = MapDoi.get(id);
                    List<String> ds_doivien = doicanxoa.getds();

                    employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                    Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();


                    Boolean success = danhsachdoi.xoa(id);
                    flag.put(id,success);
                    if(success)
                    {
                        for(String id_doivien:ds_doivien)
                        {
                            employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_doivien);
                            nhanvienxet.setiddoi("0");
                        }
                    }
                }
                else if(chedo==0)
                {
                    Event_Information.DanhsachThongtinSukien sukiencanxoa = new Event_Information.DanhsachThongtinSukien();
                    flag.put(id,sukiencanxoa.xoa(id));
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
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy nghệ sĩ hoặc có lỗi\n");
                }
                else if(chedo==2)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy tiết mục hoặc có lỗi\n");
                }
                else if(chedo==3)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy nhân viên, danh sách đội viên của đội mà nhân viên đang công tác chỉ có 1 nhân viên hoặc lỗi khi xóa\n");
                }
                else if(chedo==4)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy địa điểm hoặc có lỗi\n");
                }
                else if(chedo==5)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy lịch trình hoặc có lỗi\n");
                }
                else if(chedo==0)
                {
                    message.append(entry.getValue() ? "Đã xóa thành công\n" : "Không tìm thấy sự kiện hoặc có lỗi\n");
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
                        JTextField gia_ = (JTextField) comp[8];
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
                        else
                        {
                            flag.put(id,1);
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
                        else
                        {
                            flag.put(id,1);
                        }
                    }
                }
            
            }
            else if(chedo==3)
            {
                employeeService.Danhsachnhanvien employee = new employeeService.Danhsachnhanvien();
                for(Component c: paneltong.getComponents())
                {
                    if(c instanceof JPanel)
                    {
                        JPanel paneltam = (JPanel) c;
                        Component[] comp = paneltam.getComponents();

                        JLabel id_ = (JLabel) comp[0];
                        JTextField ten_ = (JTextField) comp[2];
                        JTextField vaitro_ = (JTextField) comp[4];
                        JComboBox calam_ = (JComboBox) comp[6];
                        JComboBox id_doi_ = (JComboBox) comp[8];


                        String id = id_.getText().trim();
                        if (id.startsWith("id:")) {
                            id = id.substring(3).trim();
                        }
                        String ten = ten_.getText().trim();
                        String vaitro = vaitro_.getText().trim();
                        String calam = (String) calam_.getSelectedItem();
                        String id_doi = (String) id_doi_.getSelectedItem();

                        if(vaitro==null)
                        {
                            flag.put(id,303);
                            break;
                        }

                        employeeService.nhanvien nhanvien_sua = new employeeService.nhanvien(id,ten,vaitro,calam,id_doi);

                        Boolean checked = employee.sua(nhanvien_sua);
                        if(!checked)
                        {
                            flag.put(id,0);
                        }
                        else
                        {
                            flag.put(id,1);
                        }

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
                        else
                        {
                            flag.put(id,1);
                        }
                    }
                }
            }
            return flag;
        }


        public static class Function_Dialog extends JDialog
        {
            private JPanel panelTong;
            private JPanel TopPanel;
            private JPanel MidPanel;
            private JPanel BottomPanel;

            private String id_dia_diem = null;
            private String id_doi = null;
            private List<String> ds_id_tietmuc = new ArrayList<>();


            public Function_Dialog(JFrame parent,String id,int chedo)
            {
                
                super(parent,chonchedo(chedo),true);

                panelTong = new JPanel();
                panelTong.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx=0;
                gbc.gridy=0;
                gbc.weightx=1;
                gbc.fill = GridBagConstraints.BOTH;

                TopPanel = new JPanel();
                gbc.weighty=0.1;
                panelTong.add(TopPanel,gbc);


                gbc.gridy=1;
                if(chedo==1||chedo==2||chedo==3)
                {
                    gbc.weighty=0.9;
                }
                else
                {
                    gbc.weighty=0.25;
                }

                MidPanel = new JPanel();
                BottomPanel = new JPanel();

                panelTong.add(MidPanel,gbc);

                if(chedo==4||chedo==5||chedo==6)
                {
                    gbc.gridy=2;
                    gbc.weighty=0.75;
                    panelTong.add(BottomPanel,gbc);
                }
                
                
                add(panelTong);

                refreshTopPanel(chedo,id);
                refreshMidPanel(chedo, id);
                refreshBottomPanel(chedo, id);


                setSize(1200, 800);

                setLocationRelativeTo(parent);

            }

            public void refreshTopPanel(int chedo,String id)
            {

                panelTong.remove(TopPanel);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.BOTH;


                TopPanel = VeTopPanel(chedo,id);
                panelTong.add(TopPanel,gbc);
                panelTong.validate();
                panelTong.repaint();

                
            }

            public void refreshMidPanel(int chedo,String id)
            {

                panelTong.remove(MidPanel);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.BOTH;


                if(chedo==1||chedo==2||chedo==3)
                {
                    gbc.weighty=0.9;

                    MidPanel = VeBottomPanel(chedo,id);
                    panelTong.add(MidPanel,gbc);
                    panelTong.validate();
                    panelTong.repaint();
                    
                }
                else if(chedo==4||chedo==5||chedo==6)
                {
                    gbc.weighty=0.25;
                    MidPanel = VeMidPanel(chedo,id);
                    panelTong.add(MidPanel,gbc);
                    panelTong.validate();
                    panelTong.repaint();
                }

                
            }
            public void refreshBottomPanel(int chedo,String id)
            {

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.BOTH;


                if(chedo==4||chedo==5||chedo==6)
                {
                    gbc.weighty=0.75;

                    panelTong.remove(BottomPanel);
                    BottomPanel = VeBottomPanel(chedo, id);
                    panelTong.add(BottomPanel,gbc);
                    panelTong.validate();
                    panelTong.repaint();
                }
            }



            private JPanel VeTopPanel(int chedo,String id)
            {


                JPanel TopPanel = new JPanel(new GridLayout(0,3,5,5));

                if(chedo==1||chedo==2||chedo==3)
                {

                    ScheduleService.DanhsachLichtrinh danhsachlichtrinh = new ScheduleService.DanhsachLichtrinh();
                    Map<String,ScheduleService.Schedule> MapLichtrinh = danhsachlichtrinh.xuat();
                    ScheduleService.Schedule lichtrinh = null;

                    if(id!=null)
                    {
                        lichtrinh= MapLichtrinh.get(id);
                    }



                    if(chedo==1&&id!=null)
                    {
                        JLabel label = new JLabel("ID lịch trình: " + id);
                        label.setFont(new Font("Arial", Font.BOLD, 24));
                        TopPanel.add(label);
                    }
                    if(chedo==2&&id!=null)
                    {
                        TopPanel.add(new JLabel("ID lịch trình: "+id));

                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));


                        TopPanel.add(new JLabel("Chọn tiết mục thêm:"));

                        PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                        Map<String,PerformanceService.tietmuc> MapTietMuc = danhsachtietmuc.xuat();
                        JComboBox<PerformanceService.tietmuc> option_tietmuc = new JComboBox<>(MapTietMuc.values().toArray(new PerformanceService.tietmuc[0]));

                        PerformanceService.tietmuc tietmucMoi = new PerformanceService.tietmuc("0","Khoảng nghỉ giữa giờ",15);
                        option_tietmuc.insertItemAt(tietmucMoi, 0);
                        TopPanel.add(option_tietmuc);

                        
                        JButton them = new JButton("➕ Thêm tiết mục");
                        TopPanel.add(them);

                        TopPanel.add(new JLabel(""));

                        JButton save = new JButton("Lưu");
                        TopPanel.add(save);

                        ds_id_tietmuc = lichtrinh.getIdTietmuc();

                        them.addActionListener(e->{
                            PerformanceService.tietmuc tietmucchon = (PerformanceService.tietmuc) option_tietmuc.getSelectedItem();
                            String id_chon = tietmucchon.getidtietmuc();
                            ds_id_tietmuc.add(id_chon);
                            refreshMidPanel(chedo, id);
                        });

                        save.addActionListener(e->{
                            ScheduleService.Schedule lichtrinhsua = new ScheduleService.Schedule(id,ds_id_tietmuc);
                            if(danhsachlichtrinh.sua(lichtrinhsua))
                            {
                                JOptionPane.showMessageDialog(null, "Sửa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(save);
                                dialog.dispose();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Sửa thất bại","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
                    }
                    if(chedo==3&&id==null)
                    {
                        TopPanel.add(new JLabel("   ID lịch trình mới"));

                        
                        JTextField input_id_lichtrinh = new JTextField();
                        input_id_lichtrinh.setName("input_id_lichtrinh");
                        TopPanel.add(input_id_lichtrinh);

                        TopPanel.add(new JLabel(""));

                        TopPanel.add(new JLabel("   ID | Tên | Thời lượng(phút)"));
                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));

                        PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                        Map<String,PerformanceService.tietmuc> MapTietMuc = danhsachtietmuc.xuat();
                        JComboBox<PerformanceService.tietmuc> option_tietmuc = new JComboBox<>(MapTietMuc.values().toArray(new PerformanceService.tietmuc[0]));

                        PerformanceService.tietmuc tietmucMoi = new PerformanceService.tietmuc("0","Khoảng nghỉ giữa giờ",15);
                        option_tietmuc.insertItemAt(tietmucMoi, 0);
                        option_tietmuc.setSelectedIndex(0);
                        TopPanel.add(option_tietmuc);

                        TopPanel.add(new JLabel(""));

                        JButton them = new JButton("➕ Thêm tiết mục");
                        TopPanel.add(them);

                        TopPanel.add(new JLabel(""));

                        JButton save = new JButton("Lưu");
                        TopPanel.add(save);

                        them.addActionListener(e->{
                            PerformanceService.tietmuc tietmucchon = (PerformanceService.tietmuc) option_tietmuc.getSelectedItem();
                            String id_chon = tietmucchon.getidtietmuc();
                            ds_id_tietmuc.add(id_chon);
                            refreshMidPanel(chedo, id);
                        });

                        save.addActionListener(e->{
                            Boolean checked =true;
                            
                            String id_lichtrinh = input_id_lichtrinh.getText();
                            if(id_lichtrinh==null||id_lichtrinh.isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "ID lịch trình mới không được trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                checked=false;
                            }


                            ScheduleService.DanhsachLichtrinh ds = new ScheduleService.DanhsachLichtrinh();
                            Map<String, ScheduleService.Schedule> map = ds.xuat();
                            List<String> danh_sach_lich_trinh = new ArrayList<>(map.keySet());
                            for(String id_xet : danh_sach_lich_trinh)
                            {
                                if(id_xet.equalsIgnoreCase(id_lichtrinh))
                                {
                                    JOptionPane.showMessageDialog(null, "ID lịch trình đã tồn tại","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                    checked=false;
                                }
                            }

                            if(checked)
                            {
                                ScheduleService.Schedule lichtrinhsua = new ScheduleService.Schedule(id_lichtrinh,ds_id_tietmuc);
                                if(danhsachlichtrinh.them(lichtrinhsua))
                                {
                                    JOptionPane.showMessageDialog(null, "Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(save);
                                    dialog.dispose();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null, "Thêm thất bại","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            
                        });
                    }
                }
                else if(chedo==4||chedo==5||chedo==6)
                {
                    Event_Information.DanhsachThongtinSukien danhsach_sk = new Event_Information.DanhsachThongtinSukien();
                    Map<String,Event_Information.thongtin_sukien> Map_sk = danhsach_sk.xuat();
                    Event_Information.thongtin_sukien tt_sk = null;

                    if(id!=null)
                    {
                        tt_sk = Map_sk.get(id);
                    }

                    if(chedo==4 && id!=null)
                    {
                        TopPanel.add(new JLabel("ID sự kiện: "+id));
                        TopPanel.add(new JLabel("Tên sự kiện: "+tt_sk.get_ten_sk()));
                        TopPanel.add(new JLabel("Ngày tổ chức: "+tt_sk.get_ngaytochuc_sk()));
                        TopPanel.add(new JLabel("ID đội phụ trách: "+tt_sk.get_id_doi_phutrach()));

                        teamService.DanhsachDoi danhsachDoi = new teamService.DanhsachDoi();
                        Map<String,teamService.team> MapDoi = danhsachDoi.xuat();
                        teamService.team doixet = MapDoi.get(tt_sk.get_id_doi_phutrach());

                        TopPanel.add(new JLabel("ID địa điểm: "+tt_sk.get_id_diadiem()));

                        locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                        Map<String,locationService.location> MapDiadiem = danhsachdiadiem.xuat();
                        locationService.location diadiemxet = MapDiadiem.get(tt_sk.get_id_diadiem());

                        TopPanel.add(new JLabel("Tên địa điểm: "+diadiemxet.getdiadiem()));

                        TopPanel.add(new JLabel("Sức chứa: "+diadiemxet.getsucchua()));

                        TopPanel.add(new JLabel("ID lịch trình: "+tt_sk.get_id_lichtrinh()));

                        ScheduleService.DanhsachLichtrinh danhsachLichtrinh = new ScheduleService.DanhsachLichtrinh();
                        Map<String,ScheduleService.Schedule> MapLichtrinh = danhsachLichtrinh.xuat();
                        ScheduleService.Schedule lichtrinhxet = MapLichtrinh.get(tt_sk.get_id_lichtrinh());

                    }
                    if(chedo==5&&id!=null)
                    {
                        TopPanel.add(new JLabel("ID sự kiện: "+id));

                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));
                        
                        
                        JLabel label_ten_sk = new JLabel("Tên sự kiện:");
                        label_ten_sk.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_ten_sk);

                        JTextField input_ten_sk = new JTextField(tt_sk.get_ten_sk());
                        input_ten_sk.setName("input_ten_sk");
                        TopPanel.add(input_ten_sk);

                        TopPanel.add(new JLabel(""));

                        JLabel label_ngay_tc = new JLabel("Ngày tổ chức:");
                        label_ngay_tc.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_ngay_tc);

                        String ngay_tc = tt_sk.get_ngaytochuc_sk();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
                        JSpinner spinner_ngay = new JSpinner(model);
                        try
                        {
                            Date ngay =format.parse(ngay_tc);
                            spinner_ngay.setValue(ngay);
                        }
                        catch(ParseException e)
                        {
                            e.printStackTrace();
                        }

                        TopPanel.add(spinner_ngay);

                        TopPanel.add(new JLabel(""));

                        JLabel label_doi_pt = new JLabel("ID đội phụ trách:");
                        label_doi_pt.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_doi_pt);

                        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                        List<String>danh_sach_id_doi_vien = new ArrayList<>(MapDoi.keySet());

                        JComboBox<String> option_doi = new JComboBox<>(danh_sach_id_doi_vien.toArray(new String[0]));
                        option_doi.setSelectedItem((String) tt_sk.get_id_doi_phutrach());

                        TopPanel.add(option_doi);

                        JButton them_doi = new JButton("Thêm đội");
                        TopPanel.add(them_doi);

                        JLabel label_id_diadiem = new JLabel("ID địa điểm:");
                        label_id_diadiem.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_id_diadiem);

                        locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                        Map<String,locationService.location> MapDiadiem = danhsachdiadiem.xuat();
                        List<String>danh_sach_id_dia_diem = new ArrayList<>(MapDiadiem.keySet());

                        JComboBox<String> option_dia_diem = new JComboBox<>(danh_sach_id_dia_diem.toArray(new String[0]));
                        option_dia_diem.setSelectedItem((String) tt_sk.get_id_diadiem());

                        TopPanel.add(option_dia_diem);

                        JButton them_dia_diem = new JButton("Thêm địa điểm");
                        TopPanel.add(them_dia_diem);

                        JLabel label_id_lichtrinh = new JLabel("ID lịch trình:");
                        label_id_lichtrinh.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_id_lichtrinh);

                        ScheduleService.DanhsachLichtrinh danhsachlichtrinh = new ScheduleService.DanhsachLichtrinh();
                        Map<String,ScheduleService.Schedule> MapLichtrinh = danhsachlichtrinh.xuat();
                        List<String>danh_sach_id_lich_trinh = new ArrayList<>(MapLichtrinh.keySet());

                        JComboBox<String>option_lich_trinh = new JComboBox<>(danh_sach_id_lich_trinh.toArray(new String[0]));
                        option_lich_trinh.setSelectedItem(tt_sk.get_id_lichtrinh());

                        TopPanel.add(option_lich_trinh);

                        JButton them_lich_trinh = new JButton("Thêm lịch trình");
                        TopPanel.add(them_lich_trinh);

                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));
                        
                        JButton save = new JButton("Lưu");
                        TopPanel.add(save);


                        option_dia_diem.addActionListener(e->{
                            id_dia_diem = (String) option_dia_diem.getSelectedItem();
                            refreshMidPanel(chedo, id);
                        });
                        option_doi.addActionListener(e->{
                            id_doi = (String) option_doi.getSelectedItem();
                            refreshMidPanel(chedo, id);
                        });
                        option_lich_trinh.addActionListener(e->{
                            String id_lichtrinh = (String) option_lich_trinh.getSelectedItem();
                            ScheduleService.Schedule lichtrinhxet = MapLichtrinh.get(id_lichtrinh);
                            ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                            refreshBottomPanel(chedo, id);
                        });

                        them_dia_diem.addActionListener(e->{
                            createAddDialog(null, 4);
                            refreshTopPanel(chedo,id);
                        });
                        them_doi.addActionListener(e->{
                            createAddDialog(null, 6);
                            refreshTopPanel(chedo,id);
                        });
                        them_lich_trinh.addActionListener(e->{
                            Function_Dialog addDialog = new MainFunction.function.Function_Dialog(null, null, 3);
                            addDialog.setVisible(true);
                            refreshTopPanel(chedo,id);
                        });



                        save.addActionListener(e->{
                            String id_sk = id;
                            String ten_sk = input_ten_sk.getText();

                            Date date = (Date) spinner_ngay.getValue();
                            String ngay_to_chuc = format.format(date);

                            String id_doi = (String) option_doi.getSelectedItem();
                            String id_dia_diem = (String) option_dia_diem.getSelectedItem();
                            String id_lich_trinh = (String) option_lich_trinh.getSelectedItem();

                            Boolean checked=true;
                            if(ten_sk==null || ten_sk.isEmpty())
                            {
                                JOptionPane.showMessageDialog(this,"Tên sự kiện không được trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                checked=false;
                            }
                            else if(ngay_to_chuc==null || ngay_to_chuc.isEmpty())
                            {
                                JOptionPane.showMessageDialog(this,"Ngày tổ chức sư kiện không được trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                checked=false;
                            }
                            if(checked)
                            {
                                Event_Information.thongtin_sukien sukiensua = new Event_Information.thongtin_sukien(id,ten_sk,ngay_to_chuc,id_lich_trinh,id_dia_diem,id_doi);
                                Event_Information.DanhsachThongtinSukien danhsachsukien = new Event_Information.DanhsachThongtinSukien();
                                if(danhsachsukien.sua(sukiensua))
                                {
                                    JOptionPane.showMessageDialog(this,"Sửa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(save);
                                    dialog.dispose();
                                    
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this,"Sửa thất bại, kiểm tra lại hàm sửa","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            
                        });

                    }
                    if(chedo==6&&id==null)
                    {
                        JLabel label_id_sk = new JLabel("ID sự kiện: ");
                        label_id_sk.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_id_sk);

                        JTextField input_id_sk = new JTextField();
                        input_id_sk.setName("input_id_sk");
                        TopPanel.add(input_id_sk);

                        TopPanel.add(new JLabel(""));

                        
                        JLabel label_ten_sk = new JLabel("Tên sự kiện:");
                        label_ten_sk.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_ten_sk);

                        JTextField input_ten_sk = new JTextField();
                        input_ten_sk.setName("input_ten_sk");
                        TopPanel.add(input_ten_sk);

                        TopPanel.add(new JLabel(""));

                        JLabel label_ngay_tc = new JLabel("Ngày tổ chức:");
                        label_ngay_tc.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_ngay_tc);

                        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
                        JSpinner spinner_ngay = new JSpinner(model);

                        TopPanel.add(spinner_ngay);

                        TopPanel.add(new JLabel(""));

                        JLabel label_doi_pt = new JLabel("ID đội phụ trách:");
                        label_doi_pt.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_doi_pt);

                        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                        List<String>danh_sach_id_doi_vien = new ArrayList<>(MapDoi.keySet());

                        JComboBox<String> option_doi = new JComboBox<>(danh_sach_id_doi_vien.toArray(new String[0]));
                        if (option_doi.getItemCount() == 0) 
                        {
                            option_doi.addItem("— Không có dữ liệu —");
                        }

                        TopPanel.add(option_doi);

                        JButton them_doi = new JButton("Thêm đội");
                        TopPanel.add(them_doi);


                        JLabel label_id_diadiem = new JLabel("ID địa điểm:");
                        label_id_diadiem.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_id_diadiem);

                        locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                        Map<String,locationService.location> MapDiadiem = danhsachdiadiem.xuat();
                        List<String>danh_sach_id_dia_diem = new ArrayList<>(MapDiadiem.keySet());

                        JComboBox<String> option_dia_diem = new JComboBox<>(danh_sach_id_dia_diem.toArray(new String[0]));
                        if (option_dia_diem.getItemCount() == 0) 
                        {
                            option_dia_diem.addItem("— Không có dữ liệu —");
                        }
                        TopPanel.add(option_dia_diem);

                        JButton them_dia_diem = new JButton("Thêm địa điểm");
                        TopPanel.add(them_dia_diem);

                        JLabel label_id_lichtrinh = new JLabel("ID lịch trình:");
                        label_id_lichtrinh.setHorizontalAlignment(SwingConstants.RIGHT);
                        TopPanel.add(label_id_lichtrinh);

                        ScheduleService.DanhsachLichtrinh danhsachlichtrinh = new ScheduleService.DanhsachLichtrinh();
                        Map<String,ScheduleService.Schedule> MapLichtrinh = danhsachlichtrinh.xuat();
                        List<String>danh_sach_id_lich_trinh = new ArrayList<>(MapLichtrinh.keySet());

                        JComboBox<String>option_lich_trinh = new JComboBox<>(danh_sach_id_lich_trinh.toArray(new String[0]));
                        if (option_lich_trinh.getItemCount() == 0) 
                        {
                            option_lich_trinh.addItem("— Không có dữ liệu —");
                        }

                        TopPanel.add(option_lich_trinh);

                        JButton them_lich_trinh = new JButton("Thêm lịch trình");
                        TopPanel.add(them_lich_trinh);

                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));
                        TopPanel.add(new JLabel(""));

                        JButton save = new JButton("Lưu");
                        TopPanel.add(save);

                        option_dia_diem.addActionListener(e->{
                            id_dia_diem = (String) option_dia_diem.getSelectedItem();
                            refreshMidPanel(chedo, id);
                        });
                        option_doi.addActionListener(e->{
                            id_doi = (String) option_doi.getSelectedItem();
                            refreshMidPanel(chedo, id);
                        });
                        option_lich_trinh.addActionListener(e->{
                            String id_lichtrinh = (String) option_lich_trinh.getSelectedItem();
                            ScheduleService.Schedule lichtrinhxet = MapLichtrinh.get(id_lichtrinh);
                            ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                            refreshBottomPanel(chedo, id);
                        });


                        them_dia_diem.addActionListener(e->{
                            createAddDialog(null, 4);
                            refreshTopPanel(chedo,id);
                        });
                        them_doi.addActionListener(e->{
                            createAddDialog(null, 6);
                            refreshTopPanel(chedo,id);
                        });
                        them_lich_trinh.addActionListener(e->{
                            Function_Dialog addDialog = new MainFunction.function.Function_Dialog(null, null, 3);
                            addDialog.setVisible(true);
                            refreshTopPanel(chedo,id);
                        });

                        save.addActionListener(e->{
                            String id_sk = input_id_sk.getText();
                            String ten_sk = input_ten_sk.getText();

                            Date date = (Date) spinner_ngay.getValue();
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            String ngay_to_chuc = format.format(date);

                            String id_lich_trinh = (String) option_lich_trinh.getSelectedItem();
                            String id_doi = (String) option_doi.getSelectedItem();
                            String id_dia_diem = (String) option_dia_diem.getSelectedItem();
                            


                            Boolean checked=true;
                            Event_Information.DanhsachThongtinSukien danhsachsukien = new Event_Information.DanhsachThongtinSukien();
                            Map<String,Event_Information.thongtin_sukien> Map_su_kien = danhsachsukien.xuat();
                            List<String> id_sk_trong_danh_sach = new ArrayList<>(Map_su_kien.keySet());
                            
                            if(id_sk==null || id_sk.isEmpty())
                            {
                                JOptionPane.showMessageDialog(this,"ID sự kiện không được trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                checked=false;
                            }
                            else if(ten_sk==null || ten_sk.isEmpty())
                            {
                                JOptionPane.showMessageDialog(this,"Tên sự kiện không được trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                checked=false;
                            }
                            else if(ngay_to_chuc==null || ngay_to_chuc.isEmpty())
                            {
                                JOptionPane.showMessageDialog(this,"Ngày tổ chức sư kiện không được trống","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                checked=false;
                            }

                            for(String id_sukien : id_sk_trong_danh_sach)
                            {
                                if(id_sk.equalsIgnoreCase(id_sukien))
                                {
                                    JOptionPane.showMessageDialog(this,"ID sự kiện đã tồn tại","Cảnh báo",JOptionPane.WARNING_MESSAGE);
                                    checked=false;
                                    break;
                                }
                            }


                            if(checked)
                            {
                                Event_Information.thongtin_sukien sukiensua = new Event_Information.thongtin_sukien(id_sk,ten_sk,ngay_to_chuc,id_lich_trinh,id_dia_diem,id_doi);
                                
                                if(danhsachsukien.them(sukiensua))
                                {
                                    JOptionPane.showMessageDialog(this,"Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                    JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(save);
                                    dialog.dispose();
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(this,"Thêm thất bại, kiểm tra lại hàm thêm","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                            
                        });


                    }
                }
                
                return TopPanel;
            }

            private JPanel VeMidPanel(int chedo,String id)
            {
                JPanel MidPanel = new JPanel();
                MidPanel.setLayout(new BoxLayout(MidPanel, BoxLayout.Y_AXIS));


                if(chedo==4||chedo==5||chedo==6)
                {
                    Event_Information.DanhsachThongtinSukien danhsach_sk = new Event_Information.DanhsachThongtinSukien();
                    Map<String,Event_Information.thongtin_sukien> Map_sk = danhsach_sk.xuat();
                    Event_Information.thongtin_sukien tt_sk = null;

                    if(id!=null)
                    {
                        tt_sk = Map_sk.get(id);
                    }

                    if(chedo==4&&id!=null)
                    {

                        JPanel MidPanel_1_1 = new JPanel();
                        MidPanel_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                        MidPanel_1_1.setPreferredSize(new Dimension(300,50));
                        MidPanel_1_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                        locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                        Map<String,locationService.location> MapDiadiem = danhsachdiadiem.xuat();
                        locationService.location diadiemxet = MapDiadiem.get(tt_sk.get_id_diadiem());


                        MidPanel_1_1.add(new JLabel("ID địa điểm: "+diadiemxet.getiddd()));
                        
                        MidPanel_1_1.add(new JLabel("Tên địa điểm: "+diadiemxet.getdiadiem()));

                        String succhua = String.valueOf(diadiemxet.getsucchua());
                        MidPanel_1_1.add(new JLabel("Sức chứa: "+succhua));

                        MidPanel.add(MidPanel_1_1);


                        JPanel MidPanel_1_2 = new JPanel(new GridLayout(0,5,2,2));
                        MidPanel_1_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                        MidPanel_1_2.add(new JLabel("Thành viên đội "+tt_sk.get_id_doi_phutrach()));
                        MidPanel_1_2.add(new JLabel("ID nhân viên"));
                        MidPanel_1_2.add(new JLabel("Tên"));
                        MidPanel_1_2.add(new JLabel("Ca làm"));
                        MidPanel_1_2.add(new JLabel("chức vụ"));

                        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                        teamService.team doixet = MapDoi.get(tt_sk.get_id_doi_phutrach());
                        List<String> danh_sach_doi_vien = doixet.getds();

                        employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                        Map<String,employeeService.nhanvien> MapNhanvien = danhsachnhanvien.xuat();


                        for(String id_doi_vien:danh_sach_doi_vien)
                        {
                            employeeService.nhanvien nhanvienxet = MapNhanvien.get(id_doi_vien);
                            MidPanel_1_2.add(new JLabel(""));
                            MidPanel_1_2.add(new JLabel(id_doi_vien));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getName()));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getca()));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getVaitro()));
                        }

                        JScrollPane thanhcuon = new JScrollPane(MidPanel_1_2);
                        thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    

                        MidPanel.add(thanhcuon);

                    }
                    if(chedo==5&&id!=null)
                    {
                        JPanel MidPanel_1_1 = new JPanel();
                        MidPanel_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                        MidPanel_1_1.setPreferredSize(new Dimension(300,50));
                        MidPanel_1_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                        locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                        Map<String,locationService.location> MapDiadiem = danhsachdiadiem.xuat();
                        locationService.location diadiemxet;
                        if(id_dia_diem!=null)
                        {
                            diadiemxet = MapDiadiem.get(id_dia_diem);
                        }
                        else
                        {
                            
                            diadiemxet = MapDiadiem.get(tt_sk.get_id_diadiem());
                        }
                        


                        MidPanel_1_1.add(new JLabel("ID địa điểm: "+diadiemxet.getiddd()));
                        
                        MidPanel_1_1.add(new JLabel("Tên địa điểm: "+diadiemxet.getdiadiem()));

                        String succhua = String.valueOf(diadiemxet.getsucchua());
                        MidPanel_1_1.add(new JLabel("Sức chứa: "+succhua));

                        MidPanel.add(MidPanel_1_1);


                        JPanel MidPanel_1_2 = new JPanel(new GridLayout(0,5,2,2));
                        MidPanel_1_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                        MidPanel_1_2.add(new JLabel("Thành viên đội "+tt_sk.get_id_doi_phutrach()));
                        MidPanel_1_2.add(new JLabel("ID nhân viên"));
                        MidPanel_1_2.add(new JLabel("Tên"));
                        MidPanel_1_2.add(new JLabel("Ca làm"));
                        MidPanel_1_2.add(new JLabel("chức vụ"));

                        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                        teamService.team doixet;
                        if(id_doi!=null)
                        {
                            doixet = MapDoi.get(id_doi);
                        }
                        else
                        {
                            doixet = MapDoi.get(tt_sk.get_id_doi_phutrach());
                        }
                        
                        List<String> danh_sach_doi_vien = doixet.getds();

                        employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                        Map<String,employeeService.nhanvien> MapNhanvien = danhsachnhanvien.xuat();


                        for(String id_doi_vien:danh_sach_doi_vien)
                        {
                            employeeService.nhanvien nhanvienxet = MapNhanvien.get(id_doi_vien);
                            MidPanel_1_2.add(new JLabel(""));
                            MidPanel_1_2.add(new JLabel(id_doi_vien));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getName()));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getca()));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getVaitro()));
                        }

                        JScrollPane thanhcuon = new JScrollPane(MidPanel_1_2);
                        thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    

                        MidPanel.add(thanhcuon);
                    }
                    else if(chedo==6&&id==null)
                    {
                        JPanel MidPanel_1_1 = new JPanel();
                        MidPanel_1_1.setLayout(new FlowLayout(FlowLayout.LEFT));
                        MidPanel_1_1.setPreferredSize(new Dimension(300,50));
                        MidPanel_1_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                        locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                        Map<String,locationService.location> MapDiadiem = danhsachdiadiem.xuat();
                        locationService.location diadiemxet;
                        if(id_dia_diem!=null)
                        {
                            diadiemxet = MapDiadiem.get(id_dia_diem);
                        }
                        else
                        {
                            
                            Map.Entry<String,locationService.location>first = MapDiadiem.entrySet().iterator().next();
                            diadiemxet = first.getValue();
                        }
                        


                        MidPanel_1_1.add(new JLabel("ID địa điểm: "+diadiemxet.getiddd()));
                        
                        MidPanel_1_1.add(new JLabel("Tên địa điểm: "+diadiemxet.getdiadiem()));

                        String succhua = String.valueOf(diadiemxet.getsucchua());
                        MidPanel_1_1.add(new JLabel("Sức chứa: "+succhua));

                        MidPanel.add(MidPanel_1_1);


                        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
                        teamService.team doixet;
                        if(id_doi!=null)
                        {
                            doixet = MapDoi.get(id_doi);
                        }
                        else
                        {
                            Map.Entry<String,teamService.team>first = MapDoi.entrySet().iterator().next();
                            doixet = first.getValue();
                        }


                        JPanel MidPanel_1_2 = new JPanel(new GridLayout(0,5,2,2));
                        MidPanel_1_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                        MidPanel_1_2.add(new JLabel("Thành viên đội "+doixet.getiddoi()));
                        MidPanel_1_2.add(new JLabel("ID nhân viên"));
                        MidPanel_1_2.add(new JLabel("Tên"));
                        MidPanel_1_2.add(new JLabel("Ca làm"));
                        MidPanel_1_2.add(new JLabel("chức vụ"));

                        List<String> danh_sach_doi_vien = doixet.getds();

                        employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                        Map<String,employeeService.nhanvien> MapNhanvien = danhsachnhanvien.xuat();


                        for(String id_doi_vien:danh_sach_doi_vien)
                        {
                            employeeService.nhanvien nhanvienxet = MapNhanvien.get(id_doi_vien);
                            MidPanel_1_2.add(new JLabel(""));
                            MidPanel_1_2.add(new JLabel(id_doi_vien));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getName()));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getca()));
                            MidPanel_1_2.add(new JLabel(nhanvienxet.getVaitro()));
                        }

                        JScrollPane thanhcuon = new JScrollPane(MidPanel_1_2);
                        thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    

                        MidPanel.add(thanhcuon);
                    }
                }

                
                return MidPanel;
            }


            public JPanel VeBottomPanel(int chedo,String id)
            {
                JPanel container = new JPanel(new BorderLayout());

                JPanel BottomPanel = new JPanel(new GridLayout(0,2,5,5));

                Event_Information.DanhsachThongtinSukien danhsachthongtinsukien = new Event_Information.DanhsachThongtinSukien();
                Map<String,Event_Information.thongtin_sukien> MapSuKien = danhsachthongtinsukien.xuat();
                Event_Information.thongtin_sukien sukienxet;

                ScheduleService.DanhsachLichtrinh danhsachlichtrinh = new ScheduleService.DanhsachLichtrinh();
                Map<String,ScheduleService.Schedule> MapLichtrinh = danhsachlichtrinh.xuat();
                ScheduleService.Schedule lichtrinhxet;

                PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                Map<String,PerformanceService.tietmuc> MapTietmuc = danhsachtietmuc.xuat();

                ArtistService.Danhsachnghesi danhsachnghesi = new ArtistService.Danhsachnghesi();
                Map<String,ArtistService.nghesi> MapNgheSi = danhsachnghesi.xuat();



                if((chedo==1&&id!=null)||(chedo==4&&id!=null))
                {
                    if(chedo==1)
                    {
                        lichtrinhxet = MapLichtrinh.get(id);
                        ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                    }
                    else if(chedo==4)
                    {
                        sukienxet = MapSuKien.get(id);
                        lichtrinhxet = MapLichtrinh.get(sukienxet.get_id_lichtrinh());
                        ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                    }
                    
                    int count=1;

                    for(String id_tietmuc : ds_id_tietmuc)
                    {
                        

                        JPanel thong_tin = new JPanel(new GridLayout(0,2,5,10));


                        if(id_tietmuc.equalsIgnoreCase("0"))
                        {
                            JPanel part_1 = new JPanel(new GridLayout(1, 1));
                            part_1.add(new JLabel("Khoảng nghỉ", SwingConstants.CENTER));
                            part_1.setBackground(Color.getHSBColor(237, 233, 233));
                            part_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                            BottomPanel.add(part_1);

                            JPanel part_2 = new JPanel(new GridLayout(1, 1));
                            part_2.add(new JLabel("Khoảng nghỉ", SwingConstants.CENTER));
                            part_2.setBackground(Color.getHSBColor(237, 233, 233));
                            part_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                            BottomPanel.add(part_2);
                        }
                        else
                        {
                            if(MapTietmuc.containsKey(id_tietmuc))
                            {
                                if(count%2!=0){
                                    thong_tin.setBackground(new Color(230, 245, 255));
                                } else {
                                    thong_tin.setBackground(new Color(255, 240, 245));
                                }

                                PerformanceService.tietmuc tietmucxet = MapTietmuc.get(id_tietmuc);

                                thong_tin.add(new JLabel("ID tiết mục: "+tietmucxet.getidtietmuc()));
                                thong_tin.add(new JLabel("Tên tiết mục: "+ tietmucxet.gettentietmuc()));

                                thong_tin.add(new JLabel("Thời lượng: "+tietmucxet.getthoiluong()));
                                thong_tin.add(new JLabel(""));

                                List<String>ds_id_nghe_si_bieu_dien = danhsachtietmuc.timIdNgheSiTheoTietMuc(id_tietmuc);
                                if(ds_id_nghe_si_bieu_dien==null)
                                {
                                    thong_tin.add(new JLabel(" Không có nghệ sĩ biểu diễn"));
                                }
                                else
                                {
                                    StringBuilder ten_ns = new StringBuilder();
                                    int counting=0;

                                    for(String id_ns:ds_id_nghe_si_bieu_dien)
                                    {
                                        ArtistService.nghesi nghesixet = MapNgheSi.get(id_ns);
                                        ten_ns.append(nghesixet.getVaitro() +" "+nghesixet.getName());
                                        if(counting<ds_id_nghe_si_bieu_dien.size()-1 && ds_id_nghe_si_bieu_dien.size()>1)
                                        {
                                            ten_ns.append(",");
                                        }
                                        else
                                        {
                                            ten_ns.append(" và ");
                                        }
                                        String ten_nghe_si = ten_ns.toString();
                                    }
                                    String ten_nghe_si = ten_ns.toString();
                                    thong_tin.add(new JLabel("Biểu diễn bởi: "+ten_nghe_si));
                                }
                                if(count%2!=0)
                                {
                                    BottomPanel.add(thong_tin);
                                    BottomPanel.add(new JLabel(""));
                                }
                                else
                                {
                                    BottomPanel.add(new JLabel(""));
                                    BottomPanel.add(thong_tin);
                                }
                                
                                count+=1;
                            }
                            else
                            {
                                BottomPanel.add(new JLabel("Tiết mục không tồn tại"));
                            }

                        }
                    }
                    
                }
                else if((chedo==2&&id!=null)||(chedo==5&&id!=null))
                {
                    if(ds_id_tietmuc.isEmpty())
                    {
                        if(chedo==2)
                        {
                            lichtrinhxet = MapLichtrinh.get(id);
                            ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                        }
                        else if(chedo==5)
                        {
                            sukienxet = MapSuKien.get(id);
                            lichtrinhxet = MapLichtrinh.get(sukienxet.get_id_lichtrinh());
                            ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                        }
                    }
                    
                    
                    int count=1;
                    int index=0;

                    for(String id_tietmuc : ds_id_tietmuc)
                    {

                        final int currentIndex = index;
                        

                        JPanel thong_tin = new JPanel(new GridLayout(5,2,0,0));


                        if(id_tietmuc.equalsIgnoreCase("0"))
                        {
                            JPanel part_1 = new JPanel(new GridLayout(3, 3));
                            part_1.add(new JLabel("Khoảng nghỉ", SwingConstants.CENTER));
                            part_1.setBackground(Color.getHSBColor(237, 233, 233));
                            part_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                            


                            BottomPanel.add(part_1);

                            JPanel part_2 = new JPanel(new GridLayout(3, 3));


                            part_2.add(new JLabel(""));
                            part_2.add(new JLabel("Khoảng nghỉ", SwingConstants.CENTER));
                            part_2.add(new JLabel(""));


                            part_2.add(new JLabel(""));
                            part_2.add(new JLabel(""));
                            part_2.add(new JLabel(""));
                            if(chedo==2)
                            {
                                if(currentIndex>0)
                                {
                                    JButton up_Button = new JButton("Di chuyển lên ⬆️");
                                    up_Button.addActionListener(e -> {
                                        index_swaper(ds_id_tietmuc, currentIndex, 1);
                                        refreshMidPanel(chedo, id);
                                                
                                    });
                                    part_2.add(up_Button);
                                }
                                
                                if(currentIndex<ds_id_tietmuc.size()-1)
                                {
                                    JButton down_Button = new JButton("Di chuyển xuống ⬇️");
                                    down_Button.addActionListener(e -> {
                                        index_swaper(ds_id_tietmuc, currentIndex, 2);
                                        refreshMidPanel(chedo, id);
                                                    
                                    });
                                    part_2.add(down_Button);
                                }
                                
                                JButton delete_Button = new JButton("Xóa 🚮");

                                delete_Button.addActionListener(e->{
                                    index_deleter(ds_id_tietmuc,currentIndex);
                                    refreshMidPanel(chedo, id);
                                });

                                part_2.add(delete_Button);
                            }
                            

                            part_2.setBackground(Color.getHSBColor(237, 233, 233));
                            part_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                            index+=1;
                            


                            BottomPanel.add(part_2);
                        }
                        else
                        {
                            if(MapTietmuc.containsKey(id_tietmuc))
                            {
                                if(count%2!=0){
                                    thong_tin.setBackground(new Color(230, 245, 255));
                                } else {
                                    thong_tin.setBackground(new Color(255, 240, 245));
                                }

                                PerformanceService.tietmuc tietmucxet = MapTietmuc.get(id_tietmuc);

                                thong_tin.add(new JLabel("ID tiết mục: "+tietmucxet.getidtietmuc()));
                                thong_tin.add(new JLabel("Tên tiết mục: "+ tietmucxet.gettentietmuc()));

                                thong_tin.add(new JLabel("Thời lượng: "+tietmucxet.getthoiluong()));

                                List<String>ds_id_nghe_si_bieu_dien = danhsachtietmuc.timIdNgheSiTheoTietMuc(id_tietmuc);
                                if(ds_id_nghe_si_bieu_dien==null)
                                {
                                    thong_tin.add(new JLabel(" Không có nghệ sĩ biểu diễn"));
                                }
                                else
                                {
                                    StringBuilder ten_ns = new StringBuilder();
                                    int counting=0;

                                    for(String id_ns:ds_id_nghe_si_bieu_dien)
                                    {
                                        ArtistService.nghesi nghesixet = MapNgheSi.get(id_ns);
                                        ten_ns.append(nghesixet.getVaitro() +" "+nghesixet.getName());
                                        if(counting<ds_id_nghe_si_bieu_dien.size()-1 && ds_id_nghe_si_bieu_dien.size()>1)
                                        {
                                            ten_ns.append(",");
                                        }
                                        else
                                        {
                                            ten_ns.append(" và ");
                                        }
                                        String ten_nghe_si = ten_ns.toString();
                                    }
                                    String ten_nghe_si = ten_ns.toString();
                                    thong_tin.add(new JLabel("Biểu diễn bởi: "+ten_nghe_si));
                                }
                                if(chedo==2)
                                {
                                    if(currentIndex>0)
                                    {
                                        JButton up_Button = new JButton("Di chuyển lên ⬆️");
                                        up_Button.addActionListener(e -> {
                                            index_swaper(ds_id_tietmuc, currentIndex, 1);
                                            refreshMidPanel(chedo, id);
                                                    
                                        });
                                        thong_tin.add(up_Button);
                                    }
                                
                                    if(currentIndex<ds_id_tietmuc.size())
                                    {
                                        JButton down_Button = new JButton("Di chuyển xuống ⬇️");
                                        down_Button.addActionListener(e -> {
                                            index_swaper(ds_id_tietmuc, currentIndex, 2);
                                            refreshMidPanel(chedo, id);
                                                        
                                        });
                                        thong_tin.add(down_Button);
                                    }
                                    
                                    JButton delete_Button = new JButton("Xóa 🚮");

                                    delete_Button.addActionListener(e->{
                                        index_deleter(ds_id_tietmuc,currentIndex);
                                        refreshMidPanel(chedo, id);
                                    });

                                    thong_tin.add(delete_Button);

                                    thong_tin.add(new JLabel(""));
                                }


                                
                                if(count%2!=0)
                                {
                                    BottomPanel.add(thong_tin);
                                    BottomPanel.add(new JLabel(""));
                                }
                                else
                                {
                                    BottomPanel.add(new JLabel(""));
                                    BottomPanel.add(thong_tin);
                                }
                                index+=1;
                                count+=1;
                            }
                            else
                            {
                                BottomPanel.add(new JLabel("Tiết mục không tồn tại"));
                            }

                        }
                    }
                    
                }
                else if((chedo==3&&id==null)||(chedo==6&&id==null))
                {
                    
                    int count=1;
                    int index=0;

                    if(chedo==6 && ds_id_tietmuc.isEmpty())
                    {
                        Map.Entry<String,ScheduleService.Schedule> first = MapLichtrinh.entrySet().iterator().next();
                        lichtrinhxet = first.getValue();
                        ds_id_tietmuc = lichtrinhxet.getIdTietmuc();
                    }

                    if(ds_id_tietmuc!=null)
                    {
                        for(String id_tietmuc : ds_id_tietmuc)
                        {
                            final int currentIndex = index;
                            

                            JPanel thong_tin = new JPanel(new GridLayout(5,2,0,0));


                            if(id_tietmuc.equalsIgnoreCase("0"))
                            {
                                JPanel part_1 = new JPanel(new GridLayout(3, 3));
                                part_1.add(new JLabel("Khoảng nghỉ", SwingConstants.CENTER));
                                part_1.setBackground(Color.getHSBColor(237, 233, 233));
                                part_1.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

                                


                                BottomPanel.add(part_1);

                                JPanel part_2 = new JPanel(new GridLayout(3, 3));


                                part_2.add(new JLabel(""));
                                part_2.add(new JLabel("Khoảng nghỉ", SwingConstants.CENTER));
                                part_2.add(new JLabel(""));


                                part_2.add(new JLabel(""));
                                part_2.add(new JLabel(""));
                                part_2.add(new JLabel(""));

                                if(chedo==3)
                                {
                                    if(currentIndex>0)
                                    {
                                        JButton up_Button = new JButton("Di chuyển lên ⬆️");
                                        up_Button.addActionListener(e -> {
                                            index_swaper(ds_id_tietmuc, currentIndex, 1);
                                            refreshMidPanel(chedo, id);
                                                    
                                        });
                                        part_2.add(up_Button);
                                    }
                                
                                    if(currentIndex<ds_id_tietmuc.size()-1)
                                    {
                                        JButton down_Button = new JButton("Di chuyển xuống ⬇️");
                                        down_Button.addActionListener(e -> {
                                            index_swaper(ds_id_tietmuc, currentIndex, 2);
                                            refreshMidPanel(chedo, id);
                                                        
                                        });
                                        part_2.add(down_Button);
                                    }
                                    
                                    JButton delete_Button = new JButton("Xóa 🚮");

                                    delete_Button.addActionListener(e->{
                                        index_deleter(ds_id_tietmuc,currentIndex);
                                        refreshMidPanel(chedo, id);
                                    });

                                    part_2.add(delete_Button);
                                }
                                
                                part_2.setBackground(Color.getHSBColor(237, 233, 233));
                                part_2.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
                                index+=1;

                                


                                BottomPanel.add(part_2);
                            }
                            else
                            {
                                if(MapTietmuc.containsKey(id_tietmuc))
                                {
                                    if(count%2!=0){
                                        thong_tin.setBackground(new Color(230, 245, 255));
                                    } else {
                                        thong_tin.setBackground(new Color(255, 240, 245));
                                    }

                                    PerformanceService.tietmuc tietmucxet = MapTietmuc.get(id_tietmuc);

                                    thong_tin.add(new JLabel("ID tiết mục: "+tietmucxet.getidtietmuc()));
                                    thong_tin.add(new JLabel("Tên tiết mục: "+ tietmucxet.gettentietmuc()));

                                    thong_tin.add(new JLabel("Thời lượng: "+tietmucxet.getthoiluong()));

                                    List<String>ds_id_nghe_si_bieu_dien = danhsachtietmuc.timIdNgheSiTheoTietMuc(id_tietmuc);
                                if(ds_id_nghe_si_bieu_dien==null)
                                {
                                    thong_tin.add(new JLabel(" Không có nghệ sĩ biểu diễn"));
                                }
                                else
                                {
                                    StringBuilder ten_ns = new StringBuilder();
                                    int counting=0;

                                    for(String id_ns:ds_id_nghe_si_bieu_dien)
                                    {
                                        ArtistService.nghesi nghesixet = MapNgheSi.get(id_ns);
                                        ten_ns.append(nghesixet.getVaitro() +" "+nghesixet.getName());
                                        if(counting<ds_id_nghe_si_bieu_dien.size()-1 && ds_id_nghe_si_bieu_dien.size()>1)
                                        {
                                            ten_ns.append(",");
                                        }
                                        else
                                        {
                                            ten_ns.append(" và ");
                                        }
                                        String ten_nghe_si = ten_ns.toString();
                                    }
                                    String ten_nghe_si = ten_ns.toString();
                                    thong_tin.add(new JLabel("Biểu diễn bởi: "+ten_nghe_si));
                                }
                                    if(chedo==3)
                                    {
                                        if(currentIndex>0)
                                        {
                                            JButton up_Button = new JButton("Di chuyển lên ⬆️");
                                            up_Button.addActionListener(e -> {
                                                index_swaper(ds_id_tietmuc, currentIndex, 1);
                                                refreshMidPanel(chedo, id);
                                                        
                                            });
                                            thong_tin.add(up_Button);
                                        }
                                    
                                        if(currentIndex<ds_id_tietmuc.size()-1)
                                        {
                                            JButton down_Button = new JButton("Di chuyển xuống ⬇️");
                                            down_Button.addActionListener(e -> {
                                                index_swaper(ds_id_tietmuc, currentIndex, 2);
                                                refreshMidPanel(chedo, id);
                                                            
                                            });
                                            thong_tin.add(down_Button);
                                        }
                                        
                                        JButton delete_Button = new JButton("Xóa 🚮");

                                        delete_Button.addActionListener(e->{
                                            index_deleter(ds_id_tietmuc,currentIndex);
                                            refreshMidPanel(chedo, id);
                                        });

                                        thong_tin.add(delete_Button);


                                        thong_tin.add(new JLabel(""));
                                    }

                                    


                                    
                                    if(count%2!=0)
                                    {
                                        BottomPanel.add(thong_tin);
                                        BottomPanel.add(new JLabel(""));
                                    }
                                    else
                                    {
                                        BottomPanel.add(new JLabel(""));
                                        BottomPanel.add(thong_tin);
                                    }
                                    index+=1;
                                    count+=1;
                                }
                                else
                                {
                                    BottomPanel.add(new JLabel("Tiết mục không tồn tại"));
                                }

                            }
                        }
                    }
                    
                }
                JScrollPane thanhcuon = new JScrollPane(BottomPanel);
                thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                container.add(thanhcuon,BorderLayout.CENTER);

                return container;
            }

            

            public static String chonchedo(int chedo)
            {
                if(chedo ==1)
                {
                    return "Thông tin lịch trình";
                }
                else if(chedo==2)
                {
                    return "Sửa lịch trình";
                }
                else if(chedo==3)
                {
                    return "Thêm lịch trình";
                }
                else if(chedo==4)
                {
                    return "Thông tin sự kiện";
                }
                else if(chedo==5)
                {
                    return "Sửa thông tin sự kiện";
                }
                else if(chedo==6)
                {
                    return "Thêm sự kiện";
                }
                return null;
            }


            public static void index_swaper(List<String>ds_id_tietmuc,int index,int updown)
            {
                /*
                 * 1 up
                 * 2 down
                 */

                if(updown==1)
                {
                    Collections.swap(ds_id_tietmuc,index,index-1);
                }
                else if(updown==2)
                {
                    Collections.swap(ds_id_tietmuc,index,index+1);
                }
            }

            public static Boolean index_deleter(List<String>ds_id_tietmuc,int index)
            {


                try
                {
                    ds_id_tietmuc.remove(index);
                    return true;
                }
                catch(IndexOutOfBoundsException e)
                {
                    return false;
                }
                
            }


        }
        public static void createAddDialog(JFrame parent, int chedo)
        {

            List<String> ds_thanhvien = new ArrayList<>();
            final String[] idLeader = new String[1];
            idLeader[0]=null;


            JDialog addDialog = new JDialog(parent, getTitleByMode(chedo), true);
            addDialog.setSize(950, 400);
            addDialog.setLocationRelativeTo(parent);
            addDialog.setLayout(new BorderLayout());

            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            if (chedo == 1) // Artist
            {
                addFormField(formPanel, gbc, 0, "ID nghệ sĩ:", new JTextField(20));//1
                addFormField(formPanel, gbc, 1, "Tên nghệ sĩ:", new JTextField(20));//3
                addFormField(formPanel, gbc, 2, "Vai trò:", new JTextField(20));//5
                addFormField(formPanel, gbc, 3, "Công ty:", new JTextField(20));//7
                addFormField(formPanel, gbc, 4, "Giá thành (đ):", new JTextField(20));//9

                gbc.gridy=5;
                JButton them_tietmuc = new JButton("Thêm tiết mục");
                formPanel.add(them_tietmuc,gbc);

                gbc.gridy=6;
                gbc.gridx=0;
                gbc.gridwidth=2;

                JPanel panel_them_tiet_muc = new JPanel(new GridLayout(0,5,5,5));
                panel_them_tiet_muc.add(new JLabel("Chọn tiết mục có sẵn"));
                panel_them_tiet_muc.add(new JLabel("ID tiết mục"));
                panel_them_tiet_muc.add(new JLabel("Tên tiết mục"));
                panel_them_tiet_muc.add(new JLabel("Thời lượng (Phút)"));
                panel_them_tiet_muc.add(new JLabel(""));

                JScrollPane thanhcuon = new JScrollPane(panel_them_tiet_muc);
                thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                thanhcuon.setPreferredSize(new Dimension(150,300));
                formPanel.add(thanhcuon,gbc);

                them_tietmuc.addActionListener(e->{

                    PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                    Map<String,PerformanceService.tietmuc> MapTietMuc = danhsachtietmuc.xuat();

                    JComboBox<PerformanceService.tietmuc> option_tietmuc = new JComboBox<>();
                    option_tietmuc.addItem(new PerformanceService.tietmuc("0", "Chọn tiết mục có sẵn", 0));
                    for (PerformanceService.tietmuc tietmuc : MapTietMuc.values()) {
                        option_tietmuc.addItem(tietmuc);
                    }
                    panel_them_tiet_muc.add(option_tietmuc);

                    JTextField input_ID = new JTextField();
                    JTextField input_Ten = new JTextField();

                    Integer[] thoiluong = new Integer[30];
                    for(int i=0;i<30;i++) thoiluong[i] = i+1;
                    JComboBox<Integer> cbThoiluong = new JComboBox<>(thoiluong);

                    JButton btnXoa = new JButton("Xóa");
                    btnXoa.addActionListener(ev -> {
                        panel_them_tiet_muc.remove(option_tietmuc);
                        panel_them_tiet_muc.remove(input_ID);
                        panel_them_tiet_muc.remove(input_Ten);
                        panel_them_tiet_muc.remove(cbThoiluong);
                        panel_them_tiet_muc.remove(btnXoa);
                        panel_them_tiet_muc.revalidate();
                        panel_them_tiet_muc.repaint();
                    });


                    option_tietmuc.addActionListener(s->{
                        boolean selected = option_tietmuc.getSelectedIndex() != 0;
                        input_ID.setEnabled(!selected);
                        input_Ten.setEnabled(!selected);
                        cbThoiluong.setEnabled(!selected);
                    });

                    panel_them_tiet_muc.add(input_ID);
                    panel_them_tiet_muc.add(input_Ten);
                    panel_them_tiet_muc.add(cbThoiluong);
                    panel_them_tiet_muc.add(btnXoa);

                    panel_them_tiet_muc.revalidate();
                    panel_them_tiet_muc.repaint();
                });
            }
            else if(chedo == 2) // Performance
            {
                addFormField(formPanel, gbc, 0, "ID tiết mục:", new JTextField(20));
                addFormField(formPanel, gbc, 1, "Tên tiết mục:", new JTextField(20));
                List<Integer>list_thoiluong = new ArrayList<>();
                for(int i=1;i<=30;i++)
                {
                    list_thoiluong.add(i);
                }
                addFormField(formPanel, gbc, 2, "Thời lượng (phút):", new JComboBox<>(list_thoiluong.toArray(new Integer[0])));
            }
            else if(chedo == 3) // Employee
            {
                addFormField(formPanel, gbc, 0, "ID nhân viên:", new JTextField(20));
                addFormField(formPanel, gbc, 1, "Tên nhân viên:", new JTextField(20));
                addFormField(formPanel, gbc, 2, "Vai trò:", new JTextField(20));
                
                String[] calam = {"sáng", "chiều", "tối"};
                JComboBox<String> calamCombo = new JComboBox<>(calam);
                addFormField(formPanel, gbc, 3, "Ca làm việc:", calamCombo);
                
                teamService.DanhsachDoi teams = new teamService.DanhsachDoi();
                Map<String, teamService.team> ds_doi = teams.xuat();
                List<String> ds_id_doi = new ArrayList<>(ds_doi.keySet());
                ds_id_doi.add(0,"0");
                JComboBox<String> doiCombo = new JComboBox<>(ds_id_doi.toArray(new String[0]));
                addFormField(formPanel, gbc, 4, " ", new JLabel("0 nếu chưa cần phân về đội nào"));
                addFormField(formPanel, gbc, 5, "ID đội:", doiCombo);
            }
            else if(chedo == 4) // Location
            {
                addFormField(formPanel, gbc, 0, "ID địa điểm:", new JTextField(20));
                addFormField(formPanel, gbc, 1, "Tên địa điểm:", new JTextField(20));
                addFormField(formPanel, gbc, 2, "Sức chứa:", new JTextField(20));
            }
            else if(chedo == 6) // Team
            {
                

                Runnable[] refreshselectpanel = new Runnable[1];
                Runnable[] refreshviewpanel = new Runnable[1];

                addFormField(formPanel, gbc, 0, "ID đội:", new JTextField(20));
                gbc.gridy=1;
                JPanel selectPanel = new JPanel(new GridLayout(1,2,5,5));
                formPanel.add(selectPanel,gbc);

                gbc.gridy=2;
                JPanel viewPanel = new JPanel(new GridLayout(1,1,0,0));
                formPanel.add(viewPanel,gbc);




                refreshviewpanel[0] = () ->
                {
                    JPanel panel = new JPanel(new GridLayout(0,4,0,0));


                    employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                    Map<String,employeeService.nhanvien>MapNhanVien = danhsachnhanvien.xuat();


                    viewPanel.removeAll();

                    panel.add(new JLabel("ID nhân viên"));
                    panel.add(new JLabel("Tên nhân viên"));
                    panel.add(new JLabel("Ca làm"));
                    panel.add(new JLabel("Thao tác"));


                    for(String id_nhanvien:ds_thanhvien)
                    {
                        employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_nhanvien);
                        panel.add(new JLabel(nhanvienxet.getId()));
                        panel.add(new JLabel(nhanvienxet.getName()));
                        panel.add(new JLabel(nhanvienxet.getVaitro()));
                        if(!id_nhanvien.equalsIgnoreCase(idLeader[0]))
                        {
                            JButton switch_to_leader = new JButton("Đổi đội trưởng");

                            switch_to_leader.addActionListener(e->{
                                idLeader[0] = nhanvienxet.getId();
                                refreshviewpanel[0].run();
                            });

                            panel.add(switch_to_leader);
                        }
                        else
                        {
                            panel.add(new JLabel(""));
                        }
                    }

                    JScrollPane thanhcuon = new JScrollPane(panel);
                    thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    thanhcuon.setPreferredSize(new Dimension(450, 200));
                    viewPanel.add(thanhcuon);

                    viewPanel.validate();
                    viewPanel.repaint();
                };
                

                refreshselectpanel[0] = ()->
                {
                    selectPanel.removeAll();

                    employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                    Map<String,employeeService.nhanvien>MapNhanVien = danhsachnhanvien.xuat();

                    List<String> danhsachtoanbonhanvien = new ArrayList<>(MapNhanVien.keySet());
                    List<String> nhanvienchuacodoi = new ArrayList<>();

                    for(String id_nhanvien : danhsachtoanbonhanvien)
                    {
                        employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_nhanvien);
                        if(nhanvienxet.getiddoi().equalsIgnoreCase("0") && !ds_thanhvien.contains(id_nhanvien))
                        {
                            nhanvienchuacodoi.add(id_nhanvien);
                        }
                    }

                    Map<String,employeeService.nhanvien> MapChonNhanVien = new HashMap<>();

                    for(String id_nhanvien: nhanvienchuacodoi)
                    {
                        MapChonNhanVien.put(id_nhanvien, MapNhanVien.get(id_nhanvien));
                    }

                    JComboBox<employeeService.nhanvien> optionnhanvien = new JComboBox<>(MapChonNhanVien.values().toArray(new employeeService.nhanvien[0]));

                    selectPanel.add(optionnhanvien);

                    JButton add_button = new JButton("Thêm nhân viên vào đội");
                    selectPanel.add(add_button);


                    add_button.addActionListener(e->{
                        employeeService.nhanvien nhanvienxet =(employeeService.nhanvien) optionnhanvien.getSelectedItem();
                        ds_thanhvien.add(nhanvienxet.getId());
                        refreshviewpanel[0].run();
                        refreshselectpanel[0].run();
                    });


                    selectPanel.validate();
                    selectPanel.repaint();
                };

                refreshviewpanel[0].run();
                refreshselectpanel[0].run();
            }

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton saveButton = new JButton("Lưu");
            JButton cancelButton = new JButton("Hủy");

            saveButton.addActionListener(e -> {
                Boolean checked=true;
                StringBuilder message = new StringBuilder();
                if(chedo==1)
                {
                    List<String> danh_sach_tiet_muc_bieu_dien = new ArrayList<>();
                    List<String> danh_sach_id_tiet_muc_them = new ArrayList<>();
                    List<String> danh_sach_ten_tiet_muc_them = new ArrayList<>();
                    List<Integer> danh_sach_thoi_luong_tiet_muc_them = new ArrayList<>();


                    Component[] parts = formPanel.getComponents();

                    JTextField input_id_ns =(JTextField) parts[1];
                    JTextField input_ten_ns =(JTextField) parts[3];
                    JTextField input_vaitro_ns =(JTextField) parts[5];
                    JTextField input_congty_ns =(JTextField) parts[7];
                    JTextField input_giathanh_ns =(JTextField) parts[9];
                    JScrollPane scrollPane = (JScrollPane) parts[11];
                    JPanel input_ds_id_tietmuc = (JPanel) scrollPane.getViewport().getView();

                    String id_ns = input_id_ns.getText().trim();
                    String ten_ns = input_ten_ns.getText().trim();
                    String vaitro_ns = input_vaitro_ns.getText().trim();
                    String congty_ns = input_congty_ns.getText().trim();
                    String giathanhText = input_giathanh_ns.getText().trim();

                    int giathanh = 0;

                    ArtistService.Danhsachnghesi danhsachnghesi = new ArtistService.Danhsachnghesi();
                    Map<String, ArtistService.nghesi> danh_sach_nghe_si = danhsachnghesi.xuat();
                    List<String> ds_id_nghe_si = new ArrayList<>(danh_sach_nghe_si.keySet());


                        Component[] panel_components = input_ds_id_tietmuc.getComponents();

                        boolean inner_error = false;

                        if(panel_components.length>5)
                        {
                            for (int i = 5; i < panel_components.length; i += 5)
                            {
                                JComboBox<PerformanceService.tietmuc> cbTietMuc = (JComboBox<PerformanceService.tietmuc>) panel_components[i];
                                JTextField input_ID = (JTextField) panel_components[i + 1];
                                JTextField input_Ten = (JTextField) panel_components[i + 2];
                                JComboBox<Integer> cbThoiluong = (JComboBox<Integer>) panel_components[i + 3];

                                String id_tietmuc;
                                String ten_tietmuc;
                                int thoiluong_tietmuc;

                                if(cbTietMuc.getSelectedIndex()!=0)
                                {
                                    PerformanceService.tietmuc TietMucChon = (PerformanceService.tietmuc) cbTietMuc.getSelectedItem();
                                    danh_sach_tiet_muc_bieu_dien.add(TietMucChon.getidtietmuc());
                                }
                                else
                                {
                                    id_tietmuc = input_ID.getText();

                                    ten_tietmuc = input_Ten.getText();
                                    
                                    thoiluong_tietmuc = (Integer) cbThoiluong.getSelectedItem();

                                    if((id_tietmuc.isEmpty() || id_tietmuc==null)&&!inner_error)
                                    {
                                        message.append("ID tiết mục thêm không được trống\n");
                                        checked=false;
                                        inner_error=true;
                                    }
                                    else if((ten_tietmuc.isEmpty() || ten_tietmuc==null) && !inner_error)
                                    {
                                        message.append("Tên tiết mục thêm không được trống\n");
                                        checked=false;
                                        inner_error=true;
                                    }
                                    else
                                    {
                                        danh_sach_id_tiet_muc_them.add(id_tietmuc);
                                        danh_sach_ten_tiet_muc_them.add(ten_tietmuc);
                                        danh_sach_thoi_luong_tiet_muc_them.add(thoiluong_tietmuc);
                                        danh_sach_tiet_muc_bieu_dien.add(id_tietmuc);
                                    }
                                }
                            }
                        }
                        else
                        {
                            message.append("Nghệ sĩ thêm phải có tiết mục\n");
                            checked=false;
                        }
                        
                    if(id_ns == null || id_ns.isEmpty())
                    {
                        message.append("ID nghệ sĩ không được trống\n");
                        checked=false;
                    }
                    else if(vaitro_ns == null || vaitro_ns.isEmpty())
                    {
                        message.append("Vai trò của nghệ sĩ không được trống\n");
                        checked=false;
                    }
                    else if(congty_ns == null || congty_ns.isEmpty())
                    {
                        message.append("Công ty nghệ sĩ không được trống\n");
                        checked=false;
                    }
                    try
                    {
                        giathanh = Integer.parseInt(giathanhText);
                    }
                    catch(NumberFormatException g)
                    {
                        message.append("Giá một buổi diễn không thể có ký tự khác ngoài số");
                        checked=false;
                    }

                
                    ArtistService.Danhsachnghesi nghesicanthem = new ArtistService.Danhsachnghesi();
                    ArtistService.nghesi nghesithem = new ArtistService.nghesi(id_ns,ten_ns,vaitro_ns,congty_ns,giathanh,danh_sach_tiet_muc_bieu_dien);

                    if(checked)
                    {
                        if(!nghesicanthem.them(nghesithem))
                        {
                            message.append("Xảy ra lỗi, kiểm tra lại phương thức thêm");
                        }
                        else
                        {
                            message.append("Thêm thành công");
                            PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                            for(int i = 0;i<danh_sach_id_tiet_muc_them.size();i++)
                            {
                                PerformanceService.tietmuc tietmucthem = new PerformanceService.tietmuc(danh_sach_id_tiet_muc_them.get(i),danh_sach_ten_tiet_muc_them.get(i),danh_sach_thoi_luong_tiet_muc_them.get(i));
                                danhsachtietmuc.them(tietmucthem);
                            }
                        }
                    }

                }
            
                else if(chedo==2)
                {

                  Component[] parts = formPanel.getComponents();

                        JTextField input_id_tiet_muc = (JTextField) parts[1];
                        JTextField input_ten_tiet_muc = (JTextField) parts[3];
                        JComboBox input_thoi_luong = (JComboBox) parts[5];

                        String id_tiet_muc = input_id_tiet_muc.getText().trim();
                        String ten_tiet_muc = input_ten_tiet_muc.getText().trim();
                        int thoi_luong = (int) input_thoi_luong.getSelectedItem();

                        PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
                        Map<String, PerformanceService.tietmuc> danh_sach_tiet_muc = danhsachtietmuc.xuat();
                        List<String> ds_id_tiet_muc = new ArrayList<>(danh_sach_tiet_muc.keySet());



                        // 🔹 Kiểm tra ID trùng
                        for (String id_tiet_muc_xet : ds_id_tiet_muc) {
                            if (id_tiet_muc_xet.equalsIgnoreCase(id_tiet_muc)) {
                                message.append("ID thêm mới không được trùng\n");
                                checked = false;
                                break;
                            }
                        }

                        // 🔹 Kiểm tra thiếu thông tin
                        if (id_tiet_muc.isEmpty() || ten_tiet_muc.isEmpty()) {
                            message.append("Vui lòng nhập đủ thông tin\n");
                            checked = false;
                        }


                        // 🔹 Thực hiện thêm nếu mọi thứ hợp lệ
                        if (checked) 
                        {
                            PerformanceService.tietmuc tietmuc_them = new PerformanceService.tietmuc(
                                id_tiet_muc, ten_tiet_muc, thoi_luong
                            );

                            if (danhsachtietmuc.them(tietmuc_them)) {
                                message.append("Thêm tiết mục thành công");
                            } else {
                                message.append("Xảy ra lỗi khi thêm, kiểm tra lại hàm thêm");
                                checked = false;
                            }
                    }
              }
              if(chedo==3)
                {
                    Component[] parts = formPanel.getComponents();

                    JTextField input_idnv= (JTextField) parts[1];
                    JTextField input_ten= (JTextField) parts[3];
                    JTextField input_vai_tro= (JTextField) parts[5];
                    JComboBox input_ca_lam_viec = (JComboBox) parts[7];
                    JComboBox input_id_doinv= (JComboBox) parts[11];

                    String idnv = input_idnv.getText().trim();
                    String ten = input_ten.getText().trim();
                    String vaitro= input_vai_tro.getText().trim();
                    String ca_lam_viec= (String) input_ca_lam_viec.getSelectedItem();
                    String id_doinv = (String) input_id_doinv.getSelectedItem();

                    employeeService.Danhsachnhanvien danhsachnv= new employeeService.Danhsachnhanvien();

                    Map<String,employeeService.nhanvien> danh_sach_nv= danhsachnv.xuat();
                    List<String> list_id_nv = new ArrayList<>(danh_sach_nv.keySet());

                    
                    if(idnv==null||idnv.isEmpty())
                    {
                        message.append("ID nhân viên rỗng");
                        checked=false;
                    }
                    if(ten==null||ten.isEmpty())
                    {
                        message.append("Tên nhân viên rỗng");
                        checked=false;
                    }
                    if(vaitro==null||vaitro.isEmpty())
                    {
                        message.append("Vai trò của nhân viên rỗng");
                        checked=false;
                    }

                    employeeService.nhanvien nhanvienmoi = new employeeService.nhanvien(idnv, ten, vaitro, ca_lam_viec, id_doinv);

                    if(checked)
                    {
                        if(danhsachnv.them(nhanvienmoi))
                        {
                            message.append("Thêm thành công");

                            if(!id_doinv.equalsIgnoreCase("0"))
                            {
                                teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
                                Map<String,teamService.team> MapDoi = danhsachdoi.xuat();

                                teamService.team doixet = MapDoi.get(id_doinv);

                                List<String> ds_doivien = new ArrayList<>(doixet.getds());
                                ds_doivien.add(idnv);
                                doixet.setds(ds_doivien);
                                danhsachdoi.sua(doixet);
                            }
                            
                        }
                        else
                        {
                            message.append("Thêm thất bại, kiểm tra phương thức thêm");
                        }
                    }
                    


                }    
              else if(chedo == 4) // Location
                {
                    Component[] parts = formPanel.getComponents();
                    JTextField input_id = (JTextField) parts[1];
                    JTextField input_ten = (JTextField) parts[3];
                    JTextField input_succhua = (JTextField) parts[5];
                   
                    String id = input_id.getText().trim();
                    String ten = input_ten.getText().trim();
                    String succhua_string = input_succhua.getText().trim();
                   
                    // Kiểm tra trùng ID
                    locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
                    Map<String, locationService.location> danh_sach_diadiem = danhsachdiadiem.xuat();
                    List<String> list_id_diadiem = new ArrayList<>(danh_sach_diadiem.keySet());
                   
                    for(String id_xet : list_id_diadiem) {
                        if(id_xet.equalsIgnoreCase(id)) {
                            message.append("ID thêm mới không được trùng");
                            checked = false;
                            break;
                        }
                    }
                   
                    // Kiểm tra dữ liệu rỗng
                    if(id.isEmpty() || ten.isEmpty() || succhua_string.isEmpty()) {
                        message.append("Vui lòng nhập đủ thông tin");
                        checked = false;
                    }
                   
                    // Kiểm tra sức chứa
                    int succhua = 0;
                    if(checked) {
                        try {
                            succhua = Integer.parseInt(succhua_string);
                        } catch(NumberFormatException ex) {
                            message.append("Sức chứa phải là số");
                            checked = false;
                        }
                    }
                   
                    // Thêm địa điểm
                    if(checked) {
                        locationService.location diadiem_them = new locationService.location(ten, succhua, id);
                        if(danhsachdiadiem.them(diadiem_them)) {
                            message.append("Thêm thành công");
                        } else {
                            message.append("Xảy ra lỗi khi thêm, kiểm tra lại dữ liệu");
                            checked = false;
                        }
                    }
                }
                else if(chedo==6)
                {
                    Component[] parts = formPanel.getComponents();
 
                    JTextField input_id_doi = (JTextField) parts[1];
                    String id_doi = input_id_doi.getText().trim();


                    String id_leader = idLeader[0];
 
                    List<String> ds_id_doivien = new ArrayList<>(ds_thanhvien);
 
 
                    teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
 
                    Map<String,teamService.team> danh_sach_doi = danhsachdoi.xuat();
                    List<String> list_id_doi = new ArrayList<>(danh_sach_doi.keySet());
                    
                    
 
                    for(String id_doixet: list_id_doi)
                    {
                        if(id_doixet.equalsIgnoreCase(id_doi))
                        {
                            message.append("ID thêm mới không được trùng");
                            checked=false;
                        }
                    }
                    if(id_doi.isEmpty()||id_leader.isEmpty()||ds_id_doivien.isEmpty()||id_doi==null||id_leader==null||ds_id_doivien==null)
                    {
                        message.append("Vui lòng nhập đủ thông tin");
                        checked=false;
                    }
 
 
                    teamService.team doithem = new teamService.team(id_doi, id_leader, ds_id_doivien);
                    if(checked)
                    {
                        if(danhsachdoi.them(doithem))
                        {
                            message.append("Thêm thành công");
                        }
                        else
                        {
                            message.append("Xảy ra lỗi khi thêm, kiểm tra lại làm thêm");
                            checked=false;
                        }
                    }
                    
                    
 
                }
                JOptionPane.showMessageDialog(formPanel,message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                if(checked)
                addDialog.dispose();
            });
 

            cancelButton.addActionListener(e -> addDialog.dispose());

            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);

            JScrollPane scrollPane = new JScrollPane(formPanel);
            addDialog.add(scrollPane, BorderLayout.CENTER);
            addDialog.add(buttonPanel, BorderLayout.SOUTH);
            addDialog.setVisible(true);
        }

        private static void addFormField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent component)
        {
            gbc.gridx = 0;
            gbc.gridy = row;
            gbc.weightx = 0.3;
            panel.add(new JLabel(labelText), gbc);

            gbc.gridx = 1;
            gbc.weightx = 0.7;
            panel.add(component, gbc);
        }

        private static String getTitleByMode(int chedo)
        {
            switch(chedo) {
                case 1: return "Thêm nghệ sĩ";
                case 2: return "Thêm tiết mục";
                case 3: return "Thêm nhân viên";
                case 4: return "Thêm địa điểm";
                case 6: return "Thêm đội";
                default: return "Thêm dữ liệu";
            }
        }

        
    }



    public static void configure_schedule_dialog(String id,Runnable refresh)
    {
        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
        teamService.team doixet = MapDoi.get(id);

        List<String> ds_thanhvien = new ArrayList<>(doixet.getds());
        StringBuilder leader = new StringBuilder(doixet.getidleader());

        JDialog sua_lichtrinh_dialog = new JDialog();
        sua_lichtrinh_dialog.setLayout(new GridBagLayout());
        sua_lichtrinh_dialog.setSize(800,600);
        sua_lichtrinh_dialog.setLocationRelativeTo(null);
        sua_lichtrinh_dialog.setModal(true);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;




        gbc.gridy = 0;
        JLabel label_id = new JLabel(id, SwingConstants.CENTER);
        label_id.setFont(new Font("Arial", Font.BOLD, 24));
        sua_lichtrinh_dialog.add(label_id, gbc);





        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.2;
        JPanel panel_them = new JPanel(new GridLayout(1,2,5,5));
        sua_lichtrinh_dialog.add(panel_them, gbc);





        gbc.gridy = 2;
        gbc.weighty = 0.7;
        JPanel panel_xem = new JPanel(new GridLayout(0,5,5,5));
        JScrollPane scrollPanel = new JScrollPane(panel_xem);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setPreferredSize(new Dimension(800,300));
        sua_lichtrinh_dialog.add(scrollPanel, gbc);



        refreshpanel_them(panel_them,panel_xem, ds_thanhvien,leader);
        refreshpanel_xem(panel_them,panel_xem,ds_thanhvien,leader);

        gbc.gridy = 3;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel panel_save = new JPanel();
        JButton save = new JButton("Lưu");
        panel_save.add(save);
        sua_lichtrinh_dialog.add(panel_save, gbc);


        save.addActionListener(e->{
            StringBuilder message = new StringBuilder();
            Boolean checked=true;
            if(leader.toString() == null || leader.length()==0)
            {
                message.append("Thiếu đội trưởng\n");
                checked=false;
            }
            if(ds_thanhvien.isEmpty())
            {
                message.append("Đội rỗng\n");
                checked=false;
            }

            if(checked)
            {
                teamService.team doidangxet = new teamService.team(id,leader.toString(),ds_thanhvien);
                if(danhsachdoi.sua(doidangxet))
                {
                    message.append("Sửa thành công");

                    employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
                    Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();
                    for(String id_doi_vien:ds_thanhvien)
                    {
                        employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_doi_vien);
                        nhanvienxet.setiddoi(id);
                        danhsachnhanvien.sua(nhanvienxet);
                    }
                }
                else
                {
                    message.append("Sửa thất bại, kiểm tra lại phương thức sửa");
                }
            }
            JOptionPane.showMessageDialog(sua_lichtrinh_dialog, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            if(checked)
            {
                sua_lichtrinh_dialog.dispose();
                refresh.run();
            }

        });

        sua_lichtrinh_dialog.setVisible(true);
    }

    private static void refreshpanel_them(JPanel panel_them,JPanel panel_xem,List<String>ds_thanhvien,StringBuilder leader)
    {
        teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
        Map<String,teamService.team> MapDoi = danhsachdoi.xuat();
        
        employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
        Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();
        List<String> ds_id_nhanvien = new ArrayList<>(MapNhanVien.keySet());

        panel_them.removeAll();

        Map<String,employeeService.nhanvien> ds_nhanvien_chuadoi = new HashMap<>();
        for(String id_nv: ds_id_nhanvien)
        {
            employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_nv);
            if ("0".equalsIgnoreCase(nhanvienxet.getiddoi()) && !ds_thanhvien.contains(id_nv))
            {
                ds_nhanvien_chuadoi.put(id_nv,nhanvienxet);
            }
        }

        JComboBox<employeeService.nhanvien> option_nhanvien = new JComboBox<>(ds_nhanvien_chuadoi.values().toArray(new employeeService.nhanvien[0]));

        panel_them.add(option_nhanvien);

        JButton them_nv_vao_list = new JButton("Thêm nhân viên vào đội");
        panel_them.add(them_nv_vao_list);

        them_nv_vao_list.addActionListener(e->{
        employeeService.nhanvien nhanvienthem = (employeeService.nhanvien) option_nhanvien.getSelectedItem();
        String id_them = nhanvienthem.getId();
        ds_thanhvien.add(id_them);
        refreshpanel_them(panel_them,panel_xem,ds_thanhvien,leader);
        refreshpanel_xem(panel_them,panel_xem,ds_thanhvien,leader);
        });

        panel_them.validate();
        panel_them.repaint();
    }


    private static void refreshpanel_xem(JPanel panel_them,JPanel panel_xem,List<String>ds_thanhvien,StringBuilder leader)
    {
        employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
        Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();


        panel_xem.removeAll();
        panel_xem.add(new JLabel("ID nhân viên"));
        panel_xem.add(new JLabel("Tên nhân viên"));
        panel_xem.add(new JLabel("Ca làm"));
        panel_xem.add(new JLabel("Thao tác"));
        panel_xem.add(new JLabel(""));

        for(String id_nhanvien : ds_thanhvien)
        {
            employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_nhanvien);
            panel_xem.add(new JLabel(id_nhanvien));
            panel_xem.add(new JLabel(nhanvienxet.getName()));
            panel_xem.add(new JLabel(nhanvienxet.getca()));
            
            if(!leader.toString().equalsIgnoreCase(id_nhanvien))
            {
                JButton switch_captain = new JButton("Đặt đội trưởng");
                panel_xem.add(switch_captain);

                switch_captain.addActionListener(e->{
                leader.setLength(0);
                leader.append(nhanvienxet.getId());
                refreshpanel_xem(panel_them,panel_xem,ds_thanhvien,leader);
                });
            }
            else
            {
                panel_xem.add(new JLabel(""));
            }
            
            JButton delete = new JButton("Xóa khỏi danh sách");
            panel_xem.add(delete);

            delete.addActionListener(e->{
                ds_thanhvien.remove(id_nhanvien);
                if(leader.length()>0 && leader.toString().equalsIgnoreCase(id_nhanvien))
                {
                    leader.setLength(0);
                }
                refreshpanel_xem(panel_them,panel_xem,ds_thanhvien,leader);
                refreshpanel_them(panel_them, panel_xem, ds_thanhvien, leader);
            });

            panel_xem.validate();
            panel_xem.repaint();
        }

    }


}

