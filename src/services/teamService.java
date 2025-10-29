package services;

import java.util.*;
import java.io.*;
import javax.imageio.IIOException;

public class teamService {
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
            emplyeeService em= new emplyeeService();
            try(BufferedReader brg= new BufferedReader(new FileReader(file_path)))
            {
                 String line;
                 
                 while((line=brg.readLine())!=null)
                 {
                    String[] path= line.split("\\|");
                    String iddoi= path[0].trim();
                    String idleader= path[1].trim();
                    int songuoi=Integer.parseInt(path[2].trim());
                    songuoi= em.demiddoi(iddoi);
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
    public static Map<String, team> chuyendoidoi()
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
        emplyeeService em= new emplyeeService();
        try(BufferedWriter bfg= new BufferedWriter( new FileWriter(file_path)))
        {
                for(team ds: dsdoi)
                {
                    int songuoi= em.demiddoi(ds.getiddoi());
                    ds.setsonguoi(songuoi);   //để dùng khi thêm, xóa nhân viên bên employeeService//
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
    public boolean sua(String iddoi, String idleader)
    {
        List<team> dsdoi= loadpt.dsteam(file_path);
        emplyeeService em= new emplyeeService();
        boolean found=false;
        for(team ds: dsdoi)
        {
            if (ds.getiddoi().equalsIgnoreCase(iddoi.trim()))
            {
                   if(idleader!=null && !idleader.isEmpty()&& em.ktlai(idleader))
                   {
                    ds.setidleader(idleader);
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
        emplyeeService em = new emplyeeService();
        for(int i=0; i<dsdoi.size(); i++ )
        {
            team loc= dsdoi.get(i);
            if( loc.getiddoi().equalsIgnoreCase(iddoi.trim()))
            {
                dsdoi.remove(i);
                em.xoadoi(iddoi);
                ghidoi(dsdoi);
                return true;
            }
        }
        return false;
    }
    public void giamnv(String iddoi)
    {
        emplyeeService em= new emplyeeService();
        int songuoi=em.demiddoi(iddoi);
        List<team> dsdoi=loadpt.dsteam(file_path);
        for( team ds: dsdoi)
        {
            if (ds.getiddoi().equalsIgnoreCase(iddoi))
            {
                ds.setsonguoi(songuoi);
                break;
            }
        }
        ghidoi(dsdoi);
    }
    
}
