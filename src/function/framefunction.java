package function;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;
import java.util.Map;


import services.ArtistService;
import services.MainFunction;

//================== add event ===============================
public class framefunction {
    public static class EventDialog extends JDialog
    {

        private List<JLabel> labels = new ArrayList<>();
        String[] iddoi = {"Đội 1", "Đội 2", "Đội 3", "Đội 4", "Đội 5", "Đội 6", "Đội 7"};
        String[] tlctrinh = {"90 phút", "120 phút", "150 phút", "180 phút", "210 phút", "240 phút", "270 phút","300 phút"};
        private JTextField eventNameField;
        private JButton saveButton,cancelButton;

        int socanghesi=8;

        public EventDialog(JFrame parent)
        {
            super(parent,"Thêm sự kiện",false);
            setSize(600,400);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel panel = new JPanel(new GridBagLayout());
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            add(scrollPane, BorderLayout.CENTER);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1;


            //hang dau tien(j=3)

            JLabel labelname = new JLabel("Tên Sự Kiện");
            GridBagConstraints gbclabelname=new GridBagConstraints();
            gbclabelname.gridx=0;
            gbclabelname.gridy=3;
            gbclabelname.weightx=0;
            gbclabelname.anchor=GridBagConstraints.EAST;
            panel.add(labelname,gbclabelname);

            JTextField eventNameField = new JTextField(15);
            GridBagConstraints gbc1 = new GridBagConstraints();
            gbc1.gridy = 3;
            gbc1.gridx = 1;
            gbc1.gridwidth = 1;
            gbc1.weightx = 0.3;
            gbc1.fill = GridBagConstraints.HORIZONTAL;
            panel.add(eventNameField, gbc1);

            
            
            JLabel labeldiadiem = new JLabel("Địa Điểm Tổ Chức");
            GridBagConstraints gbclabel1=new GridBagConstraints();
            gbclabel1.gridx=2;
            gbclabel1.gridy=3;
            gbclabel1.weightx=0;
            gbclabel1.anchor=GridBagConstraints.WEST;
            panel.add(labeldiadiem,gbclabel1);


            JComboBox<String>chon_dia_diem_tc = new JComboBox<>();
            chon_dia_diem_tc.setPreferredSize(new Dimension(120,20));
            GridBagConstraints gbc2=new GridBagConstraints();
            gbc2.gridx = 3;
            gbc2.gridy = 3;
            gbc2.weightx = 0.7;
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            panel.add(chon_dia_diem_tc,gbc2);

            //ngang cach
            JPanel hr1 = new JPanel();
            GridBagConstraints gbchr1 = new GridBagConstraints();
            gbchr1.gridx = 0;
            gbchr1.gridy = 4;
            gbchr1.weightx = 1.0;
            gbchr1.gridwidth = GridBagConstraints.REMAINDER;
            gbchr1.fill = GridBagConstraints.HORIZONTAL;
            panel.add(hr1, gbchr1);

            //hang thu 2(j=5)

            JLabel labelcasi = new JLabel("Chọn ca sĩ/Nghệ sĩ ");
            GridBagConstraints gbclabelcasi = new GridBagConstraints();
            gbclabelcasi.gridx=0;
            gbclabelcasi.gridy=5;
            gbclabelcasi.weightx=0;
            gbclabelcasi.anchor=GridBagConstraints.WEST;
            panel.add(labelcasi,gbclabelcasi);



            GridBagConstraints gbcchoncasi = new GridBagConstraints();
            gbcchoncasi.gridx=1;
            gbcchoncasi.gridy=5;
            gbcchoncasi.weightx=0.5;
            gbcchoncasi.anchor=GridBagConstraints.WEST;
            for(int i=0;i<=socanghesi;i++)
            {
                JCheckBox cb = new JCheckBox("test"+i);
                GridBagConstraints gbceachcheckbox = new GridBagConstraints();
                gbceachcheckbox.gridx=1;
                gbceachcheckbox.gridy=5+i;
                gbceachcheckbox.weightx=0.5;
                gbceachcheckbox.anchor=GridBagConstraints.WEST;
                panel.add(cb,gbceachcheckbox);
            }




            JLabel labelthoigiantc = new JLabel("Thời Gian Tổ Chức");
            GridBagConstraints gbclabeltgtc=new GridBagConstraints();
            gbclabeltgtc.gridx=2;
            gbclabeltgtc.gridy=5;
            gbclabeltgtc.weightx=0;
            gbclabeltgtc.anchor=GridBagConstraints.WEST;
            panel.add(labelthoigiantc,gbclabeltgtc);


            JComboBox<String>chon_thoi_gian_tc = new JComboBox<>();
            chon_thoi_gian_tc.setPreferredSize(new Dimension(120,20));


            int startHour = 8;   // 8:00 AM
            int endHour = 22;    // 22:00 PM
            int interval = 10;   // mỗi 10 phút

            for (int h = startHour; h <= endHour; h++) {
                for (int m = 0; m < 60; m += interval) {
                    if (h == endHour && m > 0) break; // dừng khi vượt quá 22:00
                    String timeStr;
                    if(h<12)
                    {
                        timeStr = String.format("%02d:%02d AM", h, m);
                    }
                    else
                    {
                        timeStr = String.format("%02d:%02d PM", h, m);
                    }
                    chon_thoi_gian_tc.addItem(timeStr);
                }
            }


            GridBagConstraints gbctgtc=new GridBagConstraints();
            gbctgtc.gridx = 3;
            gbctgtc.gridy = 5;
            gbctgtc.weightx = 0.;
            gbctgtc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(chon_thoi_gian_tc,gbctgtc);


            //ngang cach
            JPanel hr2 = new JPanel();
            GridBagConstraints gbchr2 = new GridBagConstraints();
            gbchr2.gridx = 0;
            gbchr2.gridy = 6+socanghesi;
            gbchr2.weightx = 1.0;
            gbchr2.gridwidth = GridBagConstraints.REMAINDER;
            gbchr2.fill = GridBagConstraints.HORIZONTAL;
            panel.add(hr2, gbchr2);

            //hang thu 3 (j=7)

            JLabel labelngaytc = new JLabel("Ngày Tổ Chức");
            GridBagConstraints gbclabelngaytc=new GridBagConstraints();
            gbclabelngaytc.gridx=0;
            gbclabelngaytc.gridy=7+socanghesi;
            gbclabelngaytc.weightx=0;
            gbclabelngaytc.anchor=GridBagConstraints.WEST;
            panel.add(labelngaytc,gbclabelngaytc);

            JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
            dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
            GridBagConstraints gbcdatepicker=new GridBagConstraints();
            gbcdatepicker.gridx=1;
            gbcdatepicker.gridy=7+socanghesi;
            gbcdatepicker.weightx=0.5;
            panel.add(dateSpinner,gbcdatepicker);


            JLabel labeldoitochuc = new JLabel("Đội phụ trách");
            GridBagConstraints gbclabeldoitochuc=new GridBagConstraints();
            gbclabeldoitochuc.gridx=2;
            gbclabeldoitochuc.gridy=7+socanghesi;
            gbclabeldoitochuc.weightx=0;
            gbclabeldoitochuc.anchor=GridBagConstraints.WEST;
            panel.add(labeldoitochuc,gbclabeldoitochuc);


            JComboBox<String>id_doi_phu_trach=new JComboBox<>(iddoi);
            id_doi_phu_trach.setPreferredSize(new Dimension(120,20));
            GridBagConstraints gbciddoi = new GridBagConstraints();
            gbciddoi.gridx=3;
            gbciddoi.gridy=7+socanghesi;
            gbciddoi.weightx=1;
            gbciddoi.fill=GridBagConstraints.HORIZONTAL;
            panel.add(id_doi_phu_trach);

            //ngang cach
            JPanel hr4 = new JPanel();
            GridBagConstraints gbchr4 = new GridBagConstraints();
            gbchr4.gridx = 0;
            gbchr4.gridy = 8+socanghesi;
            gbchr4.weightx = 1.0;
            gbchr4.gridwidth = GridBagConstraints.REMAINDER;
            gbchr4.fill = GridBagConstraints.HORIZONTAL;
            panel.add(hr4, gbchr4);

            //hang thu 4 (j=9)

            JLabel labelThoiluongct = new JLabel("Thời lượng chương trình");
            GridBagConstraints gbclabelthoiluong=new GridBagConstraints();
            gbclabelthoiluong.gridx=0;
            gbclabelthoiluong.gridy=9+socanghesi;
            gbclabelthoiluong.weightx=0;
            gbclabelthoiluong.anchor=GridBagConstraints.WEST;
            panel.add(labelThoiluongct,gbclabelthoiluong);

            JComboBox<String>thoiluongctrinh=new JComboBox<>(tlctrinh);
            thoiluongctrinh.setPreferredSize(new Dimension(120,20));
            GridBagConstraints gbctlct = new GridBagConstraints();
            gbctlct.gridx=1;
            gbctlct.gridy=9+socanghesi;
            gbctlct.weightx=1;
            gbctlct.fill=GridBagConstraints.HORIZONTAL;
            panel.add(thoiluongctrinh,gbctlct);

            JLabel labelsoluongtietmuc = new JLabel("Số lượng tiết mục");
            GridBagConstraints gbclabelsltietmuc=new GridBagConstraints();
            gbclabelsltietmuc.gridx=2;
            gbclabelsltietmuc.gridy=9+socanghesi;
            gbclabelsltietmuc.weightx=0;
            gbclabelsltietmuc.anchor=GridBagConstraints.WEST;
            panel.add(labelsoluongtietmuc,gbclabelsltietmuc);

            JTextField eventcontentsumary = new JTextField(9);
            GridBagConstraints gbceventsumary = new GridBagConstraints();
            gbceventsumary.gridx = 3;
            gbceventsumary.gridy = 9+socanghesi;
            gbceventsumary.gridwidth = 1;
            gbceventsumary.weightx = 0.3;
            gbceventsumary.fill = GridBagConstraints.HORIZONTAL;
            panel.add(eventcontentsumary, gbceventsumary);


        



            //ngang cach
            JPanel hr8 = new JPanel();
            GridBagConstraints gbchr3 = new GridBagConstraints();
            gbchr3.gridx = 0;
            gbchr3.gridy = 12+socanghesi;
            gbchr3.weightx = 1.0;
            gbchr3.gridwidth = GridBagConstraints.REMAINDER;
            gbchr3.fill = GridBagConstraints.HORIZONTAL;
            panel.add(hr8, gbchr3);





            JPanel cangiuaPanel = new JPanel();
            GridBagConstraints cangiua=new GridBagConstraints();
            cangiua.gridx=0;
            cangiua.gridy=13+socanghesi+1;
            cangiua.weightx=0.4;
            panel.add(cangiuaPanel,cangiua);


            JPanel buttonPanel = new JPanel();
            saveButton = new JButton("Lưu");
            cancelButton = new JButton("Hủy");
            GridBagConstraints callbackend=new GridBagConstraints();
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            callbackend.gridy = 13+socanghesi+1;
            callbackend.weightx=0.6;
            callbackend.anchor=GridBagConstraints.EAST;
            panel.add(buttonPanel, callbackend);

            cancelButton.addActionListener(e -> dispose()); // đóng dialog

            add(panel);

        }
    }





