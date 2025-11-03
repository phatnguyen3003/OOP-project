package Main_interface;

import java.util.*;
public class main_interface {


    public interface IGeneralService<T> {

        Map<String, T> xuat();

        boolean them(T obj);

        boolean sua(T obj);

        boolean xoa(String id);
    }
}
