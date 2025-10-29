import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;


import function.framefunction;




//===============================================================================

public class ui {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.insets = new Insets(0,0,0,0);gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;

            // Ô hàng 0 (60px)
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 5; gbc.gridheight = 1;
            gbc.weighty = 0.1;
            JPanel cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            GridBagConstraints gbclabelname=new GridBagConstraints();
            gbclabelname.gridx=0;
            gbclabelname.gridy=0;
            gbclabelname.weightx=0;
            gbclabelname.anchor=GridBagConstraints.EAST;
            gbclabelname.insets = new Insets(0,0,0,5);
            JLabel labelname = new JLabel("ADMIN");
            cell.add(labelname,gbclabelname);
            JPanel circlePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.GREEN);
                    g.fillOval(0, 0, 60, 60);
                }
            };
            circlePanel.setPreferredSize(new Dimension(60, 60));
            GridBagConstraints gbcavatar=new GridBagConstraints();
            gbcavatar.gridx=1;
            gbcavatar.gridy=0;
            gbcavatar.weightx=0;
            gbcavatar.anchor=GridBagConstraints.EAST;
            cell.add(circlePanel,gbcavatar);
            panel.add(cell, gbc);

            // Ô hàng 1 (120px)
            gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3; gbc.gridheight = 1;
            gbc.weighty = 0.2;
            cell = new JPanel();
            cell.setLayout(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(cell, gbc);
            JButton evenbutton= new JButton("Sự kiện");
            cell.add(evenbutton, BorderLayout.CENTER);

            gbc.gridx = 3; gbc.gridy = 1; gbc.gridwidth = 2; gbc.gridheight = 1;
            gbc.weighty = 0.2;
            cell = new JPanel();
            cell.setLayout(new BorderLayout());
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(cell, gbc);
            JButton infobutton= new JButton("Cơ sở dữ Liệu");
            cell.add(infobutton, BorderLayout.CENTER);



            // Ô hàng 2 (còn lại 360px)
            gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 5; gbc.gridheight = 1;
            gbc.weighty = 0.6;

            JPanel mainframe1 = new JPanel(new BorderLayout());
            mainframe1.setBorder(BorderFactory.createLineBorder(Color.black));
            JPanel Listpanel1 = new JPanel();
            Listpanel1.setLayout(new BoxLayout(Listpanel1,BoxLayout.Y_AXIS));
            JScrollPane Scrollpane1 =new JScrollPane(Listpanel1);
            Scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainframe1.add(Scrollpane1,BorderLayout.CENTER);
            

            mainframe1.add(new JLabel("Ô 4 - Cell 1"),BorderLayout.NORTH);
            mainframe1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(mainframe1, gbc);


            
            // Cell thứ 2
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 5; gbc.gridheight = 1;
            gbc.weighty = 0.6;
            JPanel mainframe2 = new JPanel(new BorderLayout());
            mainframe2.setBorder(BorderFactory.createLineBorder(Color.black));
            JPanel Listpanel2 = new JPanel();
            Listpanel2.setLayout(new BoxLayout(Listpanel2, BoxLayout.Y_AXIS));
            JScrollPane Scrollpane2 = new JScrollPane();
            Scrollpane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainframe2.add(Scrollpane2,BorderLayout.CENTER);


            JPanel ButtonContainer = new JPanel(new GridBagLayout());
            GridBagConstraints grid = new GridBagConstraints();
            grid.gridx=0;
            grid.gridy=GridBagConstraints.RELATIVE;
            grid.insets = new Insets(5,0,5,0);



            JButton artistDataButton = new JButton("Dữ liệu ca / nghệ sĩ");
            artistDataButton.setPreferredSize(new Dimension(300,60));
            JButton performanceDataButton = new JButton("Dữ liệu các tiết mục đã đăng ký");
            performanceDataButton.setPreferredSize(new Dimension(300,60));
            JButton employeeDataButton = new JButton("Dữ liệu nhân viên/ đội ngũ");
            employeeDataButton.setPreferredSize(new Dimension(300,60));
            JButton locationDataButton = new JButton("Dữ liệu địa điểm tổ chức sự kiện");
            locationDataButton.setPreferredSize(new Dimension(300,60));


            ButtonContainer.add(artistDataButton,grid);
            ButtonContainer.add(performanceDataButton,grid);
            ButtonContainer.add(employeeDataButton,grid);
            ButtonContainer.add(locationDataButton,grid);

            artistDataButton.addActionListener(e->{
                framefunction.ArtistDialog artistdialog = new framefunction.ArtistDialog(frame);
                artistdialog.setVisible(true);
            });


            mainframe2.add(ButtonContainer,BorderLayout.CENTER);



            mainframe2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mainframe2.setVisible(false);
            panel.add(mainframe2, gbc);


            //Ô hàng 3(60px)
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 5; gbc.gridheight = 1;
            gbc.weighty = 0.1;
            cell = new JPanel();
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(cell, gbc);
            JButton addbutton = new JButton("Thêm sự kiện");
            addbutton.setPreferredSize(new Dimension(200, 50));
            cell.add(addbutton);
            JButton deletebutton = new JButton("Xóa sự kiện đã chọn");
            deletebutton.setPreferredSize(new Dimension(200, 50));
            cell.add(deletebutton);

            addbutton.addActionListener(e -> {
                framefunction.EventDialog dialog = new framefunction.EventDialog(frame);
                //dialog.loadDataToLabels(List.of("Ngày", "Giờ", "Địa điểm")); // dữ liệu demo
                dialog.setVisible(true);
            });


            evenbutton.addActionListener(e->
            {
                mainframe1.setVisible(true);
                mainframe2.setVisible(false);
                panel.revalidate();
                panel.repaint();
            });
            infobutton.addActionListener(e->
            {
                mainframe1.setVisible(false);
                mainframe2.setVisible(true);
                panel.revalidate();
                panel.repaint();
            });


            frame.add(panel);
            frame.setVisible(true);
        });
    }
    
}
