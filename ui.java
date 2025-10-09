import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.awt.*;

class EventDialog extends JDialog
{

    private List<JLabel> labels = new ArrayList<>();
    String[] iddoi = {"Đội 1", "Đội 2", "Đội 3", "Đội 4", "Đội 5", "Đội 6", "Đội 7"};
    String[] tlctrinh = {"90 phút", "120 phút", "150 phút", "180 phút", "210 phút", "240 phút", "270 phút","300 phút"};
    private JTextField eventNameField;
    private JButton saveButton,cancelButton;

    int socanghesi=8;

    public EventDialog(JFrame parent)
    {
        super(parent,"Thêm sự kiện",false);
        setSize(600,400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;


        //hang dau tien(j=3)

        JLabel labelname = new JLabel("Tên Sự Kiện");
        GridBagConstraints gbclabelname=new GridBagConstraints();
        gbclabelname.gridx=0;
        gbclabelname.gridy=3;
        gbclabelname.weightx=0;
        gbclabelname.anchor=GridBagConstraints.EAST;
        panel.add(labelname,gbclabelname);

        JTextField eventNameField = new JTextField(15);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridy = 3;
        gbc1.gridx = 1;
        gbc1.gridwidth = 1;
        gbc1.weightx = 0.3;
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        panel.add(eventNameField, gbc1);

        
        
        JLabel labeldiadiem = new JLabel("Địa Điểm Tổ Chức");
        GridBagConstraints gbclabel1=new GridBagConstraints();
        gbclabel1.gridx=2;
        gbclabel1.gridy=3;
        gbclabel1.weightx=0;
        gbclabel1.anchor=GridBagConstraints.WEST;
        panel.add(labeldiadiem,gbclabel1);


        JComboBox<String>chon_dia_diem_tc = new JComboBox<>();
        chon_dia_diem_tc.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbc2=new GridBagConstraints();
        gbc2.gridx = 3;
        gbc2.gridy = 3;
        gbc2.weightx = 0.7;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        panel.add(chon_dia_diem_tc,gbc2);

        //ngang cach
        JPanel hr1 = new JPanel();
        GridBagConstraints gbchr1 = new GridBagConstraints();
        gbchr1.gridx = 0;
        gbchr1.gridy = 4;
        gbchr1.weightx = 1.0;
        gbchr1.gridwidth = GridBagConstraints.REMAINDER;
        gbchr1.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hr1, gbchr1);

        //hang thu 2(j=5)

        JLabel labelcasi = new JLabel("Chọn ca sĩ/Nghệ sĩ ");
        GridBagConstraints gbclabelcasi = new GridBagConstraints();
        gbclabelcasi.gridx=0;
        gbclabelcasi.gridy=5;
        gbclabelcasi.weightx=0;
        gbclabelcasi.anchor=GridBagConstraints.WEST;
        panel.add(labelcasi,gbclabelcasi);



        GridBagConstraints gbcchoncasi = new GridBagConstraints();
        gbcchoncasi.gridx=1;
        gbcchoncasi.gridy=5;
        gbcchoncasi.weightx=0.5;
        gbcchoncasi.anchor=GridBagConstraints.WEST;
        for(int i=0;i<=socanghesi;i++)
        {
            JCheckBox cb = new JCheckBox("test"+i);
            GridBagConstraints gbceachcheckbox = new GridBagConstraints();
            gbceachcheckbox.gridx=1;
            gbceachcheckbox.gridy=5+i;
            gbceachcheckbox.weightx=0.5;
            gbceachcheckbox.anchor=GridBagConstraints.WEST;
            panel.add(cb,gbceachcheckbox);
        }




        JLabel labelthoigiantc = new JLabel("Thời Gian Tổ Chức");
        GridBagConstraints gbclabeltgtc=new GridBagConstraints();
        gbclabeltgtc.gridx=2;
        gbclabeltgtc.gridy=5;
        gbclabeltgtc.weightx=0;
        gbclabeltgtc.anchor=GridBagConstraints.WEST;
        panel.add(labelthoigiantc,gbclabeltgtc);


        JComboBox<String>chon_thoi_gian_tc = new JComboBox<>();
        chon_thoi_gian_tc.setPreferredSize(new Dimension(120,20));


        int startHour = 8;   // 8:00 AM
        int endHour = 22;    // 22:00 PM
        int interval = 10;   // mỗi 10 phút

        for (int h = startHour; h <= endHour; h++) {
            for (int m = 0; m < 60; m += interval) {
                if (h == endHour && m > 0) break; // dừng khi vượt quá 22:00
                String timeStr;
                if(h<12)
                {
                    timeStr = String.format("%02d:%02d AM", h, m);
                }
                else
                {
                    timeStr = String.format("%02d:%02d PM", h, m);
                }
                chon_thoi_gian_tc.addItem(timeStr);
            }
        }


        GridBagConstraints gbctgtc=new GridBagConstraints();
        gbctgtc.gridx = 3;
        gbctgtc.gridy = 5;
        gbctgtc.weightx = 0.;
        gbctgtc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(chon_thoi_gian_tc,gbctgtc);


        //ngang cach
        JPanel hr2 = new JPanel();
        GridBagConstraints gbchr2 = new GridBagConstraints();
        gbchr2.gridx = 0;
        gbchr2.gridy = 6+socanghesi;
        gbchr2.weightx = 1.0;
        gbchr2.gridwidth = GridBagConstraints.REMAINDER;
        gbchr2.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hr2, gbchr2);

