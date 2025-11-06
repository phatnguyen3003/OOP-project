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

import Main_interface.main_interface;
 
public class ScheduleService
{
    private static final String FILE_PATH = "src/database/Schedule.txt";
    public static class Schedule
    {
        public String id_lichtrinh;              // Mã lịch trình
        public List<String> id_tietmuc;          // Danh sách ID tiết mục tương ứng

    public Schedule() {
        id_tietmuc = new ArrayList<>();
    }
 
    public Schedule(String id_lichtrinh, List<String> id_casi, List<String> id_tietmuc) {
        this.id_lichtrinh = id_lichtrinh;
        this.id_tietmuc = id_tietmuc;
    }

    public String getIdLichTrinh() { return id_lichtrinh; }
    public void setIdLichTrinh(String id) { this.id_lichtrinh = id; }

    public List<String> getIdTietmuc() { return id_tietmuc; }
    public void setIdTietmuc(List<String> list) { this.id_tietmuc = list; }
    }

    public static class DanhsachLichtrinh implements IGeneralService<ScheduleService.Schedule>
    {
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
                    
                
                    ScheduleService.Schedule schedule = new ScheduleService.Schedule(id_lichtrinh, id_casi, id_tietmuc);
                    schedules.add(schedule);
                } else {
                }
            }
        } catch (IOException e) {
        }
        
        return schedules;
    }

    public  boolean them(ScheduleService.Schedule newSchedule) {
        String tietmucStr = String.join(" ", newSchedule.id_tietmuc);
        
        String scheduleLine = String.format("%s|%s|%s", 
                                            newSchedule.id_lichtrinh,
                                            tietmucStr);

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            pw.println(scheduleLine);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public  boolean sua(ScheduleService.Schedule updatedSchedule) {
        List<ScheduleService.Schedule> allSchedules = loadFromFile();
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
            for (ScheduleService.Schedule s : allSchedules) {
                String tietmucStr = String.join(" ", s.id_tietmuc);
                String scheduleLine = String.format("%s|%s|%s", s.id_lichtrinh, tietmucStr);
                
                pw.println(scheduleLine);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public  boolean xoa(String id_lichtrinh) {
        List<ScheduleService.Schedule> allSchedules = loadFromFile();
        
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
            for (ScheduleService.Schedule s : allSchedules) {
                // Định dạng lại chuỗi để ghi vào file
                String tietmucStr = String.join(" ", s.id_tietmuc);
                String scheduleLine = String.format("%s|%s|%s", s.id_lichtrinh, tietmucStr);
                
                pw.println(scheduleLine);
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    public Map<String, ScheduleService.Schedule> xuat() {
        Map<String, ScheduleService.Schedule> mapSchedule = new HashMap<>();
        List<Schedule> allSchedules = loadFromFile();
        
        for (ScheduleService.Schedule schedule : allSchedules) {
            mapSchedule.put(schedule.id_lichtrinh, schedule);
        }
        return mapSchedule;
    }

    }
}
