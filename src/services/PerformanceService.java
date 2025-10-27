package services;

import services.ArtistService;


import java.io.*;
import java.util.*;


public class PerformanceService {
    private static final String FILE_PATH = "src/database/Performance.txt";
    
    public static  class tietmuc 
    {
       private String idtietmuc;
       private String tentietmuc;
       private int thoiluong;
       private String id;
       public tietmuc()
       {

       }
       public tietmuc( String idtietmuc, String tentietmuc,int thoiluong, String id)
       {
        this.idtietmuc=idtietmuc;
        this.tentietmuc=tentietmuc;
        this.thoiluong=thoiluong;
        this.id=id;

       }
       public String getidtietmuc()
       {
        return idtietmuc;
       }
       public void setidtietmuc (String idtietmuc)
       {
        this.idtietmuc=idtietmuc;
       }
       public String gettentietmuc()
       {
        return tentietmuc;
       }
       public void settentietmuc(String tentietmuc)
       {
        this.tentietmuc=tentietmuc;
      }
      public int getthoiluong()
      {
        return thoiluong;
      }    
      public void setthoiluong(int thoiluong)
      {
        this.thoiluong=thoiluong;
      }
      public String getId()
      {
        return id;
      }
      public void setId(String id)
      {
        this.id=id;
      }
      @Override 
      public String toString()
      {
        return String.format("%s|%s|%d|%s",idtietmuc,tentietmuc,thoiluong,id);
      }
    }
    public static class loadtufile
    {
        public static List<tietmuc> loadtietmuc (String FILE_PATH)
        {
           List<tietmuc> danhsachtam=new ArrayList<>();
           try(BufferedReader bfr =new BufferedReader(new FileReader(FILE_PATH)))
           {
               String line;
               while((line=bfr.readLine())!=null)
               {
                if(line.trim().isEmpty())
                    continue;
                String [] phan =line.split("\\|");
                if(phan.length<4)
                continue;
                
                 String idtietmuc = phan[0];
                String tentietmuc = phan[1];
               
                int thoiluong = Integer.parseInt(phan[2]);
                 String id = phan[3];

                tietmuc tm = new tietmuc(idtietmuc,tentietmuc,thoiluong,id);
                danhsachtam.add(tm);
               }
           }
           catch(IOException e)
           {
             e.printStackTrace();
           }        

          return danhsachtam;
        }
    }
     public Map<String,tietmuc> hienthitatcatietmuc()
    {
        Map<String,tietmuc> maptietmuc= new HashMap<>();
        List<tietmuc> danhsachtam = loadtufile.loadtietmuc(FILE_PATH);
          for( tietmuc tm : danhsachtam)
          {
            maptietmuc.put(tm.getidtietmuc(),tm);
          }
        return maptietmuc;
    }
    private void ghifile(List<tietmuc> ds) {
    try (BufferedWriter bfw = new BufferedWriter(new FileWriter(FILE_PATH))) {
        for (tietmuc tm : ds) {
            bfw.write(tm.toString());
            bfw.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public boolean themTietMuc(tietmuc moi) {
    List<tietmuc> ds = loadtufile.loadtietmuc(FILE_PATH);

    for (tietmuc tm : ds) {
   
        if (tm.getidtietmuc().equalsIgnoreCase(moi.getidtietmuc()) ||
            tm.gettentietmuc().equalsIgnoreCase(moi.gettentietmuc())) {
            
            // Nếu trùng id hoặc tên tiết mục, kiểm tra tiếp id nghệ sĩ
            if (!tm.getId().equalsIgnoreCase(moi.getId())) {
                // Nếu id nghệ sĩ khác  thi cho phép thêm
                ds.add(moi);
                ghifile(ds);
                return true;
            } else {
              
           
                return false;
            }
        }
    }

    // Nếu không trùng id hoặc tên tiết mục nào thi thêm mới bình thường
    ds.add(moi);
    ghifile(ds);
    return true;
}
public boolean xoatietmuc(String ma) {
    List<tietmuc> ds = loadtufile.loadtietmuc(FILE_PATH);

    for (int i = 0; i < ds.size(); i++) {
        tietmuc tm = ds.get(i);
        
        if (tm.getidtietmuc().equalsIgnoreCase(ma) || tm.gettentietmuc().equalsIgnoreCase(ma)) {
            ds.remove(i);
            ghifile(ds); 
            return true;
        }
    }

    return false; 
    
}


public boolean suaTietMuc(String ma, String tenMoi, Integer thoiLuongMoi, String iDMoi) 
{
    List<tietmuc> ds = loadtufile.loadtietmuc(FILE_PATH);
    boolean found = false;

    for (tietmuc tm : ds) {
        
        if (tm.getidtietmuc().equalsIgnoreCase(ma) || tm.gettentietmuc().equalsIgnoreCase(ma)) {

            if (tenMoi != null && !tenMoi.isEmpty()) {
                tm.settentietmuc(tenMoi);
            }

            if (thoiLuongMoi != null) {
                tm.setthoiluong(thoiLuongMoi);
            }

            if (iDMoi != null && !iDMoi.isEmpty()) {
                tm.setId(iDMoi);
            }

            found = true;
            break;
        }
    }

    if (found) {
        ghifile(ds); 
        return true;
    }

    return false; 
}


public ArtistService.nghesi timNgheSiTheoTietMuc(String maTietMuc) {
    // 1. Tải toàn bộ danh sách tiết mục
    List<tietmuc> ds = loadtufile.loadtietmuc(FILE_PATH);

    // 2. Tạo đối tượng ArtistService để truy danh sách nghệ sĩ
    ArtistService artistService = new ArtistService();
    Map<String, ArtistService.nghesi> dsNgheSi = artistService.hienthitatcanghesi();

    // 3. Duyệt tìm tiết mục có id hoặc tên trùng với mã truyền vào
    for (tietmuc tm : ds) {
        if (tm.getidtietmuc().equalsIgnoreCase(maTietMuc) || tm.gettentietmuc().equalsIgnoreCase(maTietMuc)) {
            
            // 4. Lấy id nghệ sĩ từ tiết mục và tìm trong map nghệ sĩ
            return dsNgheSi.get(tm.getId());
        }
    }

    // 5. Nếu không thấy thì trả về null
    return null;
}
}

