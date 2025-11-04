package services;

import Main_interface.main_interface.IGeneralService;
import services.ArtistService.nghesi;
import services.PerformanceService.tietmuc;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
 
public class Schedule {
    private static final String FILE_PATH = "src/database/Schedule.txt";
    public String id_lichtrinh;              // Mã lịch trình
    public List<String> id_casi;             // Danh sách ID ca sĩ
    public List<String> id_tietmuc;          // Danh sách ID tiết mục tương ứng
 
    public Schedule() {
        id_casi = new ArrayList<>();
        id_tietmuc = new ArrayList<>();
    }
 
    public Schedule(String id_lichtrinh, List<String> id_casi, List<String> id_tietmuc) {
        this.id_lichtrinh = id_lichtrinh;
        this.id_casi = id_casi;
        this.id_tietmuc = id_tietmuc;
    }

    public static List<Schedule> loadFromFile(){
        List<Schedule> schedules = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split("\\|");
                
                if (parts.length == 3) {
                    String id_lichtrinh = parts[0].trim();
                    
                    List<String> id_casi = Arrays.stream(parts[1].trim().split("\\s+"))
                                                 .filter(s -> !s.isEmpty())
                                                 .collect(Collectors.toList());
                    
                    List<String> id_tietmuc = Arrays.stream(parts[2].trim().split("\\s+"))
                                                    .filter(s -> !s.isEmpty())
                                                    .collect(Collectors.toList());
                    
                    Schedule schedule = new Schedule(id_lichtrinh, id_casi, id_tietmuc);
                    schedules.add(schedule);
                } else {
                }
            }
        } catch (IOException e) {
        }
        
        return schedules;
    }

    public static boolean them(Schedule newSchedule) {
        String casiStr = String.join(" ", newSchedule.id_casi);
        String tietmucStr = String.join(" ", newSchedule.id_tietmuc);
        
        String scheduleLine = String.format("%s|%s|%s", 
                                            newSchedule.id_lichtrinh, 
                                            casiStr, 
                                            tietmucStr);

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            pw.println(scheduleLine);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean sua(Schedule updatedSchedule) {
        List<Schedule> allSchedules = loadFromFile();
        boolean found = false;
        
        for (int i = 0; i < allSchedules.size(); i++) {
            if (allSchedules.get(i).id_lichtrinh.equals(updatedSchedule.id_lichtrinh)) {
                allSchedules.set(i, updatedSchedule); 
                found = true;
                break;
            }
        }
        
        if (!found) {
            return false;
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH, false))) { 
            for (Schedule s : allSchedules) {
                String casiStr = String.join(" ", s.id_casi);
                String tietmucStr = String.join(" ", s.id_tietmuc);
                String scheduleLine = String.format("%s|%s|%s", s.id_lichtrinh, casiStr, tietmucStr);
                
                pw.println(scheduleLine);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean xoa(String id_lichtrinh) {
        List<Schedule> allSchedules = loadFromFile();
        
        // Dùng List.removeIf để tìm và xóa lịch trình dựa trên ID, 
        // đồng thời trả về true nếu có bất kỳ phần tử nào bị xóa
        boolean removed = allSchedules.removeIf(
            schedule -> schedule.id_lichtrinh.equalsIgnoreCase(id_lichtrinh)
        );
        
        if (!removed) {
            return false;
        }

        // Tham số false trong FileWriter để GHI ĐÈ (overwrite) nội dung cũ
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH, false))) { 
            for (Schedule s : allSchedules) {
                // Định dạng lại chuỗi để ghi vào file
                String casiStr = String.join(" ", s.id_casi);
                String tietmucStr = String.join(" ", s.id_tietmuc);
                String scheduleLine = String.format("%s|%s|%s", s.id_lichtrinh, casiStr, tietmucStr);
                
                pw.println(scheduleLine);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    

}