    public static class ArtistDialog extends JDialog
    {
        public ArtistDialog(JFrame parent)
        {


            super(parent,"Quản lý dữ liệu ca /nghệ sĩ",false);
            setSize(800,600);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel ContentContainer = new JPanel(new BorderLayout());




            ArtistService artists = new ArtistService();
            Map <String, ArtistService.nghesi> Mapnghesi = new HashMap<>();
            List<String> ds_idnghesi = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();





            JPanel themnghesi = new JPanel(new GridBagLayout());
            themnghesi.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            themnghesi.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));
            GridBagConstraints themnghesi_bgc = new GridBagConstraints();

            JLabel Label_ID_them = new JLabel("ID ca / nghệ sĩ sẽ thêm:");
            Label_ID_them.setPreferredSize(new Dimension(300,20));
            themnghesi_bgc.weightx = 0;
            themnghesi_bgc.gridx=0;
            themnghesi_bgc.gridy=0;
            themnghesi_bgc.weightx = 0;
            themnghesi.add(Label_ID_them,themnghesi_bgc);

            JTextField ID_them = new JTextField();
            ID_them.setPreferredSize(new Dimension(450,20));
            ID_them.setName("ID_them");
            themnghesi_bgc.gridx=1;
            themnghesi_bgc.gridy=0;
            themnghesi_bgc.weightx = 1.0;
            themnghesi.add(ID_them,themnghesi_bgc);

