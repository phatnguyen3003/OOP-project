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



            JLabel label = new JLabel("Dữ liệu tiết mục", SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 20));

            ContentContainer.add(label, BorderLayout.NORTH);



            JPanel B_MainContainer = new JPanel();
            B_MainContainer.setLayout(new BoxLayout(B_MainContainer, BoxLayout.Y_AXIS));


            refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);



            JScrollPane scrollbar = new JScrollPane(B_MainContainer);
            scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            ContentContainer.add(scrollbar,BorderLayout.CENTER);





            JPanel B_ButtonContainer = new JPanel(new GridBagLayout());
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
             * 201: duplicate id
             * 202: special character in time_cost
             */

            addButton.addActionListener(e->
            {
               MainFunction.function.createAddDialog(null, 2);
                refresh(B_MainContainer, dspanel, quanlyselect, ds_idtietmuc);
            });



            B_ButtonContainer.add(refreshButton,A_ButtonGBC);
            B_ButtonContainer.add(addButton,A_ButtonGBC);
            B_ButtonContainer.add(deletehButton,A_ButtonGBC);
            B_ButtonContainer.add(changeButton,A_ButtonGBC);


            ContentContainer.add(B_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoatietmuc(Map<String, JPanel> dulieutruyen) 
        {
            List<String> id_can_xoa = new ArrayList<>();

            for (Map.Entry<String, JPanel> entry : dulieutruyen.entrySet()) {
                String id = entry.getKey();
                JPanel trangthai = entry.getValue();

                for (Component c : trangthai.getComponents()) {
                    if (c instanceof JCheckBox) {
                        JCheckBox checkbox = (JCheckBox) c;
                        if (checkbox.isSelected()) {
                            id_can_xoa.add(id);
                        }
                    }
                }
            }

            if (!id_can_xoa.isEmpty()) {
                // Hỏi xác nhận
                int result = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn có chắc chắn muốn xóa " + id_can_xoa.size() + " tiết mục không?\nCác nghệ sĩ có tiết mục này trong danh sách sẽ bị xoa khỏi danh sách đó",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) {
                    MainFunction.function.deleter(id_can_xoa, 2);
                } else {
                    JOptionPane.showMessageDialog(null, " Đã Hủy xóa!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không có tiết mục nào được chọn để xóa.");
            }
        }


        protected void refresh(JPanel B_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_idtietmuc)
        {
            B_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_idtietmuc.clear();

            PerformanceService.Danhsachtietmuc performances = new PerformanceService.Danhsachtietmuc();
            Map<String,PerformanceService.tietmuc> ds_tietmuc = performances.xuat();
            ds_idtietmuc.addAll(ds_tietmuc.keySet());

            if(ds_idtietmuc.isEmpty())
            {
                B_MainContainer.add(new JLabel("Không có dữ liệu tiết mục"));
            }
            else
            {
                for(String id : ds_idtietmuc)
                {
                    JPanel khungTietmuc = MainFunction.taoKhung(id,2,null,null,null);
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








        protected void configWindow(List<String>ds_id_tietmuc, Runnable refresh)
        {

            PerformanceService.Danhsachtietmuc danhsachtietmuc = new PerformanceService.Danhsachtietmuc();
            Map<String,PerformanceService.tietmuc> MapTietMuc = danhsachtietmuc.xuat();


            JDialog cogfigureWindow = new JDialog(this,"Giao diện sửa",true);
            cogfigureWindow.setSize(400,400);
            cogfigureWindow.setLocationRelativeTo(this);

            JPanel Container = new JPanel();
            Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));

            for(String id: ds_id_tietmuc)
            {
                PerformanceService.tietmuc tietmucxet = MapTietMuc.get(id);


                JPanel paneltam = MainFunction.taoKhung(id,2,null,null,null);

                String id_tietmuc = tietmucxet.getidtietmuc();
                String ten_tietmuc = tietmucxet.gettentietmuc();
                String thoiluong_tietmuc = String.valueOf(tietmucxet.getthoiluong());

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
