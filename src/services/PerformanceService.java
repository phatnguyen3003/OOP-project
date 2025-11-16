package services;

import services.ArtistService;
import Main_interface.main_interface.IGeneralService;
import java.io.*;
import java.util.*;


public class PerformanceService
{
    private static final String FILE_PATH = "src/database/Performance.txt";
    
    public static  class tietmuc 
    {
       private String idtietmuc;
       private String tentietmuc;
       private int thoiluong;
       
       public tietmuc()
       {

       }
       public tietmuc( String idtietmuc, String tentietmuc,int thoiluong)
       {
        this.idtietmuc=idtietmuc;
        this.tentietmuc=tentietmuc;
        this.thoiluong=thoiluong;
        

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
      
      @Override 
      public String toString()
      {
        return String.format("%s|%s|%d",idtietmuc,tentietmuc,thoiluong);
      }
    }
   
  
   public static class Danhsachtietmuc implements IGeneralService<PerformanceService.tietmuc>
 {
       private static class loadtufile
    {
        private static List<tietmuc> loadtietmuc (String FILE_PATH)
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
                if(phan.length<3)
                continue;
                
                 String idtietmuc = phan[0];
                String tentietmuc = phan[1];
               
                int thoiluong = Integer.parseInt(phan[2]);
                 

                tietmuc tm = new tietmuc(idtietmuc,tentietmuc,thoiluong);
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
     public Map<String,tietmuc> xuat()
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
public boolean them(tietmuc moi) {
    List<tietmuc> ds = loadtufile.loadtietmuc(FILE_PATH);

    for (tietmuc tm : ds) {
   
             if (tm.getidtietmuc().equalsIgnoreCase(moi.getidtietmuc()) ||
            tm.gettentietmuc().equalsIgnoreCase(moi.gettentietmuc())) {
              
                // trung thi khong them moi
                return false;
            } 
           
        }
    
    // Nếu không trùng id hoặc tên tiết mục nào thi thêm mới bình thường
    ds.add(moi);
    ghifile(ds);
    return true;
}
public boolean xoa(String ma) {
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


public boolean sua(tietmuc moi)
{
    List<tietmuc> ds = loadtufile.loadtietmuc(FILE_PATH);
    boolean found = false;
 
    for (int i = 0; i < ds.size(); i++) {
        tietmuc tm = ds.get(i);
 
        // Tìm tiết mục theo id
        if (tm.getidtietmuc().equalsIgnoreCase(moi.getidtietmuc())) {
            // Ghi đè đối tượng cũ bằng đối tượng mới
            ds.set(i, moi);
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

public String timIdNgheSiTheoTietMuc(String idTietMuc) {
    ArtistService.Danhsachnghesi artistService = new ArtistService.Danhsachnghesi();
    Map<String, ArtistService.nghesi> mapNgheSi = artistService.xuat();
 
    for (ArtistService.nghesi ns : mapNgheSi.values()) {
        if (ns.getidtietmuc().contains(idTietMuc)) {
            return ns.getId(); // Trả về ID nghệ sĩ đầu tiên tìm thấy
        }
    }
    return null; // Không tìm thấy
   }
  }
}
