package services;

import java.util.*;
import java.io.*;
import javax.imageio.IIOException;
import Main_interface.main_interface.IGeneralService;

public class teamService {
    public static final String file_path= "src/database/team.txt";
    public static class team{
        String iddoi;
        String idleader;
        int songuoi;
        List<String> ds_id;
        public static List<team> dsdn= new ArrayList<>();
        public team(){}
        public team(String iddoi, String idleader, List<String> ds_id)
        {
            this.iddoi=iddoi;
            this.idleader=idleader;
            this.ds_id= (ds_id !=null)? ds_id: new ArrayList<>();
            this.songuoi=ds_id.size();
        }
        public String toString()
        {
            return iddoi+"|"+ idleader+"|"+String.join(",", ds_id);
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
        public List<String> getds()
        {
            return ds_id;
        }
        public void setds(List<String> k)
        {
            this.ds_id=k;
            this.songuoi= (k!=null) ? k.size(): 0; 
        }
        public void doi(String k, String cu)
        {
            for(int i=0; i<ds_id.size(); i++)
            {
                String ds= ds_id.get(i);
                if(ds.trim().equalsIgnoreCase(cu.trim()))
                {
                    ds_id.set(i,k);
                    break;
                }
            }
        }
        public void addds_id(String k)
        {
            if (ds_id==null) ds_id= new ArrayList<>();
            if(ds_id.contains(k)==false) 
            {
                ds_id.add(k);
                songuoi= ds_id.size();
            }
        }
        public void remove(String k)
        {
            if(ds_id !=null)
            {
                ds_id.remove(k);
                songuoi=ds_id.size();
            }
        }
    }

    public static class DanhsachDoi implements IGeneralService<teamService.team>
    {
            
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
                     if (path.length < 3) continue;
                    String iddoi= path[0].trim();
                    String idleader= path[1].trim();
                    List<String> ds_id= new ArrayList<>();
                    if(path[2].trim()!=null && path.length >2)
                    {
                        ds_id = Arrays.asList(path[2].trim().split(","));
                    }
                    luu.add(new team(iddoi,idleader,ds_id));

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
         Map<String, team> maptam= new HashMap<>();
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
        ghidoi(dsdoi);
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
                   if(moi.getds()!=null && !moi.getds().isEmpty())
                   {
                    ds.setds(moi.getds());
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
}

