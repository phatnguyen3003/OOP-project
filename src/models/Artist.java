package models;

public class Artist extends Person {
    private String company;
    private int performanceCost;
    private int performanceId;

    public Artist() {}

    public Artist(int id, String name, String company, int performanceCost, int performanceId) {
        super(id, name);
        this.company = company;
        this.performanceCost = performanceCost;
        this.performanceId = performanceId;
    }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public int getPerformanceCost() { return performanceCost; }
    public void setPerformanceCost(int performanceCost) { this.performanceCost = performanceCost; }

    public int getPerformanceId() { return performanceId; }
    public void setPerformanceId(int performanceId) { this.performanceId = performanceId; }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-20s %-10d %-10d",
                id, name, company, performanceCost, performanceId);
    }
}