            JLabel Label_ten_them = new JLabel("Tên ca / nghệ sĩ:");
            Label_ten_them.setPreferredSize(new Dimension(300,20));
            themnghesi_bgc.gridx=0;
            themnghesi_bgc.gridy=3;
            themnghesi_bgc.weightx = 0;
            themnghesi.add(Label_ten_them,themnghesi_bgc);

            JTextField ten_them = new JTextField();
            ten_them.setPreferredSize(new Dimension(450,20));
            ten_them.setName("ten_them");
            themnghesi_bgc.gridx=1;
            themnghesi_bgc.gridy=3;
            themnghesi_bgc.weightx = 1.0;
            themnghesi.add(ten_them,themnghesi_bgc);

            JLabel Label_ten_congty_them = new JLabel("Tên của công ty phụ trách cho ca / nghệ sĩ:");
            Label_ten_congty_them.setPreferredSize(new Dimension(300,20));
            themnghesi_bgc.gridx=0;
            themnghesi_bgc.gridy=5;
            themnghesi_bgc.weightx = 0;
            themnghesi.add(Label_ten_congty_them,themnghesi_bgc);

            JTextField ten_congty_them = new JTextField();
            ten_congty_them.setPreferredSize(new Dimension(450,20));
            ten_congty_them.setName("ten_congty_them");
            themnghesi_bgc.gridx=1;
            themnghesi_bgc.gridy=5;
            themnghesi_bgc.weightx = 1.0;
            themnghesi.add(ten_congty_them,themnghesi_bgc);

