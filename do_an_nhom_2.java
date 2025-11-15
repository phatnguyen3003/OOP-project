import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;
import java.util.*;

import function.artist_manage;
import function.performance_manage;
import function.location_manage;
import function.employee_manage;
import function.schedule_manage;
import function.team_manage;


import services.Event_Information;
import services.MainFunction;



//===============================================================================

public class do_an_nhom_2 {
    
    public static void main(String[] args) 
    {

        Map<String, JCheckBox> quanlyselect = new HashMap<>();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new BorderLayout(10, 10));

            JPanel TopPanel = new JPanel();
            TopPanel.setLayout(new BoxLayout(TopPanel, BoxLayout.Y_AXIS));
            TopPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel TITLE = new JLabel("Quản Lý Tổ Chức Sự Kiện");
            TITLE.setFont(new Font("Arial", Font.BOLD, 28));
            TITLE.setAlignmentX(Component.CENTER_ALIGNMENT);
            TopPanel.add(TITLE);

            JPanel topButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            JButton evenbutton = new JButton("Sự kiện");
            JButton infobutton = new JButton("Cơ sở dữ liệu");
            Dimension btnSize = new Dimension(450, 60);
            evenbutton.setPreferredSize(btnSize);
            infobutton.setPreferredSize(btnSize);
            topButtons.add(evenbutton);
            topButtons.add(infobutton);

            TopPanel.add(topButtons);
            panel.add(TopPanel, BorderLayout.NORTH);



            JPanel centerPanel = new JPanel(new CardLayout());


            JPanel mainframe1 = new JPanel();
            mainframe1.setLayout(new GridLayout(0,1,2,2));
            mainframe1.setBorder(BorderFactory.createLineBorder(Color.black));

            JScrollPane scrollPane = new JScrollPane(mainframe1);
            centerPanel.add(scrollPane,"thongtinsukien");

            MainFunction.refreshUI(mainframe1,quanlyselect);


            JPanel mainframe2 = new JPanel();
            mainframe2.setLayout(new GridBagLayout());
            mainframe2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mainframe2.setVisible(false);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx=0;
            gbc.gridy=0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridwidth=1;
            gbc.insets = new Insets(5, 5, 5, 5);

            JButton artistDataButton = new JButton("Dữ liệu ca / nghệ sĩ");
            JButton performanceDataButton = new JButton("Dữ liệu các tiết mục đã đăng ký");
            JButton employeeDataButton = new JButton("Dữ liệu nhân viên");
            JButton teamDataButton = new JButton("Dữ liệu đội ngũ quản lý sự kiện");
            JButton locationDataButton = new JButton("Dữ liệu địa điểm tổ chức sự kiện");
            JButton scheduleDataButton = new JButton("Dữ liệu lịch trình cài đặt sẵn");


            artistDataButton.setPreferredSize(new Dimension(450, 80));
            performanceDataButton.setPreferredSize(new Dimension(450, 80));
            employeeDataButton.setPreferredSize(new Dimension(450, 80));
            teamDataButton.setPreferredSize(new Dimension(450, 80));
            locationDataButton.setPreferredSize(new Dimension(450, 80));
            scheduleDataButton.setPreferredSize(new Dimension(450, 80));

            gbc.gridx=0;
            gbc.gridy=0;
            mainframe2.add(artistDataButton,gbc);

            gbc.gridx=1;
            gbc.gridy=0;
            mainframe2.add(performanceDataButton,gbc);

            gbc.gridx=0;
            gbc.gridy=1;
            mainframe2.add(employeeDataButton,gbc);

            gbc.gridx=1;
            gbc.gridy=1;
            mainframe2.add(teamDataButton,gbc);

            gbc.gridx=0;
            gbc.gridy=2;
            mainframe2.add(locationDataButton,gbc);

            gbc.gridx=1;
            gbc.gridy=2;
            mainframe2.add(scheduleDataButton,gbc);


