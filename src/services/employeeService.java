package services;

import java.io.*;
import java.util.*;
import Main_interface.main_interface.IGeneralService;
import abstraction.abstraction;
public class employeeService {
    private static final String File_PATH="src/database/employee.txt";

   
  
    public static class nhanvien extends abstraction.Nguoi{
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
       maptam.put(u.getId(),u);
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
       
        List<nhanvien> dsnv= loadnvfile.loadnv(File_PATH);
        for( nhanvien u: dsnv)
        {
            if(u.getId().equalsIgnoreCase(k.getId().trim()))
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
            if(ds.getId().equalsIgnoreCase(moi.getId()))
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
            if(ds.getId().equalsIgnoreCase(idnv))
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



