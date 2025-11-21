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


import services.ScheduleService;
import services.MainFunction;

public class schedule_manage {

    public static class ScheduleDialog extends JDialog
    {
        public ScheduleDialog(JFrame parent)
        {


            super(parent,"Quản lý dữ liệu Lịch trình",false);
            setSize(800,600);
            setLocationRelativeTo(parent);
            setLayout(new BorderLayout());

            JPanel ContentContainer = new JPanel(new BorderLayout());




            ScheduleService schedule = new ScheduleService();
            Map <String, ScheduleService> Maplichtrinh = new HashMap<>();
            List<String> ds_idlichtrinh = new ArrayList<>();
            Map<String,JPanel> dspanel = new HashMap<>();
            Map<String, JCheckBox> quanlyselect = new HashMap<>();

            JLabel label = new JLabel("Dữ liệu lịch trình chương trình", SwingConstants.CENTER);

            label.setFont(new Font("Arial", Font.BOLD, 20));

            ContentContainer.add(label, BorderLayout.NORTH);


            JPanel E_MainContainer = new JPanel();
            E_MainContainer.setLayout(new BoxLayout(E_MainContainer, BoxLayout.Y_AXIS));


            refresh(E_MainContainer, dspanel, quanlyselect, ds_idlichtrinh);



            JScrollPane scrollbar = new JScrollPane(E_MainContainer);
            scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            ContentContainer.add(scrollbar,BorderLayout.CENTER);





            JPanel E_ButtonContainer = new JPanel(new GridBagLayout());
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




            deletehButton.addActionListener(e ->
            {
                goixoalichtrinh(dspanel);
                refresh(E_MainContainer, dspanel, quanlyselect, ds_idlichtrinh);
            });

            refreshButton.addActionListener(e->
            {
                refresh(E_MainContainer, dspanel, quanlyselect, ds_idlichtrinh);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });





            addButton.addActionListener(e->
            {
                services.MainFunction.function.Function_Dialog add_dialog =new services.MainFunction.function.Function_Dialog(null,null,3);
                add_dialog.setVisible(true);
                refresh(E_MainContainer, dspanel, quanlyselect, ds_idlichtrinh);
            });



            E_ButtonContainer.add(refreshButton,A_ButtonGBC);
            E_ButtonContainer.add(addButton,A_ButtonGBC);
            E_ButtonContainer.add(deletehButton,A_ButtonGBC);


            ContentContainer.add(E_ButtonContainer,BorderLayout.EAST);


            add(ContentContainer);
            setVisible(true);

        }
        protected static void goixoalichtrinh(Map<String,JPanel> dulieutruyen)
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
            MainFunction.function.deleter(id_can_xoa,5);
        }

        protected void refresh(JPanel E_MainContainer,Map<String, JPanel> dspanel,Map<String, JCheckBox> quanlyselect,List<String> ds_idlichtrinh)
        {
            E_MainContainer.removeAll();

            dspanel.clear();
            quanlyselect.clear();
            ds_idlichtrinh.clear();

            ScheduleService.DanhsachLichtrinh schedules = new ScheduleService.DanhsachLichtrinh();
            Map<String,ScheduleService.Schedule> ds_lichtrinh = schedules.xuat();
            ds_idlichtrinh.addAll(ds_lichtrinh.keySet());


            if(ds_idlichtrinh.isEmpty())
            {
                E_MainContainer.add(new JLabel("Không có lịch trình trong dữ liệu"));
            }
            else
            {
                for(String id : ds_idlichtrinh)
                {
                    JPanel khungTietmuc = MainFunction.taoKhung(id,5,null,null,null);
                    khungTietmuc.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    khungTietmuc.setAlignmentX(Component.LEFT_ALIGNMENT);


                    JCheckBox checkbox = new JCheckBox();
                    checkbox.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                    quanlyselect.put(id,checkbox);

                    JPanel khungchucnang = new JPanel(new BorderLayout());
                    khungchucnang.add(checkbox,BorderLayout.WEST);
                    khungchucnang.add(khungTietmuc,BorderLayout.CENTER);
                    dspanel.put(id,khungchucnang);

                    E_MainContainer.add(khungchucnang);
                }
            }

            E_MainContainer.revalidate();
            E_MainContainer.repaint();
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


    }
}
