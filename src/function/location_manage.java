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


import services.locationService;
import services.MainFunction;




public class location_manage {

  public static class LocationDialog extends JDialog
    {
        public LocationDialog(JFrame parent)
        {


            super(parent,"Quản lý dữ liệu địa điểm",false);
            setSize(800,600);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel ContentContainer = new JPanel(new BorderLayout());




            locationService location = new locationService();
            Map <String, locationService.location> Mapdiadiem = new HashMap<>();
            List<String> ds_iddiadiem = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();



            


            JPanel themdiadiem = new JPanel(new GridBagLayout());
            themdiadiem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            themdiadiem.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));
            GridBagConstraints themdiadiem_bgc = new GridBagConstraints();

            JLabel Label_ID_them = new JLabel("ID địa điểm:");
            Label_ID_them.setPreferredSize(new Dimension(300,20));
            themdiadiem_bgc.weightx = 0;
            themdiadiem_bgc.gridx=0;
            themdiadiem_bgc.gridy=0;
            themdiadiem_bgc.weightx = 0;
            themdiadiem.add(Label_ID_them,themdiadiem_bgc);

            JTextField ID_them = new JTextField();
            ID_them.setPreferredSize(new Dimension(450,20));
            ID_them.setName("ID_them");
            themdiadiem_bgc.gridx=1;
            themdiadiem_bgc.gridy=0;
            themdiadiem_bgc.weightx = 1.0;
            themdiadiem.add(ID_them,themdiadiem_bgc);

            JLabel Label_ten_them = new JLabel("Tên địa điểm:");
            Label_ten_them.setPreferredSize(new Dimension(300,20));
            themdiadiem_bgc.gridx=0;
            themdiadiem_bgc.gridy=3;
            themdiadiem_bgc.weightx = 0;
            themdiadiem.add(Label_ten_them,themdiadiem_bgc);

            JTextField ten_them = new JTextField();
            ten_them.setPreferredSize(new Dimension(450,20));
            ten_them.setName("ten_them");
            themdiadiem_bgc.gridx=1;
            themdiadiem_bgc.gridy=3;
            themdiadiem_bgc.weightx = 1.0;
            themdiadiem.add(ten_them,themdiadiem_bgc);

            JLabel Label_succhua_them_1 = new JLabel("Sức chứa:");
            Label_succhua_them_1.setPreferredSize(new Dimension(300,20));
            themdiadiem_bgc.gridx=0;
            themdiadiem_bgc.gridy=5;
            themdiadiem_bgc.weightx = 0;
            themdiadiem.add(Label_succhua_them_1,themdiadiem_bgc);

            JTextField succhua_them = new JTextField();
            succhua_them.setPreferredSize(new Dimension(450,20));
            succhua_them.setName("suc_chua");
            themdiadiem_bgc.gridx=1;
            themdiadiem_bgc.gridy=5;
            themdiadiem_bgc.weightx = 1.0;
            themdiadiem.add(succhua_them,themdiadiem_bgc);

            ContentContainer.add(themdiadiem,BorderLayout.NORTH);



            JPanel D_MainContainer = new JPanel();
            D_MainContainer.setLayout(new BoxLayout(D_MainContainer, BoxLayout.Y_AXIS));


            refresh(D_MainContainer, dspanel, quanlyselect, ds_iddiadiem);



            JScrollPane scrollbar = new JScrollPane(D_MainContainer);
            scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            ContentContainer.add(scrollbar,BorderLayout.CENTER);





