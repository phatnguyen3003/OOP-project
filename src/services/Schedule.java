package services;

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
                    System.err.println("❌ Lỗi định dạng dòng trong file: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi đọc file " + FILE_PATH + ": " + e.getMessage());
        }
        
        return schedules;
    }

    public static boolean createSchedule(Schedule newSchedule) {
        String casiStr = String.join(" ", newSchedule.id_casi);
        String tietmucStr = String.join(" ", newSchedule.id_tietmuc);
        
        String scheduleLine = String.format("%s|%s|%s", 
                                            newSchedule.id_lichtrinh, 
                                            casiStr, 
                                            tietmucStr);

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            pw.println(scheduleLine);
            System.out.println("✅ Đã thêm lịch trình: " + scheduleLine);
            return true;
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi vào file " + FILE_PATH + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean updateSchedule(Schedule updatedSchedule) {
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
            System.err.println("❌ Lỗi: Không tìm thấy lịch trình có ID " + updatedSchedule.id_lichtrinh + " để sửa.");
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
            System.err.println("❌ Lỗi khi ghi đè file " + FILE_PATH + ": " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteSchedule(String id_lichtrinh) {
        List<Schedule> allSchedules = loadFromFile();
        
        // Dùng List.removeIf để tìm và xóa lịch trình dựa trên ID, 
        // đồng thời trả về true nếu có bất kỳ phần tử nào bị xóa
        boolean removed = allSchedules.removeIf(
            schedule -> schedule.id_lichtrinh.equalsIgnoreCase(id_lichtrinh)
        );
        
        if (!removed) {
            System.err.println("❌ Lỗi: Không tìm thấy lịch trình có ID " + id_lichtrinh + " để xóa.");
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
            System.out.println("✅ Xóa lịch trình ID " + id_lichtrinh + " thành công.");
            return true;
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi ghi đè file " + FILE_PATH + ": " + e.getMessage());
            return false;
        }
    }
    
    public static Map<String, List<Map<String, String>>> returnData() {
        List<Schedule> allSchedules = loadFromFile();
        
        ArtistService artistService = new ArtistService();
        Map<String, nghesi> artistMap = artistService.hienthitatcanghesi();

        PerformanceService performanceService = new PerformanceService();
        Map<String, tietmuc> performanceMap = performanceService.hienthitatcatietmuc();
        
        Map<String, List<Map<String, String>>> finalDataMap = new HashMap<>();

        for (Schedule schedule : allSchedules) {
            List<Map<String, String>> scheduleDetails = new ArrayList<>();
            
            // Lịch trình cần phải có số lượng ID ca sĩ bằng số lượng ID tiết mục
            int size = Math.min(schedule.id_casi.size(), schedule.id_tietmuc.size());

            for (int i = 0; i < size; i++) {
                String casiId = schedule.id_casi.get(i);
                String tietmucId = schedule.id_tietmuc.get(i);

                nghesi artist = artistMap.get(casiId);
                tietmuc performance = performanceMap.get(tietmucId);

                // Tạo Map chứa thông tin chi tiết cho cặp này
                Map<String, String> detailMap = new HashMap<>();

                // Thêm thông tin Ca sĩ
                detailMap.put("id_casi", casiId);
                detailMap.put("ten", artist != null ? artist.getName() : "Không tìm thấy");

                // Thêm thông tin Tiết mục
                detailMap.put("id_tietmuc", tietmucId);
                detailMap.put("tentietmuc", performance != null ? performance.gettentietmuc() : "Không tìm thấy");
                detailMap.put("thoiluong", performance != null ? String.valueOf(performance.getthoiluong()) : "0");
                
                scheduleDetails.add(detailMap);
            }
            
            // Thêm danh sách chi tiết vào Map kết quả với id_lichtrinh làm khóa
            finalDataMap.put(schedule.id_lichtrinh, scheduleDetails);
        }

        return finalDataMap;
    }
}