package models;

import java.util.List;
import java.util.ArrayList;

public class Schedule {
    private int id;
    private String name;
    private List<Integer> performanceIds;

    // Constructor mặc định (cần cho việc tạo đối tượng rỗng)
    public Schedule() {
        this.performanceIds = new ArrayList<>();
    }

    // Constructor đầy đủ
    public Schedule(int id, String name, List<Integer> performanceIds) {
        this.id = id;
        this.name = name;
        this.performanceIds = performanceIds;
    }

    // Get
    public int getId() { return id; }
    public String getName() { return name; }
    public List<Integer> getPerformanceIds() { return performanceIds; }

    // Set
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPerformanceIds(List<Integer> performanceIds) { this.performanceIds = performanceIds; }

    @Override
    public String toString() {
        return String.format("%-5d %-30s ID Tiết mục: %s", id, name, performanceIds.toString());
    }
}