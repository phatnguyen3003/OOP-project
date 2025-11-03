package services;

import java.util.*;
import java.io.*;
import javax.imageio.IIOException;
import Main_interface.main_interface.IGeneralService;

public class teamService implements IGeneralService<teamService.team> {
    public static final String file_path= "src/database/team.txt";
    public static class team{
        String iddoi;
        String idleader;
        int songuoi;
        public static List<team> dsdn= new ArrayList<>();
        public team(){}
        public team(String iddoi, String idleader, int songuoi)
        {
            this.iddoi=iddoi;
            this.idleader=idleader;
            this.songuoi=songuoi;
        }
        public String toString()
        {
            return iddoi+"|"+ idleader+"|"+songuoi;
        }
        public String getiddoi()
        {
            return iddoi;
        }
        public void setiddoi(String iddoi)
        {
            this.iddoi=iddoi;
        }
        public String getidleader()
        {
            return idleader;
        }
        public void setidleader(String idleader)
        {
            this.idleader=idleader;
        }
        public int getsonguoi()
         {
            return songuoi;
         }
         public void setsonguoi(int songuoi)
         {
            this.songuoi=songuoi;
         }
    }
    
    public static class loadpt
    {
        public static List<team> dsteam(String file_path)
        {
            List<team> luu= new ArrayList<>();
            try(BufferedReader brg= new BufferedReader(new FileReader(file_path)))
            {
                 String line;
                 
                 while((line=brg.readLine())!=null)
                 {
                    String[] path= line.split("\\|");
                    String iddoi= path[0].trim();
                    String idleader= path[1].trim();
                    int songuoi=Integer.parseInt(path[2].trim());
                    luu.add(new team(iddoi,idleader,songuoi));

                 }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            team.dsdn=luu;
            return luu;
        }
    }
    public Map<String, team> xuat()
    {
         List<team> dstam= loadpt.dsteam(file_path);
         Map<String, team> maptam= new HashMap();
         for( team ds: dstam)
         {
            maptam.put(ds.getiddoi(), ds);
         }
         return maptam;
    }
    public static void ghidoi(List<team> dsdoi)
    {
        try(BufferedWriter bfg= new BufferedWriter( new FileWriter(file_path)))
        {
                for(team ds: dsdoi)
                {
                    bfg.write(ds.toString());
                    bfg.newLine();
                }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    public boolean them(team k)
    {
        List<team> dsdoi= loadpt.dsteam(file_path);
        for(team ds: dsdoi)
        {
            if(ds.getiddoi().equalsIgnoreCase(k.getiddoi().trim()))
            {
                return false;
            }
        }
        dsdoi.add(k);
        return true;
    }
    public boolean sua(team moi)
    {
        List<team> dsdoi= loadpt.dsteam(file_path);
        boolean found=false;
        for(team ds: dsdoi)
        {
            if (ds.getiddoi().equalsIgnoreCase(moi.getiddoi().trim()))
            {
                   if(moi.getidleader()!=null && !moi.getidleader().isEmpty())
                   {
                    ds.setidleader(moi.getidleader());
                   }
                   ghidoi(dsdoi);
                   found=true;
                   break;
            }
        }
        return found;
    }
    public boolean xoa(String iddoi)
    {
        List<team> dsdoi= loadpt.dsteam(file_path);
        for(int i=0; i<dsdoi.size(); i++ )
        {
            team loc= dsdoi.get(i);
            if( loc.getiddoi().equalsIgnoreCase(iddoi.trim()))
            {
                dsdoi.remove(i);
                ghidoi(dsdoi);
                return true;
            }
        }
        return false;
    }
    
}
