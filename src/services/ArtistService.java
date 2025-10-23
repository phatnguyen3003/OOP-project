package services;

import java.io.*;
import java.util.*;
import models.Artist;

public class ArtistService {
    private static final String FILE_PATH = "src/database/Artist.txt";
    private final Scanner sc = new Scanner(System.in);

    public List<Artist> getAllArtists() {
        List<Artist> artists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    artists.add(new Artist(
                            Integer.parseInt(parts[0]),  // ID
                            parts[1],                    // Name
                            parts[2],                    // Company
                            Integer.parseInt(parts[3]),  // Cost
                            Integer.parseInt(parts[4])   // PerformanceID
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ Lỗi khi đọc file nghệ sĩ: " + e.getMessage());
        }
        return artists;
    }

    private void saveAllArtists(List<Artist> artists) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Artist a : artists) {
                bw.write(a.getId() + "|" + a.getName() + "|" + a.getCompany() + "|" +
                         a.getPerformanceCost() + "|" + a.getPerformanceId());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("⚠️ Lỗi khi ghi file nghệ sĩ: " + e.getMessage());
        }
    }

    public void showAllArtists() {
        List<Artist> list = getAllArtists();
        if (list.isEmpty()) {
            System.out.println("❌ Không có nghệ sĩ nào.");
            return;
        }
        System.out.printf("%-5s %-20s %-20s %-10s %-10s%n",
                "ID", "Tên", "Công ty", "Giá", "Tiết mục");
        list.forEach(System.out::println);
    }

    public void addArtistFromInput() {
        List<Artist> artists = getAllArtists();
        System.out.print("Nhập ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Tên nghệ sĩ: ");
        String name = sc.nextLine();
        System.out.print("Công ty: ");
        String company = sc.nextLine();
        System.out.print("Giá biểu diễn: ");
        int cost = sc.nextInt();
        System.out.print("ID tiết mục (nếu có): ");
        int perfId = sc.nextInt(); sc.nextLine();

        artists.add(new Artist(id, name, company, cost, perfId));
        saveAllArtists(artists);
        System.out.println("✅ Đã thêm nghệ sĩ!");
    }

    public void deleteArtistByInput() {
        List<Artist> artists = getAllArtists();
        System.out.print("Nhập ID cần xóa: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean removed = artists.removeIf(a -> a.getId() == id);
        saveAllArtists(artists);
        System.out.println(removed ? "🗑️ Đã xóa nghệ sĩ!" : "❌ Không tìm thấy!");
    }

    public void updateArtistFromInput() {
        List<Artist> artists = getAllArtists();
        System.out.print("Nhập ID cần sửa: ");
        int id = sc.nextInt(); sc.nextLine();

        Artist found = artists.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
        if (found == null) {
            System.out.println("❌ Không tìm thấy nghệ sĩ!");
            return;
        }

        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Công ty mới: ");
        String company = sc.nextLine();
        System.out.print("Giá mới: ");
        int cost = sc.nextInt();
        System.out.print("ID tiết mục mới: ");
        int perfId = sc.nextInt(); sc.nextLine();

        artists.set(artists.indexOf(found), new Artist(id, name, company, cost, perfId));
        saveAllArtists(artists);
        System.out.println("✅ Đã cập nhật nghệ sĩ!");
    }
}
