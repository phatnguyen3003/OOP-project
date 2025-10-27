package services;

import models.Schedule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleService {
    private static final String FILE_PATH = "src/database/Schedule.txt";

    public List<Schedule> readSchedulesFromFile() {
        List<Schedule> schedules = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                Schedule schedule = parseSchedule(line);
                if (schedule != null) {
                    schedules.add(schedule);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc tệp Schedule.txt: " + e.getMessage());
            e.printStackTrace();
        }
        
        return schedules;
    }

    private Schedule parseSchedule(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split("\\|");

        // Kiểm tra phải có chính xác 4 phần
        if (parts.length != 4) {
            System.err.println("Lỗi định dạng dòng: Cần 4 phần, nhưng chỉ có " + parts.length + ". Dòng: " + line);
            return null;
        }

        try {
            // parts[0]: ID Event
            int idEvent = Integer.parseInt(parts[0].trim());
            
            // parts[1]: Tên Event
            String nameEvent = parts[1].trim();
            
            // parts[2]: Danh sách ID Tiết mục
            List<Integer> performanceIds = Arrays.stream(parts[2].trim().split("\\s+"))
                                                 .map(String::trim)
                                                 .filter(s -> !s.isEmpty()) // Loại bỏ khoảng trắng thừa
                                                 .map(Integer::parseInt)
                                                 .collect(Collectors.toList());

            // parts[3]: Danh sách Thời gian bắt đầu
            List<Integer> performanceTime = Arrays.stream(parts[3].trim().split("\\s+"))
                                                 .map(String::trim)
                                                 .filter(s -> !s.isEmpty()) // Loại bỏ khoảng trắng thừa
                                                 .map(Integer::parseInt)
                                                 .collect(Collectors.toList());
            
            // Kiểm tra tính hợp lệ: Số lượng ID và số lượng Thời gian phải khớp
            if (performanceIds.size() != performanceTime.size()) {
                System.err.println("Lỗi logic dữ liệu: Số lượng ID tiết mục và Thời gian không khớp. Dòng: " + line);
                return null;
            }
            
            return new Schedule(idEvent, nameEvent, performanceIds, performanceTime);

        } catch (NumberFormatException e) {
            System.err.println("Lỗi định dạng số (NumberFormatException) trong dòng: " + line);
            return null;
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi xử lý dòng: " + line + ". Lỗi: " + e.getMessage());
            return null;
        }
    }
}