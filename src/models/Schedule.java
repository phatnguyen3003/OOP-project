package models;

import java.util.List;
import java.util.ArrayList;

public class Schedule {
    private int idEvent;
    private String nameEvent;
    private List<Integer> performanceIds;
    private List<Integer> performanceTime;


    public Schedule() {
        this.performanceIds = new ArrayList<>();
        this.performanceTime = new ArrayList<>();
    }

    public Schedule(int idEvent, String nameEvent, List<Integer> performanceIds, List<Integer> performanceTime) {
        this.idEvent = idEvent;
        this.nameEvent = nameEvent;
        this.performanceIds = performanceIds;
        this.performanceTime = performanceTime;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public List<Integer> getPerformanceIds() {
        return performanceIds;
    }

    public List<Integer> getPerformanceTime() {
        return performanceTime;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public void setPerformanceIds(List<Integer> performanceIds) {
        this.performanceIds = performanceIds;
    }

    public void setPerformanceTime(List<Integer> performanceTime) {
        this.performanceTime = performanceTime;
    }


    @Override
    public String toString() {
        return "Schedule{" +
               "idEvent=" + idEvent +
               ", nameEvent='" + nameEvent + '\'' +
               ", performanceIds=" + performanceIds +
               ", performanceTime=" + performanceTime +
               '}';
    }
    
    public void addPerformance(int id, int time) {
        this.performanceIds.add(id);
        this.performanceTime.add(time);
    }
}