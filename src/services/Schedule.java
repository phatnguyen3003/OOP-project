package services;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import services.ArtistService;
import services.PerformanceService;
import services.ArtistService.nghesi;
import services.PerformanceService.tietmuc; 

public class Schedule {
    private static final String FILE_PATH = "src/database/Schedule.txt";

    public static class Schedules {
        int id_sukien; 
        String ten_sk;
        List<String>id_tietmuc = new ArrayList<>();
        List<String> ten_tietmuc = new ArrayList<>();
        List<Integer> thoiluong_tietmuc = new ArrayList<>();
        List<String> id_casi = new ArrayList<>();
        List<String> ten_casi = new ArrayList<>();

        public Schedules() {}

        public int getId_sukien() { return id_sukien; } 
        public String getTen_sk() { return ten_sk; }
        public List<String> getId_tietmuc() { return id_tietmuc; }
        public List<String> getTen_tietmuc() { return ten_tietmuc; }
        public List<Integer> getThoiluong_tietmuc() { return thoiluong_tietmuc; }
        public List<String> getId_casi() { return id_casi; }
        public List<String> getTen_casi() { return ten_casi; } 

        public void setSchedules(int id_sukien, String ten_sk, List<String>id_tietmuc) {
            this.id_sukien = id_sukien;
            this.ten_sk = ten_sk;
            this.id_tietmuc = id_tietmuc;
            ten_tietmuc.clear();
            thoiluong_tietmuc.clear();
            id_casi.clear();
            ten_casi.clear();
            
            Map<String,PerformanceService.tietmuc> dstietmuc = PerformanceService.hienthitatcatietmuc();
            Map<String, ArtistService.nghesi> dsnghesi = new ArtistService().hienthitatcanghesi();
            
            for(String id:id_tietmuc) {
                PerformanceService.tietmuc tietmucdangchon = dstietmuc.get(id);
                
                ten_tietmuc.add(tietmucdangchon.gettentietmuc());
                thoiluong_tietmuc.add(tietmucdangchon.getthoiluong());
                id_casi.add(tietmucdangchon.getId());
                
                ArtistService.nghesi nghesidangxet = dsnghesi.get(tietmucdangchon.getId());
                ten_casi.add(nghesidangxet.getName());
            }
        }

        public static Schedules parseSchedule(String line) {
            if (line == null || line.trim().isEmpty()) {
                return null;
            }

            String[] parts = line.split("\\|");

            if (parts.length < 3) {
                System.err.println("Lỗi định dạng dòng: Cần 3 phần. Dòng: " + line);
                return null;
            }

            try {
                int id_sukien = Integer.parseInt(parts[0].trim()); 
                String nameEvent = parts[1].trim();
                
                List<String> performanceIds = Arrays.stream(parts[2].trim().split("\\s+"))
                                                     .map(String::trim)
                                                     .filter(s -> !s.isEmpty())
                                                     .collect(Collectors.toList());

                Schedules newSchedule = new Schedules(); 
                newSchedule.setSchedules(id_sukien, nameEvent, performanceIds); 
                
                return newSchedule;

            } catch (NumberFormatException e) {
                System.err.println("Lỗi định dạng số (ID) trong dòng: " + line);
                return null;
            } catch (Exception e) {
                System.err.println("Lỗi không xác định khi xử lý dòng: " + line + ". Lỗi: " + e.getMessage());
                return null;
            }
        }

        public static Map<Integer, Schedules> loadAllSchedules() {
            Map<Integer, Schedules> schedulesMap = new HashMap<>(); 
            
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                
                while ((line = reader.readLine()) != null) {
                    Schedules schedule = Schedules.parseSchedule(line);
                    if (schedule != null) {
                        schedulesMap.put(schedule.getId_sukien(), schedule); 
                    }
                }
            } catch (IOException e) {
                System.err.println("Lỗi khi đọc tệp Schedule.txt: " + e.getMessage());
            }
            
            return schedulesMap;
        }
    }
}