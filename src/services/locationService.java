package  services;
import java.io.*;
import java.util.*;

public class locationService {
    private static final String FILE_PATH = "src/database/Location.txt";
    public static class location {
        private String tendiadiem;
        private int succhua;

        public static List<location> dsdiadiem = new ArrayList<>();
        public location(String tendiadiem, int succhua)
        {
            this.tendiadiem= tendiadiem;
            this.succhua=succhua;
        }
        public void xuat()
        {
            System.out.println("dia diem to chuc: "+tendiadiem+" suc chua: "+succhua);
        }
        public String getdiadiem()
        {
            return tendiadiem;
        }
        public int getsucchua()
        {
            return succhua;
        }
        public String toString()
        {
            return tendiadiem+"|"+ succhua;
        }
        public void setdiadiem(String tendiadiem)
        {
            this.tendiadiem=tendiadiem;
        }
        public void setsucchua(int succhua)
        {
            this.succhua=succhua;
        }
        
       
    }
    public static class loadfile
    {
        public static List<location> loaddiadiem(String FILE_PATH)
        {
            List<location> ds= new ArrayList<>();
           
           try(BufferedReader br= new BufferedReader(new FileReader(FILE_PATH)))
           {
                 String line;
                 while( (line =br.readLine())!=null)
                 {
                    String[] path= line.split("\\|");
                    String tendiadiem= path[0].trim();
                    int succhua= Integer.parseInt(path[1].trim());
                    ds.add(new location(tendiadiem, succhua));
                 }
           }
           catch (IOException e)
           {
                    e.printStackTrace();
           }
           location.dsdiadiem=ds;
           return ds;
        }
    }
   public static Map<String, location> chuyendoi()
   {
    Map<String, location> maptam= new HashMap<>();
    List<location> ds= loadfile.loaddiadiem(FILE_PATH);
    for(location tam: ds)
    {
        maptam.put(tam.tendiadiem, tam);
    }
    return maptam;
   }
   public void writerfile(List<location> ds)
   {
    try(BufferedWriter brf= new BufferedWriter(new FileWriter(FILE_PATH)))
    {
        for( location dd: ds)
        {
            brf.write(dd.toString());
            brf.newLine();
        }
    }
    catch(IOException a)
    {
        a.printStackTrace();
    }
   }
   public boolean them(location k)
   {
    List<location> dsdiadiem= loadfile.loaddiadiem(FILE_PATH);
       boolean found=false;
            
            
            for(location f: dsdiadiem)
                 if(f.getdiadiem().equalsIgnoreCase(k.getdiadiem()))
                    { found=true;  break;}
                if(found==true) 
                      return false;
                        else
                      {  dsdiadiem.add(k); 
                        writerfile(dsdiadiem);
                        return true; }
   }

   public boolean sua(String cu,String diadiem, int succhua)
        {
             List<location> dsdiadiem= loadfile.loaddiadiem(FILE_PATH);
            boolean found=false;
            for( location loc: dsdiadiem)
            {
               if(loc.getdiadiem().equalsIgnoreCase(cu))
               {
                       if(diadiem!=null&& !diadiem.trim().isEmpty())
                           loc.setdiadiem(diadiem);
                        if (succhua>0)
                           loc.setsucchua(succhua);   
                        found=true;
                        System.out.println("Da cap nhat");
                        break;   
               }
                
            }
            
            
            if(found==true) 
            writerfile(dsdiadiem);
            return found; 

        }
         public boolean xoa(String diadiem)
        {
           List<location> dsdiadiem=loadfile.loaddiadiem(FILE_PATH);
           boolean found=false;
           for( int i=0; i<dsdiadiem.size(); i++)
           {
            location loc= dsdiadiem.get(i);
            if(loc.getdiadiem().equalsIgnoreCase(diadiem))
              {
                found=true;
                dsdiadiem.remove(i);
                break;
              }
           }
           if(found==true)
            {
                writerfile(dsdiadiem);
            }
           return found;
        }
        
        
}