package services;

import java.io.*;
import java.util.*;
import Main_interface.main_interface.IGeneralService;
import abstraction.abstraction;

public class employeeService {
    private static final String File_PATH = "src/database/employee.txt";

    // =========================
    // LỚP NHÂN VIÊN
    // =========================
    public static class nhanvien extends abstraction.Nguoi 
    {
        private String calamviec;
        private String iddoi;
        private String vaitro; // thêm vai trò
        public static List<nhanvien> dsnv = new ArrayList<>();

        public nhanvien() {
            super();
        }

       
        public nhanvien(String idnv,String ten, String calamviec, String iddoi, String vaitro) {
            super(idnv, ten);
            this.calamviec = calamviec;
            this.iddoi = iddoi;
            this.vaitro = vaitro;
        }

        
        public void setca(String calamviec) {
            this.calamviec = calamviec;
        }

        public void setiddoi(String iddoi) {
            this.iddoi = iddoi;
        }

        public String getca() {
            return calamviec;
        }

        public String getiddoi() {
            return iddoi;
        }
        
         // 🔹 Ghi đè phương thức trừu tượng từ Nguoi
         @Override
        public String getVaitro() 
        {
        return vaitro;
        }
        public void setVaitro(String vaitro) {
            this.vaitro = vaitro;
        }

        
        @Override
        public String toString() {
            // Dạng: id|tên|ca|idđội|vai trò
            return id + "|" + ten + "|" + calamviec + "|" + iddoi + "|" + vaitro;
        }
    }

    
    public static class Danhsachnhanvien implements IGeneralService<employeeService.nhanvien> {

      
        public static class loadnvfile {
            public static List<nhanvien> loadnv(String File_PATH) {
                List<nhanvien> dsnhanvien = new ArrayList<>();
                try (BufferedReader ad = new BufferedReader(new FileReader(File_PATH))) {
                    String line;
                    while ((line = ad.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        String[] path = line.split("\\|");
                        if (path.length < 5) continue; // giờ có 5 cột

                        String idnv = path[0].trim();
                        String ten = path[1].trim();
                        String calamviec = path[2].trim();
                        String iddoi = path[3].trim();
                        String vaitro = path[4].trim();

                        dsnhanvien.add(new nhanvien(idnv,ten,calamviec,iddoi,vaitro));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                nhanvien.dsnv = dsnhanvien;
                return dsnhanvien;
            }
        }

      
        public Map<String, nhanvien> xuat() {
            Map<String, nhanvien> maptam = new HashMap<>();
            List<nhanvien> dstam = loadnvfile.loadnv(File_PATH);
            for (nhanvien u : dstam) {
                maptam.put(u.getId(), u);
            }
            return maptam;
        }

       
        public void ghinv(List<nhanvien> nv) {
            try (BufferedWriter brw = new BufferedWriter(new FileWriter(File_PATH))) {
                for (nhanvien ds : nv) {  
                    brw.write(ds.toString());
                    brw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       
        public boolean them(nhanvien k) {
            List<nhanvien> dsnv = loadnvfile.loadnv(File_PATH);
            for (nhanvien u : dsnv) {
                if (u.getId().equalsIgnoreCase(k.getId().trim())) {
                    return false;
                }
            }
            dsnv.add(k);
            ghinv(dsnv);
            return true;
        }

       
        public boolean sua(nhanvien moi) {
            List<nhanvien> dsnv = loadnvfile.loadnv(File_PATH);
            boolean found = false;
            for (int i = 0; i < dsnv.size(); i++) {
                nhanvien ds = dsnv.get(i);
                if (ds.getId().equalsIgnoreCase(moi.getId())) {
                    found = true;
                    dsnv.set(i, moi);
                    break;
                }
            }
            if (found) ghinv(dsnv);
            return found;
        }

       
        public boolean xoa(String idnv) {
            List<nhanvien> dsnv = loadnvfile.loadnv(File_PATH);
            boolean found = false;
            for (int i = 0; i < dsnv.size(); i++) {
                nhanvien ds = dsnv.get(i);
                if (ds.getId().equalsIgnoreCase(idnv)) {
                    found = true;
                    dsnv.remove(i);
                    break;
                }
            }
            if (found) ghinv(dsnv);
            return found;
        }
    }
}
