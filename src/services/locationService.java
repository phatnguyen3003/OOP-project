package  services;
import java.io.*;
import java.util.*;
import Main_interface.main_interface.IGeneralService;

public class locationService implements IGeneralService<locationService.location> {
    private static final String FILE_PATH = "src/database/Location.txt";
    public static class location {
        private String tendiadiem;
        private int succhua;
        private String iddiadiem;
        
        public static List<location> dsdiadiem = new ArrayList<>();
        public location(String tendiadiem, int succhua,String iddiadiem)
        {
            this.tendiadiem= tendiadiem;
            this.succhua=succhua;
            this.iddiadiem=iddiadiem;
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
            return iddiadiem+"|"+tendiadiem+"|"+ succhua;
        }
        public void setdiadiem(String tendiadiem)
        {
            this.tendiadiem=tendiadiem;
        }
        public void setsucchua(int succhua)
        {
            this.succhua=succhua;
        }
        public void setiddiadiem(String iddiadiem)
        {
            this.iddiadiem=iddiadiem;
        }
        public String getiddd()
        {
            return iddiadiem;
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
                    String iddiadiem= path[0].trim();
                    String tendiadiem= path[1].trim();
                    int succhua= Integer.parseInt(path[2].trim());
                    ds.add(new location(tendiadiem, succhua, iddiadiem));
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
   public Map<String, location> xuat()
   {
    Map<String, location> maptam= new HashMap<>();
    List<location> ds= loadfile.loaddiadiem(FILE_PATH);
    for(location tam: ds)
    {
        maptam.put(tam.getiddd(), tam);
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

   public boolean sua(location moi)
        {
             List<location> dsdiadiem= loadfile.loaddiadiem(FILE_PATH);
            boolean found=false;
            for( int i=0; i<dsdiadiem.size(); i++)
            {
                location loc=dsdiadiem.get(i);
               if(loc.getiddd().equalsIgnoreCase(moi.getiddd()))
               {
                      dsdiadiem.set(i,moi);
                      found= true;
                break;   
               }
                
            }
            
            
            if(found==true) 
            writerfile(dsdiadiem);
            return found; 

        }
         public boolean xoa(String iddiadiem)
        {
           List<location> dsdiadiem=loadfile.loaddiadiem(FILE_PATH);
           boolean found=false;
           for( int i=0; i<dsdiadiem.size(); i++)
           {
            location loc= dsdiadiem.get(i);
            if(loc.getiddd().equalsIgnoreCase(iddiadiem))
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