        //hang thu 3 (j=7)

        JLabel labelngaytc = new JLabel("Ngày Tổ Chức");
        GridBagConstraints gbclabelngaytc=new GridBagConstraints();
        gbclabelngaytc.gridx=0;
        gbclabelngaytc.gridy=7+socanghesi;
        gbclabelngaytc.weightx=0;
        gbclabelngaytc.anchor=GridBagConstraints.WEST;
        panel.add(labelngaytc,gbclabelngaytc);

        JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        GridBagConstraints gbcdatepicker=new GridBagConstraints();
        gbcdatepicker.gridx=1;
        gbcdatepicker.gridy=7+socanghesi;
        gbcdatepicker.weightx=0.5;
        panel.add(dateSpinner,gbcdatepicker);


        JLabel labeldoitochuc = new JLabel("Đội phụ trách");
        GridBagConstraints gbclabeldoitochuc=new GridBagConstraints();
        gbclabeldoitochuc.gridx=2;
        gbclabeldoitochuc.gridy=7+socanghesi;
        gbclabeldoitochuc.weightx=0;
        gbclabeldoitochuc.anchor=GridBagConstraints.WEST;
        panel.add(labeldoitochuc,gbclabeldoitochuc);


        JComboBox<String>id_doi_phu_trach=new JComboBox<>(iddoi);
        id_doi_phu_trach.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbciddoi = new GridBagConstraints();
        gbciddoi.gridx=3;
        gbciddoi.gridy=7+socanghesi;
        gbciddoi.weightx=1;
        gbciddoi.fill=GridBagConstraints.HORIZONTAL;
        panel.add(id_doi_phu_trach);