            JLabel Label_gia_motbuoi = new JLabel("Giá thành 1 buổi diễn của ca / nghệ sĩ(đ):");
            Label_gia_motbuoi.setPreferredSize(new Dimension(300,20));
            themnghesi_bgc.gridx=0;
            themnghesi_bgc.gridy=7;
            themnghesi_bgc.weightx = 0;
            themnghesi.add(Label_gia_motbuoi,themnghesi_bgc);

            JTextField gia_mot_buoi_dien = new JTextField();
            gia_mot_buoi_dien.setPreferredSize(new Dimension(450,20));
            gia_mot_buoi_dien.setName("gia_motbuoi_dien");
            themnghesi_bgc.gridx=1;
            themnghesi_bgc.gridy=7;
            themnghesi_bgc.weightx = 1.0;
            themnghesi.add(gia_mot_buoi_dien,themnghesi_bgc);

            JLabel Label_danhsach_tietmuc = new JLabel("id các tiết mục(Cách nhau bằng dấu phẩy ','):");
            Label_danhsach_tietmuc.setPreferredSize(new Dimension(300,20));
            themnghesi_bgc.gridx=0;
            themnghesi_bgc.gridy=9;
            themnghesi_bgc.weightx = 0;
            themnghesi.add(Label_danhsach_tietmuc,themnghesi_bgc);

            JTextField danhsach_tietmuc = new JTextField();
            danhsach_tietmuc.setPreferredSize(new Dimension(450,20));
            danhsach_tietmuc.setName("ds_tietmuc");
            themnghesi_bgc.gridx=1;
            themnghesi_bgc.gridy=9;
            themnghesi_bgc.weightx = 1.0;
            themnghesi.add(danhsach_tietmuc,themnghesi_bgc);

