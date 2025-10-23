package models;

public class Performance {
    private int id;
    private String name;
    private int duration;  // Thời lượng (phút)
    private int artistId;  // Liên kết với nghệ sĩ

    public Performance() {}

    public Performance(int id, String name, int duration, int artistId) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.artistId = artistId;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getDuration() { return duration; }
    public int getArtistId() { return artistId; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setArtistId(int artistId) { this.artistId = artistId; }

    @Override
    public String toString() {
        return String.format("%-5d %-20s %-10d %-10d", id, name, duration, artistId);
    }
}
