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
            List<String> ds_id_nhanvien = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();


            teamService.DanhsachDoi teams = new teamService.DanhsachDoi();
            Map<String,teamService.team> ds_doi = teams.xuat();
            List<String> ds_id_doi = new ArrayList<>(ds_doi.keySet());

            


            JLabel label = new JLabel("Dữ liệu nhân viên", SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 20));

            ContentContainer.add(label, BorderLayout.NORTH);



            JPanel C_MainContainer = new JPanel();
            C_MainContainer.setLayout(new BoxLayout(C_MainContainer, BoxLayout.Y_AXIS));


            refresh(C_MainContainer, dspanel, quanlyselect, ds_id_nhanvien);



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
                refresh(C_MainContainer, dspanel, quanlyselect, ds_id_nhanvien);
            });

            refreshButton.addActionListener(e->
            {
                refresh(C_MainContainer, dspanel, quanlyselect, ds_id_nhanvien);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });

            changeButton.addActionListener(e->
            {
                goisuanhanvien(dspanel, () -> {
                    refresh(C_MainContainer, dspanel, quanlyselect, ds_id_nhanvien);
                });
            });




            addButton.addActionListener(e->
            {
               MainFunction.function.createAddDialog(null, 3);
                refresh(C_MainContainer, dspanel, quanlyselect, ds_id_nhanvien);
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

        protected void refresh(JPanel C_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_id_nhanvien)
        {
            C_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_id_nhanvien.clear();

            employeeService.Danhsachnhanvien employees = new employeeService.Danhsachnhanvien();
            Map<String,employeeService.nhanvien> ds_diadiem = employees.xuat();
            ds_id_nhanvien.addAll(ds_diadiem.keySet());

            if(ds_id_nhanvien.isEmpty())
            {
                C_MainContainer.add(new JLabel("Không có nhân viên trong dữ liệu"));
            }
            else
            {
                for(String id : ds_id_nhanvien)
                {
                    JPanel khungDiadiem = MainFunction.taoKhung(id,3,null,null);
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






        protected void configWindow(List<String>ds_id_nhanvien, Runnable refresh)
        {

            employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
            Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();



            JDialog cogfigureWindow = new JDialog(this,"Giao diện sửa",true);
            cogfigureWindow.setSize(800,500);
            cogfigureWindow.setLocationRelativeTo(this);

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: ds_id_nhanvien)
            {

                employeeService.nhanvien nhanvienxet = MapNhanVien.get(id);


                JPanel paneltam = MainFunction.taoKhung(id,3,null,null);

                String id_nhanvien = nhanvienxet.getId();
                String ten_nhanvien = nhanvienxet.getName();
                String vaitro_nhanvien = nhanvienxet.getVaitro();
                String calam = nhanvienxet.getca();
                String id_doi = nhanvienxet.getiddoi();


                JPanel khung_sua_nhanvien = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx=0;
                gbc.gridy=0;
                gbc.weightx =1.0;
                gbc.insets = new Insets(2, 2, 2, 2);
                gbc.anchor=GridBagConstraints.WEST;
                gbc.fill = GridBagConstraints.HORIZONTAL;



                JLabel label_id_diadiem = new JLabel("id:"+id);
                gbc.gridx=0;
                gbc.gridy= 0;
                label_id_diadiem.setName("id");
                khung_sua_nhanvien.add(label_id_diadiem,gbc);


                JLabel label_ten = new JLabel("Tên nhân viên: ");
                gbc.gridx=0;
                gbc.gridy= 1;
                gbc.weightx=0;
                khung_sua_nhanvien.add(label_ten,gbc);

                JTextField input_ten_nhanvien = new JTextField(ten_nhanvien);
                gbc.gridx=1;
                gbc.gridy= 1;
                gbc.weightx=1;
                khung_sua_nhanvien.add(input_ten_nhanvien,gbc);


                JLabel label_vaitro = new JLabel("Vai trò: ");
                gbc.gridx=0;
                gbc.gridy= 2;
                gbc.weightx=0;
                khung_sua_nhanvien.add(label_vaitro,gbc);
                
                JTextField input_vaitro_nhanvien = new JTextField(vaitro_nhanvien);
                gbc.gridx=1;
                gbc.gridy= 2;
                gbc.weightx=0.9;
                khung_sua_nhanvien.add(input_vaitro_nhanvien,gbc);

                JLabel Label_calam_them = new JLabel("ca làm việc:");
                Label_calam_them.setPreferredSize(new Dimension(300,20));
                gbc.gridx=0;
                gbc.gridy=3;
                gbc.weightx = 0;
                khung_sua_nhanvien.add(Label_calam_them,gbc);

                String[] calam_sua = {"sáng","chiều","tối"};
                JComboBox<String> optionmenu_calam = new JComboBox<>(calam_sua);
                optionmenu_calam.setPreferredSize(new Dimension(450,30));
                optionmenu_calam.setSelectedItem(calam);
                optionmenu_calam.setName("ca_lam");
                gbc.gridx=1;
                gbc.gridy=3;
                gbc.weightx = 1.0;
                khung_sua_nhanvien.add(optionmenu_calam,gbc);

                teamService.DanhsachDoi teams = new teamService.DanhsachDoi();
                Map<String,teamService.team> ds_doi = teams.xuat();
                List<String> ds_id_doi = new ArrayList<>(ds_doi.keySet());

                JLabel Label_chon_doi = new JLabel("Chọn đội phụ trách của nhân viên:");
                Label_chon_doi.setPreferredSize(new Dimension(300,20));
                gbc.gridx=0;
                gbc.gridy=4;
                gbc.weightx = 0;
                khung_sua_nhanvien.add(Label_chon_doi,gbc);

                JComboBox<String> optionmenu_doi = new JComboBox<>(new Vector<>(ds_id_doi));
                optionmenu_doi.setName("option_menu_doi");
                optionmenu_doi.setSelectedItem(id_doi);
                gbc.gridx=1;
                gbc.gridy=4;
                gbc.weightx = 1.0;
                khung_sua_nhanvien.add(optionmenu_doi,gbc);

                Container.add(khung_sua_nhanvien);
            }

            JButton saveButton = new JButton("Lưu thay đổi");
            saveButton.addActionListener(e -> {
                Map<String,Integer> checked = MainFunction.function.configurer(Container,3);
                StringBuilder message = new StringBuilder();
                int errorcheck=0;
                for(String id: ds_id_nhanvien)
                {
                    int error = checked.get(id);
                    if(error == 303)
                    {
                        errorcheck+=1;
                        message.append(id);
                        message.append("==> Không thể sửa: chưa nhập vai trò nhân viên");
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
                JOptionPane.showMessageDialog(Container, message);
                cogfigureWindow.dispose();
            });


            JScrollPane scrollPane = new JScrollPane(Container);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            cogfigureWindow.add(scrollPane, BorderLayout.CENTER);
            cogfigureWindow.add(saveButton, BorderLayout.SOUTH);
            cogfigureWindow.setVisible(true);
        }

        protected void goisuanhanvien(Map<String,JPanel> dulieutruyen,Runnable refreshAction)
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

