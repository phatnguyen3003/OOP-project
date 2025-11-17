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


import services.teamService;
import services.MainFunction;
import services.employeeService;

public class team_manage {
    public static class TeamDialog extends JDialog
    {
        public TeamDialog(JFrame parent)
        {


            super(parent,"Quản lý đội:",false);
            setSize(800,600);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel ContentContainer = new JPanel(new BorderLayout());




            teamService team = new teamService();
            Map <String, teamService.team> Mapdoi= new HashMap<>();
            List<String> ds_doi = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();




            JLabel label = new JLabel("Dữ liệu đội ngũ tổ chức chương trình", SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 20));

            ContentContainer.add(label, BorderLayout.NORTH);


            JPanel F_MainContainer = new JPanel();
            F_MainContainer.setLayout(new BoxLayout(F_MainContainer, BoxLayout.Y_AXIS));


            refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);



            JScrollPane scrollbar = new JScrollPane(F_MainContainer);
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
                goixoadoi(dspanel);
                refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
            });

            refreshButton.addActionListener(e->
            {
                refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });

            changeButton.addActionListener(e->
            {
                goisuadoi(dspanel, () -> {
                    refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
                });
            });



            addButton.addActionListener(e->
            {
                MainFunction.function.createAddDialog(null, 6);
                 refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
            });



            A_ButtonContainer.add(refreshButton,A_ButtonGBC);
            A_ButtonContainer.add(addButton,A_ButtonGBC);
            A_ButtonContainer.add(deletehButton,A_ButtonGBC);
            A_ButtonContainer.add(changeButton,A_ButtonGBC);


            ContentContainer.add(A_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoadoi(Map<String,JPanel> dulieutruyen)
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
            MainFunction.function.deleter(id_can_xoa,6);
        }

        protected void refresh(JPanel F_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_doi)
        {
            F_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_doi.clear();

            teamService.DanhsachDoi team = new teamService.DanhsachDoi();
            Map<String,teamService.team> dsnghesi = team.xuat();
            ds_doi.addAll(dsnghesi.keySet());

            if(ds_doi.isEmpty())
            {
                F_MainContainer.add(new JLabel("Không có đội trong dữ liệu"));
            }
            else
            {
                for(String id : ds_doi)
                {
                    JPanel khungdoi = MainFunction.taoKhung(id,6,null,null);
                    khungdoi.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    khungdoi.setAlignmentX(Component.LEFT_ALIGNMENT);


                    JCheckBox checkbox = new JCheckBox();
                    checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                    quanlyselect.put(id,checkbox);

                    JPanel khungchucnang = new JPanel(new BorderLayout());
                    khungchucnang.add(checkbox,BorderLayout.WEST);
                    khungchucnang.add(khungdoi,BorderLayout.CENTER);
                    dspanel.put(id,khungchucnang);

                    F_MainContainer.add(khungchucnang);
                }
            }


            

            F_MainContainer.revalidate();
            F_MainContainer.repaint();
        }

        protected static String get_add_information(JPanel themdoi,String name)
        {
            for(Component c : themdoi.getComponents())
            {
                if(c instanceof JTextField && name.equals(c.getName()))
                {
                    return ((JTextField) c).getText();
                }
            }
            return null;
        }





        protected void configWindow(List<String>danh_sach_id_doi, Runnable refresh)
        {
            teamService.DanhsachDoi danhsachdoi = new teamService.DanhsachDoi();
            Map<String,teamService.team> MapDoi = danhsachdoi.xuat();



            JDialog configureWindow = new JDialog(this,"Giao diện sửa",false);
            configureWindow.setSize(400,400);
            configureWindow.setLocationRelativeTo(this);
            configureWindow.setLocation(this.getX()-300,this.getY());

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: danh_sach_id_doi)
            {
                teamService.team doixet = MapDoi.get(id);


                JPanel paneltam = MainFunction.taoKhung(id,6,null,null);

                String id_Doi = doixet.getiddoi();
                String id_Leader = doixet.getidleader();
                String ds_Nhanvien = String.join(",",doixet.getds());

                JPanel khung_sua_doi = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = GridBagConstraints.RELATIVE;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.insets = new Insets(2, 2, 2, 2);


                JLabel labelidnghesi = new JLabel("id:"+id_Doi);
                gbc.gridx=0;
                gbc.gridy= 0;
                labelidnghesi.setName("id");
                khung_sua_doi.add(labelidnghesi,gbc);


                JLabel label_id = new JLabel("ID đội trưởng: ");
                gbc.gridx=0;
                gbc.gridy= 1;
                gbc.weightx=0;
                khung_sua_doi.add(label_id,gbc);

               JTextField inputIDLeader = new JTextField(id_Leader);
               gbc.gridx = 1;
               gbc.gridy = 1;
               gbc.weightx = 1.0;
               khung_sua_doi.add(inputIDLeader, gbc);

                JLabel label_id_tietmuc = new JLabel("ID các nhân viên trong đội: ");
                gbc.gridx=0;
                gbc.gridy= 5;
                gbc.weightx=0;
                khung_sua_doi.add(label_id_tietmuc,gbc);

                JTextField inputidtietmuc = new JTextField(ds_Nhanvien);
                gbc.gridx=1;
                gbc.gridy= 5;
                gbc.weightx=1;
                khung_sua_doi.add(inputidtietmuc,gbc);

                Container.add(khung_sua_doi);
            }

            JButton saveButton = new JButton("Lưu thay đổi");
            saveButton.addActionListener(e -> {
                MainFunction.function.configurer(Container,4);
                JOptionPane.showMessageDialog(configureWindow, "Đã lưu thay đổi!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                if (refresh != null) {
                    refresh.run();
                }
                configureWindow.dispose();
            });


            JScrollPane scrollPane = new JScrollPane(Container);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


            configureWindow.add(scrollPane, BorderLayout.CENTER);
            configureWindow.add(saveButton, BorderLayout.SOUTH);
            configureWindow.setVisible(true);


            JDialog secondaryWindow = new JDialog(this, "Thông tin các nhân viên", false);
            secondaryWindow.setSize(1000, 400);
            secondaryWindow.setLocation(configureWindow.getX() + 400, configureWindow.getY());

            employeeService.Danhsachnhanvien danhsachnhanvien = new employeeService.Danhsachnhanvien();
            Map<String,employeeService.nhanvien> MapNhanVien = danhsachnhanvien.xuat();
            List<String> ds_id_nhan_vien = new ArrayList<>(MapNhanVien.keySet());

            JPanel panelSecondary = new JPanel(new GridLayout(0,4,5,5));

            panelSecondary.add(new JLabel("Nhân viên chưa được phân về đội"));
            panelSecondary.add(new JLabel(""));
            panelSecondary.add(new JLabel(""));
            panelSecondary.add(new JLabel(""));


            panelSecondary.add(new JLabel("ID nhân viên"));
            panelSecondary.add(new JLabel("Tên nhân viên"));
            panelSecondary.add(new JLabel("Vai trò"));
            panelSecondary.add(new JLabel("Ca làm"));

            for(String id_nv: ds_id_nhan_vien)
            {
                employeeService.nhanvien nhanvienxet = MapNhanVien.get(id_nv);
                if(nhanvienxet.getiddoi().equalsIgnoreCase("0"))
                {
                    panelSecondary.add(new JLabel(id_nv));
                    panelSecondary.add(new JLabel(nhanvienxet.getName()));
                    panelSecondary.add(new JLabel(nhanvienxet.getVaitro()));
                    panelSecondary.add(new JLabel(nhanvienxet.getca()));
                }
                
                
            }

            JScrollPane thanhcuon = new JScrollPane(panelSecondary);
            thanhcuon.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            thanhcuon.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            secondaryWindow.add(thanhcuon);
            secondaryWindow.setVisible(true);



        }

        protected void goisuadoi(Map<String,JPanel> dulieutruyen,Runnable refreshAction)
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
