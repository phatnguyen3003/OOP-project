package services;

import Main_interface.main_interface;
import Main_interface.main_interface.IGeneralService;
import services.PerformanceService;
import services.ArtistService;

import java.io.*;
import java.util.*;

public class Event_Information implements IGeneralService<Event_Information.thongtin_sukien>{
    private static final String FILE_PATH = "src/database/Event_Information.txt";

    public static class thongtin_sukien
    {
        private String id_sk,ten_sk,id_lichtrinh,id_diadiem,id_doi_phutrach;

        public thongtin_sukien()
        {

        }

        public thongtin_sukien(String id_sk,String ten_sk,String id_lichtrinh,String id_diadiem,String id_doi_phutrach)
        {
            this.id_sk=id_sk;
            this.ten_sk=ten_sk;
            this.id_lichtrinh=id_lichtrinh;
            this.id_diadiem=id_diadiem;
            this.id_doi_phutrach=id_doi_phutrach;
        }

        public void set_id_sk(String id_sk)
        {
            this.id_sk=id_sk;
        }
         public void set_ten_sk(String ten_sk)
        {
            this.ten_sk=ten_sk;
        }
        public void set_id_lichtrinh(String id_lichtrinh)
        {
            this.id_lichtrinh=id_lichtrinh;
        }
        public void set_id_diadiem(String id_diadiem)
        {
            this.id_diadiem=id_diadiem;
        }
        public void set_id_doi_phutrach(String id_doi_phutrach)
        {
            this.id_doi_phutrach=id_doi_phutrach;
        }

        public String get_id_sk()
        {
            return id_sk;
        }
        public String get_ten_sk()
        {
            return ten_sk;
        }
        public String get_id_diadiem()
        {
            return id_diadiem;
        }
        public String get_id_lichtrinh()
        {
            return id_lichtrinh;
        }
        public String get_id_doi_phutrach()
        {
            return id_doi_phutrach;
        }

    }
    private List<thongtin_sukien> loadtufile()
        {
            List<thongtin_sukien> ds_thongtin = new ArrayList<>();
            try(BufferedReader bfr = new BufferedReader(new FileReader(FILE_PATH)))
            {
                String Line;
                while((Line = bfr.readLine())!=null)
                {
                    if(Line.trim().isEmpty())
                    {
                        continue;
                    }
                    String[] data_parts = Line.split("\\|");
                    if(data_parts.length<5)
                    {
                        continue;
                    }

                    String id_sk = data_parts[0];
                    String ten_sk = data_parts[1];
                    String id_lichtrinh = data_parts[2];
                    String id_diadiem = data_parts[3];
                    String id_doi_phutrach = data_parts[4];

                    thongtin_sukien tt_sk = new thongtin_sukien(id_sk,ten_sk,id_lichtrinh,id_diadiem,id_doi_phutrach);
                    ds_thongtin.add(tt_sk);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return ds_thongtin;
        }

        private Boolean ghifile(List<thongtin_sukien>ds_sk)
        {
            try(BufferedWriter bfw = new BufferedWriter(new FileWriter(FILE_PATH)))
            {
                for(thongtin_sukien sukien: ds_sk)
                {
                    String line = String.join("|",sukien.get_id_sk(),sukien.get_ten_sk(),sukien.get_id_lichtrinh(),sukien.get_id_diadiem(),sukien.get_id_doi_phutrach());
                    bfw.write(line);
                    bfw.newLine();
                }
                return true;
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return false;
            }
        }

        public Map<String,thongtin_sukien> xuat()
        {
            List<thongtin_sukien>ds_thongtin = loadtufile();
            Map<String,thongtin_sukien> MapThongtin = new HashMap<>();
            
            for(thongtin_sukien sukien : ds_thongtin)
            {
                MapThongtin.put(sukien.get_id_sk(),sukien);
            }
            return MapThongtin;
        }

        public boolean them(thongtin_sukien sukien)
        {
            try
            {
                List<thongtin_sukien> ds_sk = loadtufile();
                ds_sk.add(sukien);
                ghifile(ds_sk);
                return true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }

        public boolean xoa(String id_sk_xoa)
        {
            try
            {
                List<thongtin_sukien>ds_sk = loadtufile();
                for(int i=0;i<ds_sk.size();i++)
                {
                    thongtin_sukien sukien = ds_sk.get(i);
                    if(sukien.get_id_sk().equalsIgnoreCase(id_sk_xoa))
                    {
                        ds_sk.remove(i);
                        ghifile(ds_sk);
                        return true;
                    }
                }
                return false;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }

        public boolean sua(thongtin_sukien sukien_sua)
        {
            try
            {
                List<thongtin_sukien>ds_sk = loadtufile();
                Boolean found = false;

                for(int i=0;i<ds_sk.size();i++)
                {
                    thongtin_sukien sukien = ds_sk.get(i);
                    if(sukien.get_id_sk().equalsIgnoreCase(sukien_sua.get_id_sk()))
                    {
                        ds_sk.set(i,sukien_sua);
                        found=true;
                    }
                }
                if(found)
                {
                    ghifile(ds_sk);
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
}
