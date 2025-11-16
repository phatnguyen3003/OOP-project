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

public class artist_manage {
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



            
            JLabel label = new JLabel("Dữ liệu nghệ sĩ trong hệ thống", SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 20));

            ContentContainer.add(label, BorderLayout.NORTH);



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

            changeButton.addActionListener(e->
            {
                goisuanghesi(dspanel, () -> {
                    refresh(A_MainContainer, dspanel, quanlyselect, ds_idnghesi);
                });
            });



            /*
             * 1: success
             * 101: duplicate id
             * 102: special character in price
             */

            addButton.addActionListener(e->
            {
                MainFunction.function.createAddDialog(null, 1);
                 refresh(A_MainContainer, dspanel, quanlyselect, ds_idnghesi);
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
            MainFunction.function.deleter(id_can_xoa,1);
        }

        protected void refresh(JPanel A_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_idnghesi)
        {
            A_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_idnghesi.clear();

            ArtistService.Danhsachnghesi artist = new ArtistService.Danhsachnghesi();
            Map<String,ArtistService.nghesi> dsnghesi = artist.xuat();
            ds_idnghesi.addAll(dsnghesi.keySet());



            for(String id : ds_idnghesi)
            {
                JPanel khungNgheSi = MainFunction.taoKhung(id,1,null,null);
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

        protected static String get_add_information(JPanel themnghesi,String name)
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







        protected String get_configure_information(JPanel themnghesi,String name)
        {
            for(Component c : themnghesi.getComponents())
            {
                if(c instanceof JLabel && name.equals(c.getName()))
                {
                    JLabel label = (JLabel) c;
                    String text = label.getText();

                    int index = text.indexOf(":");
                    if(index!=-1 && index+1<text.length())
                    {
                        return text.substring(index+1).trim();
                    }
                    else
                    {
                        return text.trim();
                    }
                }
            }
            return null;
        }


        protected void configWindow(List<String>id_casi, Runnable refresh)
        {
            JDialog cogfigureWindow = new JDialog(this,"Giao diện sửa",true);
            cogfigureWindow.setSize(400,400);
            cogfigureWindow.setLocationRelativeTo(this);

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: id_casi)
            {
                JPanel paneltam = MainFunction.taoKhung(id,1,null,null);

                String id_nghesi = get_configure_information(paneltam,"id_artist");
                String ten_nghesi = get_configure_information(paneltam,"name_artist");
                String ten_congty = get_configure_information(paneltam,"congty");
                String giabieudien = get_configure_information(paneltam,"giathanh");
                String id_cac_tiet_muc = get_configure_information(paneltam,"idtietmuc");

                JPanel khung_sua_nghe_Si = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx=0;
                gbc.gridy= GridBagConstraints.RELATIVE;
                gbc.weightx =1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;


                JLabel labelidnghesi = new JLabel("id:"+id_nghesi);
                gbc.gridx=0;
                gbc.gridy= 0;
                labelidnghesi.setName("id");
                khung_sua_nghe_Si.add(labelidnghesi,gbc);


                JLabel label_id = new JLabel("Tên nghệ sĩ: ");
                gbc.gridx=0;
                gbc.gridy= 1;
                gbc.weightx=0;
                khung_sua_nghe_Si.add(label_id,gbc);

                JTextField inputtennghesi = new JTextField(ten_nghesi);
                gbc.gridx=1;
                gbc.gridy= 1;
                gbc.weightx=1;
                khung_sua_nghe_Si.add(inputtennghesi,gbc);

                JLabel label_vaitro = new JLabel("Vai trò: ");
                gbc.gridx=0;
                gbc.gridy= 2;
                gbc.weightx=0;
                khung_sua_nghe_Si.add(label_vaitro,gbc);

                JTextField inputlabel_vaitro = new JTextField(ten_nghesi);
                gbc.gridx=1;
                gbc.gridy= 2;
                gbc.weightx=1;
                khung_sua_nghe_Si.add(inputlabel_vaitro,gbc);


                JLabel label_contgty = new JLabel("Công ty quản lý: ");
                gbc.gridx=0;
                gbc.gridy= 3;
                gbc.weightx=0;

                khung_sua_nghe_Si.add(label_contgty,gbc);

                JTextField inputcongty = new JTextField(ten_congty);
                gbc.gridx=1;
                gbc.gridy= 3;
                gbc.weightx=1;
                khung_sua_nghe_Si.add(inputcongty,gbc);


                JLabel label_gia_1 = new JLabel("Giá một buổi diễn: ");
                gbc.gridx=0;
                gbc.gridy= 4;
                gbc.weightx=0;
                khung_sua_nghe_Si.add(label_gia_1,gbc);
                
                JTextField inputgia = new JTextField(giabieudien);
                gbc.gridx=1;
                gbc.gridy= 4;
                gbc.weightx=0.9;
                khung_sua_nghe_Si.add(inputgia,gbc);

                JLabel label_gia_2 = new JLabel(" đ");
                gbc.gridx=2;
                gbc.gridy= 4;
                gbc.weightx=0.1;
                khung_sua_nghe_Si.add(label_gia_2,gbc);



                JLabel label_id_tietmuc = new JLabel("ID các tiết mục có thể biểu diễn: ");
                gbc.gridx=0;
                gbc.gridy= 5;
                gbc.weightx=0;
                khung_sua_nghe_Si.add(label_id_tietmuc,gbc);

                JTextField inputidtietmuc = new JTextField(id_cac_tiet_muc);
                gbc.gridx=1;
                gbc.gridy= 5;
                gbc.weightx=1;
                khung_sua_nghe_Si.add(inputidtietmuc,gbc);

                Container.add(khung_sua_nghe_Si);
            }

            JButton saveButton = new JButton("Lưu thay đổi");
            saveButton.addActionListener(e -> {
                MainFunction.function.configurer(Container,1);
                JOptionPane.showMessageDialog(cogfigureWindow, "Đã lưu thay đổi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                if (refresh != null) {
                    refresh.run();
                }
                cogfigureWindow.dispose();
            });


            JScrollPane scrollPane = new JScrollPane(Container);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            cogfigureWindow.add(scrollPane, BorderLayout.CENTER);
            cogfigureWindow.add(saveButton, BorderLayout.SOUTH);
            cogfigureWindow.setVisible(true);
        }

        protected void goisuanghesi(Map<String,JPanel> dulieutruyen,Runnable refreshAction)
        {
            List<String>id_can_sua =new ArrayList<>();

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
                            id_can_sua.add(id);
                        }
                    }
                }
            }
            configWindow(id_can_sua, refreshAction);
        }

    }
}
