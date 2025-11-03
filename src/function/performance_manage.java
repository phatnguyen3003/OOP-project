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
import services.PerformanceService;
public class performance_manage {

    public static class PerformanceDialog extends JDialog
    {
        public PerformanceDialog(JFrame parent)
        {


            super(parent,"Quản lý dữ liệu Tiết mục",false);
            setSize(800,600);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel ContentContainer = new JPanel(new BorderLayout());




            PerformanceService performance = new PerformanceService();
            Map <String, PerformanceService.tietmuc> Maptietmuc = new HashMap<>();
            List<String> ds_idtietmuc = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();



            


            JPanel themtietmuc = new JPanel(new GridBagLayout());
            themtietmuc.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            themtietmuc.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));
            GridBagConstraints themtietmuc_bgc = new GridBagConstraints();

            JLabel Label_ID_them = new JLabel("ID tiết mục:");
            Label_ID_them.setPreferredSize(new Dimension(300,20));
            themtietmuc_bgc.weightx = 0;
            themtietmuc_bgc.gridx=0;
            themtietmuc_bgc.gridy=0;
            themtietmuc_bgc.weightx = 0;
            themtietmuc.add(Label_ID_them,themtietmuc_bgc);

            JTextField ID_them = new JTextField();
            ID_them.setPreferredSize(new Dimension(450,20));
            ID_them.setName("ID_them");
            themtietmuc_bgc.gridx=1;
            themtietmuc_bgc.gridy=0;
            themtietmuc_bgc.weightx = 1.0;
            themtietmuc.add(ID_them,themtietmuc_bgc);

            JLabel Label_ten_them = new JLabel("Tên tiết mục:");
            Label_ten_them.setPreferredSize(new Dimension(300,20));
            themtietmuc_bgc.gridx=0;
            themtietmuc_bgc.gridy=3;
            themtietmuc_bgc.weightx = 0;
            themtietmuc.add(Label_ten_them,themtietmuc_bgc);

            JTextField ten_them = new JTextField();
            ten_them.setPreferredSize(new Dimension(450,20));
            ten_them.setName("ten_them");
            themtietmuc_bgc.gridx=1;
            themtietmuc_bgc.gridy=3;
            themtietmuc_bgc.weightx = 1.0;
            themtietmuc.add(ten_them,themtietmuc_bgc);

            JLabel Label_thoiluong_them = new JLabel("Thời lượng:");
            Label_thoiluong_them.setPreferredSize(new Dimension(300,20));
            themtietmuc_bgc.gridx=0;
            themtietmuc_bgc.gridy=5;
            themtietmuc_bgc.weightx = 0;
            themtietmuc.add(Label_thoiluong_them,themtietmuc_bgc);

            JTextField thoiluong_them = new JTextField();
            thoiluong_them.setPreferredSize(new Dimension(450,20));
            thoiluong_them.setName("thoi_luong");
            themtietmuc_bgc.gridx=1;
            themtietmuc_bgc.gridy=5;
            themtietmuc_bgc.weightx = 1.0;
            themtietmuc.add(thoiluong_them,themtietmuc_bgc);

            ContentContainer.add(themtietmuc,BorderLayout.NORTH);



            JPanel B_MainContainer = new JPanel();
            B_MainContainer.setLayout(new BoxLayout(B_MainContainer, BoxLayout.Y_AXIS));


            refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);



            JScrollPane scrollbar = new JScrollPane(B_MainContainer);
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
                goixoatietmuc(dspanel);
                refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);
            });

            refreshButton.addActionListener(e->
            {
                refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });

            changeButton.addActionListener(e->
            {
                goisuanghesi(dspanel, () -> {
                    refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);
                });
            });



            /*
             * 1: success
             * 101: duplicate id
             * 102: special character in price
             */

            addButton.addActionListener(e->
            {
                String id = get_add_information(themtietmuc,"ID_them");                
                String ten = get_add_information(themtietmuc,"ten_them");
                String thoi_luong = get_add_information(themtietmuc,"thoi_luong");

                if(id==null||ten==null||thoi_luong==null||id.isEmpty()||ten.isEmpty()||thoi_luong.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin","thiếu dữ liệu",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int checked = MainFunction.function.performance_adding(id,ten,thoi_luong,ds_idtietmuc);

                StringBuilder message = new StringBuilder();
                if(checked==1)
                {
                    message.append("Đã thêm tiết mục thành công!");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(checked== 201)
                {
                    message.append("ID của Tiết mục không được trùng với ID đã có sẵn");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                else if (checked==202)
                {
                    message.append("Thời lượng tiết mục không thể có ký tự khác ngoài số");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    message.append("Xảy ra lỗi trong lúc thêm tiết mục");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);
            });



            A_ButtonContainer.add(refreshButton,A_ButtonGBC);
            A_ButtonContainer.add(addButton,A_ButtonGBC);
            A_ButtonContainer.add(deletehButton,A_ButtonGBC);
            A_ButtonContainer.add(changeButton,A_ButtonGBC);


            ContentContainer.add(A_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoatietmuc(Map<String,JPanel> dulieutruyen)
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
            MainFunction.function.deleter(id_can_xoa,2);
        }

        protected void refresh(JPanel B_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_idtietmuc)
        {
            B_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_idtietmuc.clear();

            PerformanceService performance = new PerformanceService();
            Map<String,PerformanceService.tietmuc> ds_tietmuc = performance.xuat();
            ds_idtietmuc.addAll(ds_tietmuc.keySet());



            for(String id : ds_idtietmuc)
            {
                JPanel khungTietmuc = MainFunction.taoKhung(id,2);
                khungTietmuc.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                khungTietmuc.setAlignmentX(Component.LEFT_ALIGNMENT);


                JCheckBox checkbox = new JCheckBox();
                checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                quanlyselect.put(id,checkbox);

                JPanel khungchucnang = new JPanel(new BorderLayout());
                khungchucnang.add(checkbox,BorderLayout.WEST);
                khungchucnang.add(khungTietmuc,BorderLayout.CENTER);
                dspanel.put(id,khungchucnang);

                B_MainContainer.add(khungchucnang);
            }

            B_MainContainer.revalidate();
            B_MainContainer.repaint();
        }

        protected static String get_add_information(JPanel them,String name)
        {
            for(Component c : them.getComponents())
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


        protected void configWindow(List<String>ds_id_tietmuc, Runnable refresh)
        {
            JDialog cogfigureWindow = new JDialog(this,"Giao diện sửa",true);
            cogfigureWindow.setSize(400,400);
            cogfigureWindow.setLocationRelativeTo(this);

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: ds_id_tietmuc)
            {
                JPanel paneltam = MainFunction.taoKhung(id,2);

                String id_tietmuc = get_configure_information(paneltam,"ID_them");
                String ten_tietmuc = get_configure_information(paneltam,"ten_them");
                String thoiluong_tietmuc = get_configure_information(paneltam,"thoi_luong");

                JPanel khung_sua_tietmuc = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx=0;
                gbc.gridy= GridBagConstraints.RELATIVE;
                gbc.weightx =1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;


                JLabel label_id_tietmuc = new JLabel("id:"+id);
                gbc.gridx=0;
                gbc.gridy= 0;
                label_id_tietmuc.setName("id");
                khung_sua_tietmuc.add(label_id_tietmuc,gbc);


                JLabel label_id = new JLabel("Tên tiết mục: ");
                gbc.gridx=0;
                gbc.gridy= 1;
                gbc.weightx=0;
                khung_sua_tietmuc.add(label_id,gbc);

                JTextField input_ten_tietmuc = new JTextField(ten_tietmuc);
                gbc.gridx=1;
                gbc.gridy= 1;
                gbc.weightx=1;
                khung_sua_tietmuc.add(input_ten_tietmuc,gbc);


                JLabel label_thoi_luong_1 = new JLabel("Thời lượng: ");
                gbc.gridx=0;
                gbc.gridy= 2;
                gbc.weightx=0;
                khung_sua_tietmuc.add(label_thoi_luong_1,gbc);
                
                JTextField input_thoiluong_tietmuc = new JTextField(thoiluong_tietmuc);
                gbc.gridx=1;
                gbc.gridy= 2;
                gbc.weightx=0.9;
                khung_sua_tietmuc.add(input_thoiluong_tietmuc,gbc);

                JLabel label_thoiluong_tietmuc_2 = new JLabel(" phút");
                gbc.gridx=2;
                gbc.gridy= 2;
                gbc.weightx=0.1;
                khung_sua_tietmuc.add(label_thoiluong_tietmuc_2,gbc);

                Container.add(khung_sua_tietmuc);
            }

            JButton saveButton = new JButton("Lưu thay đổi");
            saveButton.addActionListener(e -> {
                MainFunction.function.configurer(Container,2);
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