            artistDataButton.addActionListener(e->{
                artist_manage.ArtistDialog artistDialog = new artist_manage.ArtistDialog(frame);
                artistDialog.setVisible(true);
            });
            performanceDataButton.addActionListener(e->{
                performance_manage.PerformanceDialog performanceDialog = new performance_manage.PerformanceDialog(frame);
                performanceDialog.setVisible(true);
            });
            employeeDataButton.addActionListener(e->{
                employee_manage.EmployeeDialog employeeDialog = new employee_manage.EmployeeDialog(frame);
                employeeDialog.setVisible(true);
            });
            teamDataButton.addActionListener(e->{
                team_manage.TeamDialog teamDialog = new team_manage.TeamDialog(frame);
                teamDialog.setVisible(true);
            });
            locationDataButton.addActionListener(e->{
                location_manage.LocationDialog locationDialog = new location_manage.LocationDialog(frame);
                locationDialog.setVisible(true);
            });
            scheduleDataButton.addActionListener(e->{
                schedule_manage.ScheduleDialog scheduleDialog = new schedule_manage.ScheduleDialog(frame);
                scheduleDialog.setVisible(true);
            });

            centerPanel.add(mainframe2,"chucnang");

            CardLayout Card = (CardLayout) centerPanel.getLayout();

            evenbutton.addActionListener(e -> {
                Card.show(centerPanel,"thongtinsukien");
            });

            infobutton.addActionListener(e -> {
                Card.show(centerPanel, "chucnang");
            });

            
            panel.add(centerPanel,BorderLayout.CENTER);

            JPanel functionPanel = new JPanel(new GridLayout(1,0,5,5));

            JButton refreshButton = new JButton("Làm mới");
            refreshButton.setPreferredSize(new Dimension(0,50));
            JButton addButton = new JButton("Thêm sự kiện");
            addButton.setPreferredSize(new Dimension(0,50));
            JButton deleteButton = new JButton("Xóa");
            deleteButton.setPreferredSize(new Dimension(0,50));

            refreshButton.addActionListener(e->{
                JOptionPane.showMessageDialog(frame,"Đã làm mới","Thông báo",JOptionPane.INFORMATION_MESSAGE);
                MainFunction.refreshUI(mainframe1,quanlyselect);
            });

            addButton.addActionListener(e->{
                MainFunction.function.Function_Dialog addDialog = new MainFunction.function.Function_Dialog(frame, null, 6);
                addDialog.setVisible(true);
                MainFunction.refreshUI(mainframe1,quanlyselect);
            });

            deleteButton.addActionListener(e->{
                List<String> ds_id_xoa = new ArrayList<>();
                for(Map.Entry<String,JCheckBox> entry : quanlyselect.entrySet())
                {
                    String id_chon=entry.getKey();
                    JCheckBox checkbox = entry.getValue();
                    if(checkbox.isSelected())
                    {
                        ds_id_xoa.add(id_chon);
                    }
                }
                MainFunction.function.deleter(ds_id_xoa, 0);
                MainFunction.refreshUI(mainframe1,quanlyselect);
            });


            functionPanel.add(refreshButton);
            functionPanel.add(addButton);
            functionPanel.add(deleteButton);

            panel.add(functionPanel,BorderLayout.SOUTH);

            frame.add(panel);
            frame.setVisible(true);

        });
    }





    /*
    public static void refresh(JPanel mainframe1,Map<String,JCheckBox> quanlyselect)
    {
        mainframe1.removeAll();
        quanlyselect.clear();

        Event_Information.DanhsachThongtinSukien danhsachttsk = new Event_Information.DanhsachThongtinSukien();
        Map<String, Event_Information.thongtin_sukien> MapSukien = danhsachttsk.xuat();
        List<String> ds_id_sk = new ArrayList<>(MapSukien.keySet());

        int count = 1;
        for(String id_sk : ds_id_sk)
        {


            JPanel dangtao = MainFunction.taoKhung(id_sk, 0);
            dangtao.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
            dangtao.setAlignmentX(Component.LEFT_ALIGNMENT);

            JCheckBox checkbox = new JCheckBox();
            checkbox.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
            quanlyselect.put(id_sk, checkbox);


            JPanel khungChucNang = new JPanel(new BorderLayout());
            khungChucNang.add(checkbox, BorderLayout.WEST);
            khungChucNang.add(dangtao, BorderLayout.CENTER);

            if(count%2!=0){
                khungChucNang.setBackground(new Color(230, 245, 255));
            } else {
                khungChucNang.setBackground(new Color(255, 240, 245));
            }
            mainframe1.add(khungChucNang);
            count++;
        }

        mainframe1.validate();
        mainframe1.repaint();
    }*/
}
