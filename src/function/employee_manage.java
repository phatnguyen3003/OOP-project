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
import java.util.Vector;



import services.employeeService;
import services.MainFunction;
import services.teamService;




public class employee_manage {

  public static class EmployeeDialog extends JDialog
    {
        public EmployeeDialog(JFrame parent)
        {


            super(parent,"Quản lý dữ liệu nhân viên",false);
            setSize(800,600);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel ContentContainer = new JPanel(new BorderLayout());




            employeeService employee = new employeeService();
            Map <String, employeeService.nhanvien> Mapnhanvien = new HashMap<>();
            List<String> ds_idnhanvien = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();


            teamService.DanhsachDoi teams = new teamService.DanhsachDoi();
            Map<String,teamService.team> ds_doi = teams.xuat();
            List<String> ds_id_doi = new ArrayList<>(ds_doi.keySet());
            System.out.print("ds doc duoc: "+ ds_doi.keySet());

            


            JPanel themnhanvien = new JPanel(new GridBagLayout());
            themnhanvien.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            themnhanvien.setBorder(BorderFactory.createLineBorder(Color.GRAY,1,true));
            GridBagConstraints themnhanvien_bgc = new GridBagConstraints();

            JLabel Label_ID_them = new JLabel("ID nhân viên:");
            Label_ID_them.setPreferredSize(new Dimension(300,20));
            themnhanvien_bgc.weightx = 0;
            themnhanvien_bgc.gridx=0;
            themnhanvien_bgc.gridy=0;
            themnhanvien_bgc.weightx = 0;
            themnhanvien.add(Label_ID_them,themnhanvien_bgc);

            JTextField ID_them = new JTextField();
            ID_them.setPreferredSize(new Dimension(450,20));
            ID_them.setName("ID_them");
            themnhanvien_bgc.gridx=1;
            themnhanvien_bgc.gridy=0;
            themnhanvien_bgc.weightx = 1.0;
            themnhanvien.add(ID_them,themnhanvien_bgc);

            JLabel Label_ten_them = new JLabel("Tên nhân viên:");
            Label_ten_them.setPreferredSize(new Dimension(300,20));
            themnhanvien_bgc.gridx=0;
            themnhanvien_bgc.gridy=3;
            themnhanvien_bgc.weightx = 0;
            themnhanvien.add(Label_ten_them,themnhanvien_bgc);

            JTextField ten_them = new JTextField();
            ten_them.setPreferredSize(new Dimension(450,20));
            ten_them.setName("ten_them");
            themnhanvien_bgc.gridx=1;
            themnhanvien_bgc.gridy=3;
            themnhanvien_bgc.weightx = 1.0;
            themnhanvien.add(ten_them,themnhanvien_bgc);

            JLabel Label_succhua_them = new JLabel("ca làm việc:");
            Label_succhua_them.setPreferredSize(new Dimension(300,20));
            themnhanvien_bgc.gridx=0;
            themnhanvien_bgc.gridy=5;
            themnhanvien_bgc.weightx = 0;
            themnhanvien.add(Label_succhua_them,themnhanvien_bgc);

            String[] calam = {"sáng","chiều","tối"};
            JComboBox<String> optionmenu_calam = new JComboBox<>(calam);
            optionmenu_calam.setPreferredSize(new Dimension(450,30));
            optionmenu_calam.setName("ca_lam");
            themnhanvien_bgc.gridx=1;
            themnhanvien_bgc.gridy=5;
            themnhanvien_bgc.weightx = 1.0;
            themnhanvien.add(optionmenu_calam,themnhanvien_bgc);

          
         
            JLabel Label_chon_doi = new JLabel("Chọn đội phụ trách của nhân viên:");
            Label_chon_doi.setPreferredSize(new Dimension(300,20));
            themnhanvien_bgc.gridx=0;
            themnhanvien_bgc.gridy=7;
            themnhanvien_bgc.weightx = 0;
            themnhanvien.add(Label_chon_doi,themnhanvien_bgc);

            JComboBox<String> optionmenu_doi = new JComboBox<>(new Vector<>(ds_id_doi));
            optionmenu_doi.setName("option_menu_doi");
            themnhanvien_bgc.gridx=1;
            themnhanvien_bgc.gridy=7;
            themnhanvien_bgc.weightx = 1.0;
            themnhanvien.add(optionmenu_doi,themnhanvien_bgc);


            ContentContainer.add(themnhanvien,BorderLayout.NORTH);



            JPanel C_MainContainer = new JPanel();
            C_MainContainer.setLayout(new BoxLayout(C_MainContainer, BoxLayout.Y_AXIS));


            refresh(C_MainContainer, dspanel, quanlyselect, ds_idnhanvien);



            JScrollPane scrollbar = new JScrollPane(C_MainContainer);
            scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            ContentContainer.add(scrollbar,BorderLayout.CENTER);





            JPanel C_ButtonContainer = new JPanel(new GridBagLayout());
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
                goixoanhanvien(dspanel);
                refresh(C_MainContainer, dspanel, quanlyselect, ds_idnhanvien);
            });

