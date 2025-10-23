package services;

import java.io.*;
import java.util.*;
import models.Performance;

public class PerformanceService {
    private static final String FILE_PATH = "src/database/Performance.txt";
    private final Scanner sc = new Scanner(System.in);

    public List<Performance> getAllPerformances() {
        List<Performance> performances = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    performances.add(new Performance(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3])
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ Lỗi khi đọc file tiết mục: " + e.getMessage());
        }
        return performances;
    }

    private void saveAllPerformances(List<Performance> performances) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Performance p : performances) {
                bw.write(p.getId() + "|" + p.getName() + "|" + p.getDuration() + "|" + p.getArtistId());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("⚠️ Lỗi khi ghi file tiết mục: " + e.getMessage());
        }
    }

    public void showAllPerformances() {
        List<Performance> list = getAllPerformances();
        if (list.isEmpty()) {
            System.out.println("❌ Không có tiết mục nào.");
            return;
        }
        System.out.printf("%-5s %-20s %-10s %-10s%n", "ID", "Tên", "Thời lượng", "Nghệ sĩ ID");
        list.forEach(System.out::println);
    }

    public void addPerformanceFromInput() {
        List<Performance> list = getAllPerformances();
        System.out.print("Nhập ID tiết mục: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Tên tiết mục: ");
        String name = sc.nextLine();
        System.out.print("Thời lượng (phút): ");
        int duration = sc.nextInt();
        System.out.print("ID nghệ sĩ: ");
        int artistId = sc.nextInt(); sc.nextLine();

        list.add(new Performance(id, name, duration, artistId));
        saveAllPerformances(list);
        System.out.println("✅ Đã thêm tiết mục!");
    }

    public void deletePerformanceByInput() {
        List<Performance> list = getAllPerformances();
        System.out.print("Nhập ID cần xóa: ");
        int id = sc.nextInt(); sc.nextLine();
        boolean removed = list.removeIf(p -> p.getId() == id);
        saveAllPerformances(list);
        System.out.println(removed ? "🗑️ Đã xóa tiết mục!" : "❌ Không tìm thấy!");
    }

    public void updatePerformanceFromInput() {
        List<Performance> list = getAllPerformances();
        System.out.print("Nhập ID cần sửa: ");
        int id = sc.nextInt(); sc.nextLine();

        Performance found = list.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (found == null) {
            System.out.println("❌ Không tìm thấy tiết mục!");
            return;
        }

        System.out.print("Tên mới: ");
        String name = sc.nextLine();
        System.out.print("Thời lượng mới: ");
        int duration = sc.nextInt();
        System.out.print("ID nghệ sĩ mới: ");
        int artistId = sc.nextInt(); sc.nextLine();

        list.set(list.indexOf(found), new Performance(id, name, duration, artistId));
        saveAllPerformances(list);
        System.out.println("✅ Đã cập nhật tiết mục!");
    }
}
