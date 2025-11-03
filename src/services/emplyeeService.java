package services;

import java.io.*;
import java.util.*;
import Main_interface.main_interface.IGeneralService;
public class emplyeeService implements IGeneralService<emplyeeService.nhanvien> {
    private static final String File_PATH="src/database/employee.txt";

    public interface INguoi
    {
       void getten();
       String setten(String ten);
    }
    public static abstract class Nguoi {
        String ten;
        String ngaysinh;

        public Nguoi() {}

        public Nguoi(String ten, String ngaysinh) {
            this.ten = ten;
            this.ngaysinh = ngaysinh;
        }
        public void setten(String ten)
        {
             this.ten=ten;
        }
        public void setngaysing(String ngaysinh)
        {
            this.ngaysinh=ngaysinh;
        }
        public String getten()
        {
            return ten;
        }
        public String getngaysinh()
        {
            return ngaysinh;
        }
        public abstract String toString();

        
    }
  
    public static class nhanvien extends Nguoi{
        private String calamviec;
        private String idnv;
        private String iddoi;
        public static List<nhanvien> dsnv= new ArrayList<>();
        public nhanvien()
        {
            super();
        }
        public nhanvien(String calamviec, String idnv, String iddoi, String ten, String ngaysinh)
        {
             super(ten, ngaysinh);
             this.calamviec= calamviec;
             this.iddoi=iddoi;
             this.idnv=idnv;   
        }
        @Override
        public String toString()
        {
            return idnv +"|"+ten+"|"+calamviec+"|"+ngaysinh+"|"+iddoi;
        }
        public void setca(String calamviec)
        {
            this.calamviec=calamviec;
        }
        public void setidnv(String idnv)
        {
            this.idnv=idnv;
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
        public String getidnv()
        {
            return idnv;
        }
    }
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
                if(path.length < 5)
                continue;
                String idnv= path[0].trim();
                String ten= path[1].trim();
                String calamviec= path[2].trim();
                String ngaysinh= path[3].trim();
                String iddoi =path[4].trim();
                dsnhanvien.add(new nhanvien(calamviec,idnv,iddoi,ten,ngaysinh));
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
       maptam.put(u.getidnv(),u);
      }
      return maptam;
    }
    public void ghinv(List<nhanvien> nv)
    {
        try(BufferedWriter brw= new BufferedWriter(new FileWriter(File_PATH)))
        {
            for(nhanvien ds: nv)
            {
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
        teamService ts= new teamService();
        List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
        for( nhanvien u: dsnv)
        {
            if(u.getidnv().equalsIgnoreCase(k.getidnv().trim()))
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
        teamService ts=new teamService();
         List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
         boolean found=false;
        for( int i=0; i<dsnv.size(); i++ )
        {
            nhanvien ds= dsnv.get(i);
            if(ds.getidnv().equalsIgnoreCase(moi.getidnv()))
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
        teamService ts= new teamService();
        List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
        boolean found=false;
        for( int i=0; i<dsnv.size(); i++)
        {
            nhanvien ds= dsnv.get(i);
            if(ds.getidnv().equalsIgnoreCase(idnv))
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