            refreshButton.addActionListener(e->
            {
                refresh(C_MainContainer, dspanel, quanlyselect, ds_idnhanvien);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });

            changeButton.addActionListener(e->
            {
                goisuadiadiem(dspanel, () -> {
                    refresh(C_MainContainer, dspanel, quanlyselect, ds_idnhanvien);
                });
            });



            /*
             * 1: success
             * 301: duplicate id
             * 302: special character in container_capapility
             */

            addButton.addActionListener(e->
            {
                String id = get_add_information(themnhanvien,"ID_them");                
                String ten = get_add_information(themnhanvien,"ten_them");
                String ca_lam_viec_them = get_add_information(themnhanvien,"ca_lam");
                String id_doi_them = get_add_information(themnhanvien,"option_menu_doi");

                if(id==null||ten==null||ca_lam_viec_them==null||id_doi_them==null||id_doi_them.isEmpty()||id.isEmpty()||ten.isEmpty()||ca_lam_viec_them.isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đủ thông tin","thiếu dữ liệu",JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int checked = MainFunction.function.employee_adding(id, ten, ca_lam_viec_them, id_doi_them, ds_id_doi);

                StringBuilder message = new StringBuilder();
                if(checked==1)
                {
                    message.append("Đã thêm nhân viên thành công!");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(checked== 301)
                {
                    message.append("ID của nhân viên không được trùng với ID đã có sẵn");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    message.append("Xảy ra lỗi trong lúc thêm nhân viên");
                    JOptionPane.showMessageDialog(null,message,"Thông báo",JOptionPane.ERROR_MESSAGE);
                }
                refresh(C_MainContainer, dspanel, quanlyselect, ds_idnhanvien);
            });



            C_ButtonContainer.add(refreshButton,A_ButtonGBC);
            C_ButtonContainer.add(addButton,A_ButtonGBC);
            C_ButtonContainer.add(deletehButton,A_ButtonGBC);
            C_ButtonContainer.add(changeButton,A_ButtonGBC);


            ContentContainer.add(C_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoanhanvien(Map<String,JPanel> dulieutruyen)
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
            MainFunction.function.deleter(id_can_xoa,3);
        }

        protected void refresh(JPanel C_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_idnhanvien)
        {
            C_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_idnhanvien.clear();

            employeeService.Danhsachnhanvien employees = new employeeService.Danhsachnhanvien();
            Map<String,employeeService.nhanvien> ds_diadiem = employees.xuat();
            ds_idnhanvien.addAll(ds_diadiem.keySet());



            for(String id : ds_idnhanvien)
            {
                JPanel khungDiadiem = MainFunction.taoKhung(id,3);
                khungDiadiem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                khungDiadiem.setAlignmentX(Component.LEFT_ALIGNMENT);


                JCheckBox checkbox = new JCheckBox();
                checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                quanlyselect.put(id,checkbox);

                JPanel khungchucnang = new JPanel(new BorderLayout());
                khungchucnang.add(checkbox,BorderLayout.WEST);
                khungchucnang.add(khungDiadiem,BorderLayout.CENTER);
                dspanel.put(id,khungchucnang);

                C_MainContainer.add(khungchucnang);
            }

            C_MainContainer.revalidate();
            C_MainContainer.repaint();
        }

        protected static String get_add_information(JPanel them,String name)
        {
            for(Component c : them.getComponents())
            {
                if(c instanceof JTextField && name.equals(c.getName()))
                {
                    return ((JTextField) c).getText();
                }
                else if (c instanceof JComboBox<?> && name.equals(c.getName()))
                {
                    Object selected = ((JComboBox<?>) c).getSelectedItem();
                    return selected != null ? selected.toString() : null;
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
                    if(error == 302)
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