        //ngang cach
        JPanel hr4 = new JPanel();
        GridBagConstraints gbchr4 = new GridBagConstraints();
        gbchr4.gridx = 0;
        gbchr4.gridy = 8+socanghesi;
        gbchr4.weightx = 1.0;
        gbchr4.gridwidth = GridBagConstraints.REMAINDER;
        gbchr4.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hr4, gbchr4);

        //hang thu 4 (j=9)

        JLabel labelThoiluongct = new JLabel("Thời lượng chương trình");
        GridBagConstraints gbclabelthoiluong=new GridBagConstraints();
        gbclabelthoiluong.gridx=0;
        gbclabelthoiluong.gridy=9+socanghesi;
        gbclabelthoiluong.weightx=0;
        gbclabelthoiluong.anchor=GridBagConstraints.WEST;
        panel.add(labelThoiluongct,gbclabelthoiluong);

        JComboBox<String>thoiluongctrinh=new JComboBox<>(tlctrinh);
        thoiluongctrinh.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbctlct = new GridBagConstraints();
        gbctlct.gridx=1;
        gbctlct.gridy=9+socanghesi;
        gbctlct.weightx=1;
        gbctlct.fill=GridBagConstraints.HORIZONTAL;
        panel.add(thoiluongctrinh,gbctlct);

        JLabel labelsoluongtietmuc = new JLabel("Số lượng tiết mục");
        GridBagConstraints gbclabelsltietmuc=new GridBagConstraints();
        gbclabelsltietmuc.gridx=2;
        gbclabelsltietmuc.gridy=9+socanghesi;
        gbclabelsltietmuc.weightx=0;
        gbclabelsltietmuc.anchor=GridBagConstraints.WEST;
        panel.add(labelsoluongtietmuc,gbclabelsltietmuc);

        JTextField eventcontentsumary = new JTextField(9);
        GridBagConstraints gbceventsumary = new GridBagConstraints();
        gbceventsumary.gridx = 3;
        gbceventsumary.gridy = 9+socanghesi;
        gbceventsumary.gridwidth = 1;
        gbceventsumary.weightx = 0.3;
        gbceventsumary.fill = GridBagConstraints.HORIZONTAL;
        panel.add(eventcontentsumary, gbceventsumary);


    



        //ngang cach
        JPanel hr8 = new JPanel();
        GridBagConstraints gbchr3 = new GridBagConstraints();
        gbchr3.gridx = 0;
        gbchr3.gridy = 12+socanghesi;
        gbchr3.weightx = 1.0;
        gbchr3.gridwidth = GridBagConstraints.REMAINDER;
        gbchr3.fill = GridBagConstraints.HORIZONTAL;
        panel.add(hr8, gbchr3);





        JPanel cangiuaPanel = new JPanel();
        GridBagConstraints cangiua=new GridBagConstraints();
        cangiua.gridx=0;
        cangiua.gridy=13+socanghesi+1;
        cangiua.weightx=0.4;
        panel.add(cangiuaPanel,cangiua);


        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Lưu");
        cancelButton = new JButton("Hủy");
        GridBagConstraints callbackend=new GridBagConstraints();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        callbackend.gridy = 13+socanghesi+1;
        callbackend.weightx=0.6;
        callbackend.anchor=GridBagConstraints.EAST;
        panel.add(buttonPanel, callbackend);

        cancelButton.addActionListener(e -> dispose()); // đóng dialog

        add(panel);

    }
}

public class ui {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);
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

            JLabel labelname = new JLabel("ADMIN");
            GridBagConstraints gbclabelname=new GridBagConstraints();
            gbclabelname.gridx=0;
            gbclabelname.gridy=0;
            gbclabelname.weightx=0;
            gbclabelname.anchor=GridBagConstraints.EAST;
            gbclabelname.insets = new Insets(0,0,0,5);
            cell.add(labelname,gbclabelname);
            JPanel circlePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(Color.GREEN);
                    g.fillOval(0, 0, 60, 60); // 60 = 2 * bán kính
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
            JPanel mainframe1 = new JPanel();
            mainframe1.add(new JLabel("Ô 4 - Cell 1"));
            mainframe1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.add(mainframe1, gbc);

            // Cell thứ 2
            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 5; gbc.gridheight = 1;
            gbc.weighty = 0.6;
            JPanel mainframe2 = new JPanel();
            mainframe2.add(new JLabel("Ô 4 - Cell 2"));
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
                EventDialog dialog = new EventDialog(frame);
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
