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

        if (parts.length != 3) {
            System.err.println("Lỗi định dạng dòng: Không đủ 3 phần. Dòng: " + line);
            return null;
        }

        try {
            int idEvent = Integer.parseInt(parts[0].trim());
            List<Integer> performanceIds = Arrays.stream(parts[1].trim().split("\\s+"))
                                                 .map(Integer::parseInt)
                                                 .collect(Collectors.toList());

            List<Integer> performanceTime = Arrays.stream(parts[2].trim().split("\\s+"))
                                                  .map(Integer::parseInt)
                                                  .collect(Collectors.toList());
            
            return new Schedule(idEvent, "N/A", performanceIds, performanceTime);

        } catch (NumberFormatException e) {
            System.err.println("Lỗi định dạng số (NumberFormatException) trong dòng: " + line);
            return null;
        } catch (Exception e) {
            System.err.println("Lỗi không xác định khi xử lý dòng: " + line + ". Lỗi: " + e.getMessage());
            return null;
        }
    }
}