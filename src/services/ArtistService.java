package services;

import Main_interface.main_interface;
import Main_interface.main_interface.IGeneralService;
import abstraction.abstraction;
import java.io.*;
import java.util.*;

public class ArtistService 
{
    private static final String FILE_PATH = "src/database/Artist.txt";

   
    public  static class nghesi extends abstraction.Nguoi
    {  
        
       private String vaitro;
       private String congty;
       private int giathanh;
       private List<String> idtietmuc = new ArrayList<>();
       public nghesi()
     {
     }      
     public nghesi(String id,String ten,String vaitro,String congty,int giathanh,List<String> idtietmuc)
     {
        super(id,ten);
        this.vaitro=vaitro;
        this.congty=congty;
        this.giathanh=giathanh;
        this.idtietmuc=idtietmuc;
     }
     public String getcongty()
     {
        return congty;
     }
     public void setcongty(String congty)
     {
        this.congty=congty;
     }
     public int getgiathanh()
     {
        return giathanh;
     }
     public void setgiathanh(int giathanh)
     {
        this.giathanh=giathanh;
     }
     public List<String> getidtietmuc()
     {
     return idtietmuc;
     }
     public void setidtietmuc(List<String> idtietmuc)
     {
          this.idtietmuc=idtietmuc;
     }
       // 🔹 Ghi đè phương thức trừu tượng từ Nguoi
    @Override
    public String getVaitro() {
        return vaitro;
    }
     public void setvaitro(String vaitro)
     {
      this.vaitro=vaitro;
     }

    @Override
    public String toString() 
    {
    String tietmucString = String.join(",", idtietmuc);
    return String.format("%s|%s|%s|%s|%d|%s", id, ten,vaitro,congty, giathanh, tietmucString);
    }
   
    }
    
 public static class Danhsachnghesi implements IGeneralService<ArtistService.nghesi>
 {
    private static class loadtufile
    {
      private static List<nghesi>loadnghesi(String filePath)
      {
        List<nghesi> danhsachtam = new ArrayList<>();
        try(BufferedReader bfr= new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line=bfr.readLine())!=null) //trong khi con doc dong
            {
                if(line.trim().isEmpty()) //bo dong rong
                continue;
                   String[] phan = line.split("\\|");
                if(phan.length < 6)
                continue;
                
                  String id = phan[0];
                String ten = phan[1];
                String vaitro =phan[2];
                String congty= phan[3];
                int giathanh = Integer.parseInt(phan[4]);

                List<String> idtietmuc = new ArrayList<>();
                String[] tietmucs= phan[5].split(",");
                idtietmuc.addAll(Arrays.asList(tietmucs));
                
                nghesi ns = new nghesi(id, ten, vaitro, congty, giathanh, idtietmuc);
                danhsachtam.add(ns);
            }
        }
        catch (IOException e) 
        {
                e.printStackTrace();
        }
        return danhsachtam;
      }
    }
    public Map<String,nghesi> xuat() 
    {
        Map<String,nghesi> mapnghesi= new HashMap<>();
        List<nghesi> danhsachtam = loadtufile.loadnghesi(FILE_PATH);
          for( nghesi ns : danhsachtam)
          {
            mapnghesi.put(ns.getId(),ns);
          }
        return mapnghesi;
    }
   
    private void ghifile(List<nghesi> ds)
    {
      try(BufferedWriter bfw= new BufferedWriter(new FileWriter(FILE_PATH)))
      {
           for(nghesi ns: ds)
           {
             bfw.write(ns.toString());
             bfw.newLine();             
           }
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
    }
    
    public boolean them(nghesi moi) {
    List<nghesi> ds = loadtufile.loadnghesi(FILE_PATH);

    for (nghesi ns : ds) {
        // Nếu trùng ID hoặc trùng tên, thì không thêm nữa
        if (ns.getId().equalsIgnoreCase(moi.getId()) || ns.getName().equalsIgnoreCase(moi.getName())) {
            return false;
        }
    }

    // Nếu không trùng ai, thêm mới hoàn toàn
    ds.add(moi);
    ghifile(ds);
    return true;
}
   public boolean xoa(String ma) // mã ở đây dùng đc cho cả id cua nghệ sĩ và tên nghệ sĩ , do có cùng kiểu String
   {
       List<nghesi> ds = loadtufile.loadnghesi(FILE_PATH);

       for(int i=0;i<ds.size();i++)
       {
        nghesi ns = ds.get(i); // tham chiếu đối tượng nghệ sĩ thứ i trong ds
        if(ns.getId().equalsIgnoreCase(ma) || ns.getName().equalsIgnoreCase(ma))
        {
            ds.remove(i);
            ghifile(ds);
            return true;
        } 
       }
       
    
    return false;
   }


   

   public boolean sua(nghesi moi) {
    List<nghesi> ds = loadtufile.loadnghesi(FILE_PATH);
    boolean found = false;

    for (int i = 0; i < ds.size(); i++) {
        nghesi ns = ds.get(i);

        // Tìm nghệ sĩ theo ID (đây là khóa duy nhất)
        if (ns.getId().equalsIgnoreCase(moi.getId())) {
            // Ghi đè đối tượng cũ bằng đối tượng mới
            ds.set(i, moi);
            found = true;
            break;
        }
    }

    if (found) {
        ghifile(ds); // Ghi toàn bộ danh sách xuống file
        return true;
    }

    return false; // Không tìm thấy nghệ sĩ cần sửa
   }
 }
}
