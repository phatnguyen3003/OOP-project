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





            JPanel F_ButtonContainer = new JPanel(new GridBagLayout());
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



            deletehButton.addActionListener(e -> {
                int result = JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn xóa các đội đã chọn?\nCác thành viên trong đội bị xóa sẽ trở thành không có đội: ID đội là 0","Xác nhận xóa",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

                if (result == JOptionPane.YES_OPTION) {
                    goixoadoi(dspanel);
                    refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
                }
            });


            refreshButton.addActionListener(e->
            {
                refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
                StringBuilder message = new StringBuilder();
                message.append("Đã làm mới!!");

                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            });




            addButton.addActionListener(e->
            {
                MainFunction.function.createAddDialog(null, 6);
                 refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
            });



            F_ButtonContainer.add(refreshButton,A_ButtonGBC);
            F_ButtonContainer.add(addButton,A_ButtonGBC);
            F_ButtonContainer.add(deletehButton,A_ButtonGBC);


            ContentContainer.add(F_ButtonContainer,BorderLayout.EAST);


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
                    JPanel khungdoi = MainFunction.taoKhung(id, 6, null, null, () -> {
                        refresh(F_MainContainer, dspanel, quanlyselect, ds_doi);
                    });
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

    }
}