            JPanel D_ButtonContainer = new JPanel(new GridBagLayout());
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
                goixoadiadiem(dspanel);
                refresh(D_MainContainer, dspanel, quanlyselect, ds_iddiadiem);
            });

            refreshButton.addActionListener(e->
            {
                refresh(D_MainContainer, dspanel, quanlyselect, ds_iddiadiem);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });

            changeButton.addActionListener(e->
            {
                goisuadiadiem(dspanel, () -> {
                    refresh(D_MainContainer, dspanel, quanlyselect, ds_iddiadiem);
                });
            });



            /*
             * 1: success
             * 401: duplicate id
             * 402: special character in container_capapility
             */

            addButton.addActionListener(e->
            {
                String id = get_add_information(themdiadiem,"ID_them");                
                String ten = get_add_information(themdiadiem,"ten_them");
                String thoi_luong = get_add_information(themdiadiem,"suc_chua");

                if(id==null||ten==null||thoi_luong==null||id.isEmpty()||ten.isEmpty()||thoi_luong.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin","thiếu dữ liệu",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int checked = MainFunction.function.performance_adding(id,ten,thoi_luong,ds_iddiadiem);

                StringBuilder message = new StringBuilder();
                if(checked==1)
                {
                    message.append("Đã thêm địa điểm thành công!");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(checked== 401)
                {
                    message.append("ID của địa điểm không được trùng với ID đã có sẵn");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                else if (checked==402)
                {
                    message.append("Sức chứa của địa điểm không thể có ký tự khác ngoài số");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    message.append("Xảy ra lỗi trong lúc thêm địa điểm");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                refresh(D_MainContainer, dspanel, quanlyselect, ds_iddiadiem);
            });



            D_ButtonContainer.add(refreshButton,A_ButtonGBC);
            D_ButtonContainer.add(addButton,A_ButtonGBC);
            D_ButtonContainer.add(deletehButton,A_ButtonGBC);
            D_ButtonContainer.add(changeButton,A_ButtonGBC);


            ContentContainer.add(D_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoadiadiem(Map<String,JPanel> dulieutruyen)
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
            MainFunction.function.deleter(id_can_xoa,4);
        }

        protected void refresh(JPanel D_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_iddiadiem)
        {
            D_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_iddiadiem.clear();

            locationService.Danhsachdiadiem locations = new locationService.Danhsachdiadiem();
            Map<String,locationService.location> ds_diadiem = locations.xuat();
            ds_iddiadiem.addAll(ds_diadiem.keySet());



            for(String id : ds_iddiadiem)
            {
                JPanel khungDiadiem = MainFunction.taoKhung(id,4);
                khungDiadiem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                khungDiadiem.setAlignmentX(Component.LEFT_ALIGNMENT);


                JCheckBox checkbox = new JCheckBox();
                checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                quanlyselect.put(id,checkbox);

                JPanel khungchucnang = new JPanel(new BorderLayout());
                khungchucnang.add(checkbox,BorderLayout.WEST);
                khungchucnang.add(khungDiadiem,BorderLayout.CENTER);
                dspanel.put(id,khungchucnang);

                D_MainContainer.add(khungchucnang);
            }

            D_MainContainer.revalidate();
            D_MainContainer.repaint();
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


        protected void configWindow(List<String>ds_id_diadiem, Runnable refresh)
        {
            JDialog cogfigureWindow = new JDialog(this,"Giao diện sửa",true);
            cogfigureWindow.setSize(400,400);
            cogfigureWindow.setLocationRelativeTo(this);

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: ds_id_diadiem)
            {
                JPanel paneltam = MainFunction.taoKhung(id,2);

                String id_diadiem = get_configure_information(paneltam,"ID_them");
                String ten_diadiem = get_configure_information(paneltam,"ten_them");
                String thoiluong_diadiem = get_configure_information(paneltam,"suc_chua");

                JPanel khung_sua_diadiem = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx=0;
                gbc.gridy= GridBagConstraints.RELATIVE;
                gbc.weightx =1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;


                JLabel label_id_diadiem = new JLabel("id:"+id);
                gbc.gridx=0;
                gbc.gridy= 0;
                label_id_diadiem.setName("id");
                khung_sua_diadiem.add(label_id_diadiem,gbc);


                JLabel label_id = new JLabel("Tên địa điểm: ");
                gbc.gridx=0;
                gbc.gridy= 1;
                gbc.weightx=0;
                khung_sua_diadiem.add(label_id,gbc);

                JTextField input_ten_diadiem = new JTextField(ten_diadiem);
                gbc.gridx=1;
                gbc.gridy= 1;
                gbc.weightx=1;
                khung_sua_diadiem.add(input_ten_diadiem,gbc);


                JLabel label_suc_Chua_1 = new JLabel("Sức chứa: ");
                gbc.gridx=0;
                gbc.gridy= 2;
                gbc.weightx=0;
                khung_sua_diadiem.add(label_suc_Chua_1,gbc);
                
                JTextField input_succhua_diadiem = new JTextField(thoiluong_diadiem);
                gbc.gridx=1;
                gbc.gridy= 2;
                gbc.weightx=0.9;
                khung_sua_diadiem.add(input_succhua_diadiem,gbc);

                JLabel label_suc_Chua_2 = new JLabel(" khách");
                gbc.gridx=2;
                gbc.gridy= 2;
                gbc.weightx=0.1;
                khung_sua_diadiem.add(label_suc_Chua_2,gbc);

                Container.add(khung_sua_diadiem);
            }

            JButton saveButton = new JButton("Lưu thay đổi");
            saveButton.addActionListener(e -> {
                Map<String,Integer> checked = MainFunction.function.configurer(Container,4);
                StringBuilder message = new StringBuilder();
                int errorcheck=0;
                for(String id: ds_id_diadiem)
                {
                    int error = checked.get(id);
                    if(error == 402)
                    {
                        errorcheck+=1;
                        message.append(id);
                        message.append("==> Không thể sửa: sức chứa không thể chứa ký tự khác ngoài số");
                    }
                    else if(error==0)
                    {
                        errorcheck+=1;
                        message.append(id);
                        message.append("==> Không thể sửa: gặp lỗi khi thực hiện thao tác sửa");
                    }
                }
                if(errorcheck==0)
                {
                    message.append("Sửa thành công");
                }
            });


            JScrollPane scrollPane = new JScrollPane(Container);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            cogfigureWindow.add(scrollPane, BorderLayout.CENTER);
            cogfigureWindow.add(saveButton, BorderLayout.SOUTH);
            cogfigureWindow.setVisible(true);
        }

        protected void goisuadiadiem(Map<String,JPanel> dulieutruyen,Runnable refreshAction)
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

