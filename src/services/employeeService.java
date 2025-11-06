package services;

import java.io.*;
import java.util.*;
import Main_interface.main_interface.IGeneralService;
public class employeeService {
    private static final String File_PATH="src/database/employee.txt";

    public interface INguoi
    {
       String getten();
       String getid();
       void setten(String ten);
       void setid(String id);
    }
    public static abstract class Nguoi implements INguoi  {
        String ten;
        String id;

        public Nguoi() {}

        public Nguoi(String ten, String id) {
            this.ten = ten;
            this.id = id;
        }
        public void setten(String ten)
        {
             this.ten=ten;
        }
       public void setid(String id)
        {
            this.id=id;
        }
        public String getten()
        {
            return ten;
        }
        public String getid()
        {
            return id;
        }
        public abstract String toString();

        
    }
  
    public static class nhanvien extends Nguoi{
        private String calamviec;
        private String iddoi;
        public static List<nhanvien> dsnv= new ArrayList<>();
        public nhanvien()
        {
            super();
        }
        public nhanvien(String calamviec, String idnv, String iddoi, String ten)
        {
             super(ten, idnv);
             this.calamviec= calamviec;
             this.iddoi=iddoi;
        }
        @Override
        public String toString()
        {
            return id +"|"+ten+"|"+calamviec+"|"+iddoi;
        }
        public void setca(String calamviec)
        {
            this.calamviec=calamviec;
        }
        
        public void setiddoi(String iddoi)
        {
            this.iddoi=iddoi;
        }
        public String getca()
        {
            return calamviec;
        }
        public String getiddoi()
        {
            return iddoi;
        }
    }

    public static class Danhsachnhanvien implements IGeneralService<employeeService.nhanvien>
    {
            public static class loadnvfile{
          public static List<nhanvien> loadnv(String File_PATH)
    {
       List<nhanvien> dsnhanvien= new ArrayList<>();
       try(BufferedReader ad= new BufferedReader(new FileReader(File_PATH)) )
       {
             String line;
             while((line=ad.readLine())!=null)
             {
                
                String[] path= line.split("\\|");
                if(path.length < 4)
                continue;
                String idnv= path[0].trim();
                String ten= path[1].trim();
                String calamviec= path[2].trim();
                switch (calamviec) {
                        case "1": calamviec = "sáng"; break;
                        case "2": calamviec = "chiều"; break;
                        case "3": calamviec = "tối"; break;
                    }
                String iddoi =path[3].trim();
                dsnhanvien.add(new nhanvien(calamviec,idnv,iddoi,ten));
             }
       }
       catch (IOException e)
       {
        e.printStackTrace();
       }
       nhanvien.dsnv=dsnhanvien;
       return dsnhanvien;
    }
    }
    public Map<String, nhanvien> xuat()
    {
      Map<String, nhanvien> maptam= new HashMap();
      List<nhanvien> dstam= loadnvfile.loadnv(File_PATH);
      for( nhanvien u: dstam)
      {
         switch (u.calamviec) {
                        case "1": u.calamviec = "sáng"; break;
                        case "2": u.calamviec = "chiều"; break;
                        case "3": u.calamviec = "tối"; break;
                    }

       maptam.put(u.getid(),u);
      }
      return maptam;
    }
    public void ghinv(List<nhanvien> nv)
    {
        try(BufferedWriter brw= new BufferedWriter(new FileWriter(File_PATH)))
        {
            for(nhanvien ds: nv)
            {
                

                // 🔹 Chuyển đổi chữ → số
                switch (ds.getca().trim()) {
                    case "sáng": ds.setca("1"); break;
                    case "chiều": ds.setca("2"); break;
                    case "tối": ds.setca("3"); break;
                }

               brw.write(ds.toString());
               brw.newLine();
            }
            }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public boolean them(nhanvien k)
    {
       
        List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
        for( nhanvien u: dsnv)
        {
            if(u.getid().equalsIgnoreCase(k.getid().trim()))
            {
                return false;
            }
        }
            dsnv.add(k);
            ghinv(dsnv);
         return true;

       
    }
    public boolean sua(nhanvien moi)
    {
        
         List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
         boolean found=false;
        for( int i=0; i<dsnv.size(); i++ )
        {
            nhanvien ds= dsnv.get(i);
            if(ds.getid().equalsIgnoreCase(moi.getid()))
            {
                found=true;
                dsnv.set(i,moi);
                break;    
            }
        }
       if(found==true) ghinv(dsnv);
       return found;
    }
    public boolean xoa(String idnv)
    {
        
        List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
        boolean found=false;
        for( int i=0; i<dsnv.size(); i++)
        {
            nhanvien ds= dsnv.get(i);
            if(ds.getid().equalsIgnoreCase(idnv))
            {
                found =true;
                dsnv.remove(i);
                break;
            }
        }
        
        
        if(found==true)  ghinv(dsnv);
        return found;
    }
    }
    
}




