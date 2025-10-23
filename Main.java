import java.util.Scanner;
import services.ArtistService;
import services.PerformanceService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArtistService artistService = new ArtistService();
        PerformanceService performanceService = new PerformanceService();

        int choice;
        do {
            System.out.println("\n=== MENU CHÍNH ===");
            System.out.println("1. Quản lý Nghệ sĩ");
            System.out.println("2. Quản lý Tiết mục");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> artistMenu(artistService, sc);
                case 2 -> performanceMenu(performanceService, sc);
                case 0 -> System.out.println("Tạm biệt!");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void artistMenu(ArtistService artistService, Scanner sc) {
        int choice;
        do {
            System.out.println("\n=== MENU NGHỆ SĨ ===");
            System.out.println("1. Hiển thị tất cả nghệ sĩ");
            System.out.println("2. Thêm nghệ sĩ");
            System.out.println("3. Sửa nghệ sĩ");
            System.out.println("4. Xóa nghệ sĩ");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> artistService.showAllArtists();
                case 2 -> artistService.addArtistFromInput();
                case 3 -> artistService.updateArtistFromInput();
                case 4 -> artistService.deleteArtistByInput();
                case 0 -> System.out.println("↩ Quay lại menu chính...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void performanceMenu(PerformanceService performanceService, Scanner sc) {
        int choice;
        do {
            System.out.println("\n=== MENU TIẾT MỤC ===");
            System.out.println("1. Hiển thị tất cả tiết mục");
            System.out.println("2. Thêm tiết mục");
            System.out.println("3. Sửa tiết mục");
            System.out.println("4. Xóa tiết mục");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> performanceService.showAllPerformances();
                case 2 -> performanceService.addPerformanceFromInput();
                case 3 -> performanceService.updatePerformanceFromInput();
                case 4 -> performanceService.deletePerformanceByInput();
                case 0 -> System.out.println("↩ Quay lại menu chính...");
                default -> System.out.println("❌ Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
}