            ContentContainer.add(themnghesi,BorderLayout.NORTH);



            JPanel A_MainContainer = new JPanel();
            A_MainContainer.setLayout(new BoxLayout(A_MainContainer, BoxLayout.Y_AXIS));


            refresh(A_MainContainer, dspanel, quanlyselect, ds_idnghesi);



            JScrollPane scrollbar = new JScrollPane(A_MainContainer);
            scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            ContentContainer.add(scrollbar,BorderLayout.CENTER);





            JPanel A_ButtonContainer = new JPanel(new GridBagLayout());
            GridBagConstraints A_ButtonGBC = new GridBagConstraints();
            A_ButtonGBC.gridx=0;
            A_ButtonGBC.gridy=GridBagConstraints.RELATIVE;
            A_ButtonGBC.insets = new Insets(5,0,5,0);

            JButton refreshButton = new JButton("Làm mới");
            refreshButton.setPreferredSize(new Dimension(200,60));
            JButton addButton = new JButton("Thêm");
            addButton.setPreferredSize(new Dimension(200,60));
            JButton deletehButton = new JButton("Xóa");
            deletehButton.setPreferredSize(new Dimension(200,60));
            JButton changeButton = new JButton("Sửa");
            changeButton.setPreferredSize(new Dimension(200,60));



            deletehButton.addActionListener(e ->
            {
                goixoanghesi(dspanel);
                refresh(A_MainContainer, dspanel, quanlyselect, ds_idnghesi);
            });

            refreshButton.addActionListener(e->
            {
                refresh(A_MainContainer, dspanel, quanlyselect, ds_idnghesi);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });




            A_ButtonContainer.add(refreshButton,A_ButtonGBC);
            A_ButtonContainer.add(addButton,A_ButtonGBC);
            A_ButtonContainer.add(deletehButton,A_ButtonGBC);
            A_ButtonContainer.add(changeButton,A_ButtonGBC);


            ContentContainer.add(A_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoanghesi(Map<String,JPanel> dulieutruyen)
        {
            List<String>id_can_xoa =new ArrayList<>();

            for(Map.Entry<String,JPanel> entry : dulieutruyen.entrySet())
            {
                String id = entry.getKey();
                JPanel trangthai = entry.getValue();

                for(Component c: trangthai.getComponents())
                {
                    if(c instanceof JCheckBox)
                    {
                        JCheckBox checkbox = (JCheckBox) c;
                        if(checkbox.isSelected())
                        {
                            id_can_xoa.add(id);
                        }
                    }
                }
            }
            MainFunction.Artist_function.artistdeleter(id_can_xoa);
        }

        protected void refresh(JPanel A_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_idnghesi)
        {
            A_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_idnghesi.clear();

            ArtistService artist = new ArtistService();
            Map<String,ArtistService.nghesi> dsnghesi = artist.hienthitatcanghesi();
            ds_idnghesi.addAll(dsnghesi.keySet());



            for(String id : ds_idnghesi)
            {
                JPanel khungNgheSi = MainFunction.Artist_taoKhung(id);
                khungNgheSi.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                khungNgheSi.setAlignmentX(Component.LEFT_ALIGNMENT);


                JCheckBox checkbox = new JCheckBox();
                checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                quanlyselect.put(id,checkbox);

                JPanel khungchucnang = new JPanel(new BorderLayout());
                khungchucnang.add(checkbox,BorderLayout.WEST);
                khungchucnang.add(khungNgheSi,BorderLayout.CENTER);
                dspanel.put(id,khungchucnang);

                A_MainContainer.add(khungchucnang);
            }

            A_MainContainer.revalidate();
            A_MainContainer.repaint();
        }
        protected String get_adding_information(JPanel themnghesi,String name)
        {
            for(Component c : themnghesi.getComponents())
            {
                if(c instanceof JTextField && name.equals(c.getName()))
                {
                    return ((JTextField) c).getText();
                }
            }
            return null;
        }
    }
}
