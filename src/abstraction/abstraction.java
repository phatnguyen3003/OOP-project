package abstraction;

public class abstraction {
    
     public interface INguoi
     {
       String getId();
       String getName();
       void setId(String id);
       void setName(String name);
     }
    public static abstract class Nguoi implements INguoi
    {
        public String id;
        public String ten;
        public Nguoi()
        {
 
        }
        public Nguoi(String id, String ten)
        {
            this.id=id;
            this.ten=ten;
        }
        public String getId()
        {
            return id;
        }
        public String getName()
        {
            return ten;
        }
        public void setId( String id)
        {
            this.id=id;
        }
        public void setName(String ten)
        {
            this.ten=ten;
        }
        public abstract String getVaitro();
        
        @Override
        public abstract String toString();
    }

}
