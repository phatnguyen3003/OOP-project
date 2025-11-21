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



            JLabel label = new JLabel("Dữ liệu địa điểm tổ chức", SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 20));

            ContentContainer.add(label, BorderLayout.NORTH);



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




            addButton.addActionListener(e->
            {
                MainFunction.function.createAddDialog(null, 4);
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

            if(ds_diadiem.isEmpty())
            {
                D_MainContainer.add(new JLabel("Không có địa điểm trong dữ liệu"));
            }
            else
            {
                for(String id : ds_iddiadiem)
                {
                    JPanel khungDiadiem = MainFunction.taoKhung(id,4,null,null,null);
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





        protected void configWindow(List<String>ds_id_diadiem, Runnable refresh)
        {

            locationService.Danhsachdiadiem danhsachdiadiem = new locationService.Danhsachdiadiem();
            Map<String,locationService.location>MapDiaDiem = danhsachdiadiem.xuat();


            JDialog cogfigureWindow = new JDialog(this,"Giao diện sửa",true);
            cogfigureWindow.setSize(400,400);
            cogfigureWindow.setLocationRelativeTo(this);

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: ds_id_diadiem)
            {

                locationService.location diadiemxet = MapDiaDiem.get(id);


                JPanel paneltam = MainFunction.taoKhung(id,4,null,null,null);

                String id_diadiem = diadiemxet.getiddd();
                String ten_diadiem = diadiemxet.getdiadiem();
                String succhua_diadiem = String.valueOf(diadiemxet.getsucchua());

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
                
                JTextField input_succhua_diadiem = new JTextField(succhua_diadiem);
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
                    JOptionPane.showMessageDialog(Container, message);
                    cogfigureWindow.dispose();
                }
                JOptionPane.showMessageDialog(Container, message);
                refresh.run();
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

