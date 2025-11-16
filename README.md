# HỆ THỐNG QUẢN LÝ TỔ CHỨC SỰ KIỆN

## 📋 MỤC LỤC
1. [Giới thiệu](#giới-thiệu)
2. [Cấu trúc dự án](#cấu-trúc-dự-án)
3. [Chi tiết các thành phần](#chi-tiết-các-thành-phần)
4. [Hướng dẫn sử dụng](#hướng-dẫn-sử-dụng)
5. [Ràng buộc dữ liệu](#ràng-buộc-dữ-liệu)

---

## 🎯 GIỚI THIỆU

Hệ thống quản lý tổ chức sự kiện là ứng dụng Java Swing giúp quản lý toàn bộ quy trình tổ chức sự kiện bao gồm:
- Quản lý nghệ sĩ/ca sĩ
- Quản lý tiết mục biểu diễn
- Quản lý nhân viên
- Quản lý đội ngũ tổ chức
- Quản lý địa điểm
- Quản lý lịch trình sự kiện
- Quản lý thông tin sự kiện

**Công nghệ:** Java Swing, File-based Database (Text files)

---

## 📁 CẤU TRÚC DỰ ÁN

```
eventmanagement/
│
├── do_an_nhom_2.java                          # File chạy chính của ứng dụng
│
└── src/
    ├── abstraction/                 # Các lớp trừu tượng
    │   └── abstraction.java         # Interface và abstract lớp Nguoi
    │
    ├── Main_interface/              # Interface chung
    │   └── main_interface.java      # IGeneralService interface
    │
    ├── database/                    # Cơ sở dữ liệu dạng text file
    │   ├── Artist.txt               # Dữ liệu nghệ sĩ
    │   ├── employee.txt             # Dữ liệu nhân viên
    │   ├── Event_Information.txt    # Dữ liệu sự kiện
    │   ├── location.txt             # Dữ liệu địa điểm
    │   ├── Performance.txt          # Dữ liệu tiết mục
    │   ├── Schedule.txt             # Dữ liệu lịch trình
    │   └── team.txt                 # Dữ liệu đội ngũ
    │
    ├──lớps/                    # Cáclớp xử lý logic nghiệp vụ
    │   ├── ArtistService.java       #lớp quản lý nghệ sĩ
    │   ├── employeeService.java     #lớp quản lý nhân viên
    │   ├── Event_Information.java   #lớp quản lý thông tin sự kiện
    │   ├── locationService.java     #lớp quản lý địa điểm
    │   ├── MainFunction.java        # Các hàm chung và dialog thêm dữ liệu
    │   ├── PerformanceService.java  #lớp quản lý tiết mục
    │   ├── ScheduleService.java     #lớp quản lý lịch trình
    │   └── teamService.java         #lớp quản lý đội ngũ
    │
    └── function/                    # Các giao diện quản lý
        ├── artist_manage.java       # Giao diện quản lý nghệ sĩ
        ├── employee_manage.java     # Giao diện quản lý nhân viên
        ├── framefunction.java       # Các hàm tiện ích cho frame
        ├── location_manage.java     # Giao diện quản lý địa điểm
        ├── performance_manage.java  # Giao diện quản lý tiết mục
        ├── schedule_manage.java     # Giao diện quản lý lịch trình
        └── team_manage.java         # Giao diện quản lý đội ngũ
```

---

## 🔍 CHI TIẾT CÁC THÀNH PHẦN

### 1. **do_an_nhom_2.java** - File chính
**Vị trí:** Thư mục gốc  
**Chức năng:** 
- Khởi chạy ứng dụng
- Hiển thị giao diện chính với 2 tab:
  - Tab "Sự kiện": Hiển thị danh sách các sự kiện
  - Tab "Cơ sở dữ liệu": Truy cập các module quản lý
- Các nút chức năng: Làm mới, Thêm sự kiện, Xóa

**Dòng code quan trọng:**
- Dòng 23-24: Khởi tạo JFrame chính
- Dòng 48-49: Tạo 2 nút chuyển tab
- Dòng 66-82: Load và hiển thị danh sách sự kiện
- Dòng 102-139: Tạo các nút truy cập module quản lý

---

### 2. **abstraction/abstraction.java**
**Chức năng:** Định nghĩa các class và interface cơ bản

**Các thành phần:**

#### **INguoi Interface** (Dòng 5-11)
```java
public interface INguoi {
    String getId();
    String getName();
    void setId(String id);
    void setName(String name);
}
```

#### **Nguoi Abstract Class** (Dòng 12-47)
- Kế thừa từ INguoi
- Chứa thuộc tính: `id`, `ten`
- Phương thức trừu tượng: `getVaitro()`, `toString()`
- Được kế thừa bởi: `nghesi` (Artist), `nhanvien` (Employee)

---

### 3. **Main_interface/main_interface.java**
**Chức năng:** Định nghĩa interface chung cho tất cả cáclớp

#### **IGeneralService<T> Interface** (Dòng 7-15)
```java
public interface IGeneralService<T> {
    Map<String, T> xuat();      // Xuất danh sách
    boolean them(T obj);         // Thêm mới
    boolean sua(T obj);          // Sửa
    boolean xoa(String id);      // Xóa
}
```

**Được implement bởi:**
- `ArtistService.Danhsachnghesi`
- `PerformanceService.Danhsachtietmuc`
- `employeeService.Danhsachnhanvien`
- `teamService.DanhsachDoi`
- `locationService.Danhsachdiadiem`
- `ScheduleService.DanhsachLichtrinh`
- `Event_Information.DanhsachThongtinSukien`

---

### 4. **database/** - Cơ sở dữ liệu

#### **Format dữ liệu:**

**Artist.txt** - Nghệ sĩ
```
Format: ID|Tên|Vai trò|Công ty|Giá thành|Danh sách ID tiết mục (phân cách bởi dấu phẩy)
Ví dụ: A001|Sơn Tùng MTP|ca sĩ|MTP Entertainment|300000000|P002,P024,P046
```

**employee.txt** - Nhân viên
```
Format: ID|Tên|Vai trò||Ca làm việc|ID đội
Ví dụ: E001|Nguyễn Tấn Phát|giám đốc|sáng|T001
```

**Event_Information.txt** - Sự kiện
```
Format: ID|Tên sự kiện|Ngày tổ chức|ID lịch trình|ID địa điểm|ID đội phụ trách
Ví dụ: EV001|Anh trai say hi|29/8/2026|SCH001|VN001|T001
```

**location.txt** - Địa điểm
```
Format: ID|Tên địa điểm|Sức chứa
Ví dụ: VN001|Sân vận động Mỹ Đình|40000
```

**Performance.txt** - Tiết mục
```
Format: ID|Tên tiết mục|Thời lượng (phút)
Ví dụ: P001|Chúng ta của hiện tại|5
```

**Schedule.txt** - Lịch trình
```
Format: ID|Danh sách ID tiết mục (phân cách bởi dấu phẩy)
Ví dụ: SCH001|P001,P002,0,P003,P004
Lưu ý: ID "0" đại diện cho khoảng nghỉ
```

**team.txt** - Đội ngũ
```
Format: ID đội|ID đội trưởng|Danh sách ID nhân viên (phân cách bởi dấu phẩy)
Ví dụ: T001|E001|E001,E011,E012,E013,E014,E015
```

---

### 5. **services/** - Cáclớp xử lý logic

#### **5.1. ArtistService.java**

**Class nghesi** (Dòng 13-73)
- Kế thừa: `abstraction.Nguoi`
- Thuộc tính: `id`, `ten`, `vaitro`, `congty`, `giathanh`, `idtietmuc`

**Class Danhsachnghesi** (Dòng 75-220)
- Implement: `IGeneralService<nghesi>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `loadnghesi()` | 78-110 | Đọc dữ liệu từ Artist.txt | - |
| `xuat()` | 112-121 | Trả về Map<String, nghesi> | - |
| `them()` | 141-156 | Thêm nghệ sĩ mới | ❌ Không được trùng ID hoặc tên (Dòng 146) |
| `xoa()` | 157-173 | Xóa nghệ sĩ theo ID hoặc tên | - |
| `sua()` | 177-195 | Sửa thông tin nghệ sĩ | ✅ Phải tồn tại ID (Dòng 184) |

---

#### **5.2. PerformanceService.java**

**Class tietmuc** (Dòng 10-68)
- Thuộc tính: `idtietmuc`, `tentietmuc`, `thoiluong`

**Class Danhsachtietmuc** (Dòng 71-210)
- Implement: `IGeneralService<tietmuc>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `loadtietmuc()` | 74-107 | Đọc dữ liệu từ Performance.txt | - |
| `xuat()` | 109-118 | Trả về Map<String, tietmuc> | - |
| `them()` | 120-139 | Thêm tiết mục mới | ❌ Không được trùng ID hoặc tên (Dòng 125-126) |
| `xoa()` | 140-155 | Xóa tiết mục | - |
| `sua()` | 158-177 | Sửa thông tin tiết mục | ✅ Phải tồn tại ID (Dòng 166) |
| `timIdNgheSiTheoTietMuc()` | 179-189 | Tìm nghệ sĩ biểu diễn tiết mục | - |

---

#### **5.3. employeeService.java**

**Class nhanvien** (Dòng 10-68)
- Kế thừa: `abstraction.Nguoi`
- Thuộc tính: `id`, `ten`, `vaitro`, `calamviec`, `iddoi`

**Class Danhsachnhanvien** (Dòng 71-165)
- Implement: `IGeneralService<nhanvien>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `loadnv()` | 75-98 | Đọc dữ liệu từ employee.txt | - |
| `xuat()` | 101-109 | Trả về Map<String, nhanvien> | - |
| `them()` | 120-131 | Thêm nhân viên mới | ❌ Không được trùng ID (Dòng 124) |
| `xoa()` | 151-163 | Xóa nhân viên | - |
| `sua()` | 134-148 | Sửa thông tin nhân viên | ✅ Phải tồn tại ID (Dòng 138) |

---

#### **5.4. teamService.java**

**Class team** (Dòng 8-82)
- Thuộc tính: `iddoi`, `idleader`, `songuoi`, `ds_id` (danh sách ID nhân viên)

**Class DanhsachDoi** (Dòng 84-195)
- Implement: `IGeneralService<team>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `dsteam()` | 88-120 | Đọc dữ liệu từ team.txt | - |
| `xuat()` | 122-131 | Trả về Map<String, team> | - |
| `them()` | 146-160 | Thêm đội mới | ❌ Không được trùng ID đội (Dòng 151) |
| `xoa()` | 177-191 | Xóa đội | - |
| `sua()` | 161-176 | Sửa thông tin đội | ✅ Phải tồn tại ID (Dòng 166) |

---

#### **5.5. locationService.java**

**Class location** (Dòng 6-52)
- Thuộc tính: `tendiadiem`, `succhua`, `iddiadiem`

**Class Danhsachdiadiem** (Dòng 53-165)
- Implement: `IGeneralService<location>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `loaddiadiem()` | 56-81 | Đọc dữ liệu từ location.txt | - |
| `xuat()` | 82-91 | Trả về Map<String, location> | - |
| `them()` | 104-119 | Thêm địa điểm mới | ❌ Không được trùng tên địa điểm (Dòng 109) |
| `xoa()` | 138-152 | Xóa địa điểm | - |
| `sua()` | 121-137 | Sửa thông tin địa điểm | ✅ Phải tồn tại ID (Dòng 127) |

---

#### **5.6. ScheduleService.java**

**Class Schedule** (Dòng 11-30)
- Thuộc tính: `id_lichtrinh`, `id_tietmuc` (List<String>)

**Class DanhsachLichtrinh** (Dòng 32-165)
- Implement: `IGeneralService<Schedule>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `loadtufile()` | 35-70 | Đọc dữ liệu từ Schedule.txt | - |
| `xuat()` | 155-163 | Trả về Map<String, Schedule> | - |
| `them()` | 72-87 | Thêm lịch trình mới | - |
| `xoa()` | 119-153 | Xóa lịch trình | - |
| `sua()` | 89-117 | Sửa lịch trình | ✅ Phải tồn tại ID (Dòng 95) |

---

#### **5.7. Event_Information.java**

**Class thongtin_sukien** (Dòng 11-70)
- Thuộc tính: `id_sk`, `ten_sk`, `ngaytochuc_sk`, `id_lichtrinh`, `id_diadiem`, `id_doi_phutrach`

**Class DanhsachThongtinSukien** (Dòng 72-250)
- Implement: `IGeneralService<thongtin_sukien>`

**Các phương thức quan trọng:**

| Phương thức | Dòng | Chức năng | Ràng buộc |
|------------|------|-----------|-----------|
| `loadtufile()` | 75-115 | Đọc dữ liệu từ Event_Information.txt | - |
| `xuat()` | 117-126 | Trả về Map<String, thongtin_sukien> | - |
| `them()` | 164-180 | Thêm sự kiện mới | - |
| `xoa()` | 218-232 | Xóa sự kiện | - |
| `sua()` | 182-216 | Sửa thông tin sự kiện | ✅ Phải tồn tại ID (Dòng 188) |

---

#### **5.8. MainFunction.java** - File quan trọng nhất

**Chức năng chính:**
1. Tạo khung hiển thị thông tin (method `taoKhung()`)
2. Xử lý thêm/xóa/sửa dữ liệu
3. Tạo dialog thêm mới cho tất cả các module

**Các phương thức quan trọng:**

##### **taoKhung()** (Dòng 25-390)
Tạo JPanel hiển thị thông tin theo chế độ:
- `chedo = 0`: Thông tin sự kiện
- `chedo = 1`: Thông tin nghệ sĩ
- `chedo = 2`: Thông tin tiết mục
- `chedo = 3`: Thông tin nhân viên
- `chedo = 4`: Thông tin địa điểm
- `chedo = 5`: Thông tin lịch trình
- `chedo = 6`: Thông tin đội ngũ

##### **deleter()** (Dòng 394-445)
Xóa dữ liệu theo chế độ:
- Nhận vào: `List<String> idtruyen`, `int chedo`
- Gọi phương thức `xoa()` tương ứng của từnglớp
- Hiển thị kết quả xóa

##### **configurer()** (Dòng 448-665)
Sửa dữ liệu hàng loạt:
- Nhận vào: `JPanel paneltong`, `int chedo`
- Lấy dữ liệu từ các component trong panel
- Gọi phương thức `sua()` tương ứng
- Trả về Map<String, Integer> chứa kết quả (1: thành công, 0: lỗi, 102/202/303/402: lỗi cụ thể)

##### **createAddDialog()** (Dòng 668-1015) - ⭐ QUAN TRỌNG
Tạo dialog thêm mới dữ liệu cho tất cả các module.

**Cấu trúc:**
```java
public static void createAddDialog(JFrame parent, int chedo)
```

**Các chế độ:**
- `chedo = 1`: Thêm nghệ sĩ
- `chedo = 2`: Thêm tiết mục
- `chedo = 3`: Thêm nhân viên
- `chedo = 4`: Thêm địa điểm
- `chedo = 6`: Thêm đội ngũ

**Logic xử lý nút Lưu (Dòng 729-1007):**

**Chế độ 1 - Thêm Nghệ sĩ (Dòng 734-797):**
```
1. Lấy dữ liệu từ form (Dòng 736-742)
2. Parse danh sách tiết mục (Dòng 746-749)
3. Kiểm tra trùng ID (Dòng 752-762)
4. Kiểm tra dữ liệu rỗng (Dòng 765-769)
5. Validate giá thành là số (Dòng 772-780)
6. Gọi ArtistService.them() (Dòng 783-791)
7. Hiển thị thông báo (Dòng 1000)
8. Đóng dialog nếu thành công (Dòng 1003-1005)
```

**Chế độ 2 - Thêm Tiết mục (Dòng 799-849):**
```
1. Lấy dữ liệu từ form (Dòng 801-807)
2. Kiểm tra trùng ID (Dòng 810-820)
3. Kiểm tra dữ liệu rỗng (Dòng 823-827)
4. Validate thời lượng là số (Dòng 830-838)
5. Gọi PerformanceService.them() (Dòng 841-849)
```

**Chế độ 3 - Thêm Nhân viên (Dòng 851-894):**
```
1. Lấy dữ liệu từ form (Dòng 853-861)
2. Kiểm tra trùng ID (Dòng 864-874)
3. Kiểm tra dữ liệu rỗng (Dòng 877-881)
4. Gọi employeeService.them() (Dòng 884-892)
```

**Chế độ 4 - Thêm Địa điểm (Dòng 896-946):**
```
1. Lấy dữ liệu từ form (Dòng 898-904)
2. Kiểm tra trùng ID (Dòng 907-917)
3. Kiểm tra dữ liệu rỗng (Dòng 920-924)
4. Validate sức chứa là số (Dòng 927-935)
5. Gọi locationService.them() (Dòng 938-946)
```

**Chế độ 6 - Thêm Đội (Dòng 948-998):**
```
1. Lấy dữ liệu từ form (Dòng 950-955)
2. Kiểm tra dữ liệu rỗng TRƯỚC (Dòng 958-962)
3. Kiểm tra trùng ID (Dòng 965-976)
4. Parse danh sách nhân viên (Dòng 980-987)
5. Gọi teamService.them() (Dòng 989-997)
```

**⚠️ Lưu ý quan trọng:**
- Dialog chỉ đóng khi `checked = true` (thêm thành công) - Dòng 1003-1005
- Nếu có lỗi, dialog vẫn mở để người dùng sửa lại

---

##### **Function_Dialog Class** (Dòng 1025-2150)
Dialog xem và sửa lịch trình/sự kiện chi tiết.

**Các chế độ:**
- `chedo = 1`: Xem thông tin lịch trình
- `chedo = 2`: Sửa lịch trình
- `chedo = 3`: Thêm lịch trình mới
- `chedo = 4`: Xem thông tin sự kiện
- `chedo = 5`: Sửa thông tin sự kiện
- `chedo = 6`: Thêm sự kiện mới

**Các phương thức hỗ trợ:**
- `index_swaper()` (Dòng 2095-2108): Đổi vị trí tiết mục trong lịch trình
- `index_deleter()` (Dòng 2110-2128): Xóa tiết mục khỏi lịch trình
- `refresh()` (Dòng 2130-2139): Làm mới giao diện

---

### 6. **function/** - Các giao diện quản lý

Tất cả các file trong thư mục này đều có cấu trúc tương tự:

#### **Cấu trúc chung:**
```java
public class [Module]_manage {
    public static class [Module]Dialog extends JDialog {
        // Constructor
        public [Module]Dialog(JFrame parent)
        
        // Phương thức xóa
        protected static void goixoa[Module](Map<String,JPanel> dulieutruyen)
        
        // Phương thức làm mới
        protected void refresh(...)
        
        // Phương thức lấy thông tin
        protected static String get_add_information(...)
        protected String get_configure_information(...)
        
        // Phương thức sửa
        protected void configWindow(...)
        protected void goisua[Module](...)
    }
}
```

#### **6.1. artist_manage.java**
**Class:** `ArtistDialog`

**Các nút chức năng:**
- **Làm mới** (Dòng 155-161): Reload danh sách nghệ sĩ
- **Thêm** (Dòng 172-176): Gọi `MainFunction.createAddDialog(parent, 1)`
- **Xóa** (Dòng 145-149): Gọi `goixoanghesi()` → `MainFunction.deleter(id_can_xoa, 1)`
- **Sửa** (Dòng 163-167): Mở `configWindow()` để sửa thông tin

**Phương thức quan trọng:**
- `goixoanghesi()` (Dòng 189-209): Lấy danh sách ID được chọn và xóa
- `refresh()` (Dòng 211-241): Làm mới danh sách hiển thị
- `configWindow()` (Dòng 276-380): Tạo dialog sửa thông tin nghệ sĩ
  - Nút "Lưu thay đổi" (Dòng 372): Gọi `MainFunction.configurer(Container, 1)`

---

#### **6.2. performance_manage.java**
**Class:** `PerformanceDialog`

**Các nút chức năng:**
- **Làm mới** (Dòng 152-158): Reload danh sách tiết mục
- **Thêm** (Dòng 169-173): Gọi `MainFunction.createAddDialog(parent, 2)`
- **Xóa** (Dòng 142-146): Gọi `goixoatietmuc()` → `MainFunction.deleter(id_can_xoa, 2)`
- **Sửa** (Dòng 160-164): Mở `configWindow()` để sửa thông tin

**Phương thức quan trọng:**
- `goixoatietmuc()` (Dòng 186-206): Lấy danh sách ID được chọn và xóa
- `refresh()` (Dòng 208-238): Làm mới danh sách hiển thị
- `configWindow()` (Dòng 273-343): Tạo dialog sửa thông tin tiết mục
  - Nút "Lưu thay đổi" (Dòng 335): Gọi `MainFunction.configurer(Container, 2)`

---

#### **6.3. employee_manage.java**
**Class:** `EmployeeDialog`

**Các nút chức năng:**
- **Làm mới** (Dòng 177-183): Reload danh sách nhân viên
- **Thêm** (Dòng 194-198): Gọi `MainFunction.createAddDialog(parent, 3)`
- **Xóa** (Dòng 167-171): Gọi `goixoanhanvien()` → `MainFunction.deleter(id_can_xoa, 3)`
- **Sửa** (Dòng 185-189): Mở `configWindow()` để sửa thông tin

**Phương thức quan trọng:**
- `goixoanhanvien()` (Dòng 214-234): Lấy danh sách ID được chọn và xóa
- `refresh()` (Dòng 236-266): Làm mới danh sách hiển thị
- `configWindow()` (Dòng 301-425): Tạo dialog sửa thông tin nhân viên
  - Nút "Lưu thay đổi" (Dòng 397): Gọi `MainFunction.configurer(Container, 3)`
  - Xử lý lỗi 303: Chưa nhập vai trò (Dòng 403-407)

---

#### **6.4. location_manage.java**
**Class:** `LocationDialog`

**Các nút chức năng:**
- **Làm mới** (Dòng 149-155): Reload danh sách địa điểm
- **Thêm** (Dòng 166-170): Gọi `MainFunction.createAddDialog(parent, 4)`
- **Xóa** (Dòng 139-143): Gọi `goixoadiadiem()` → `MainFunction.deleter(id_can_xoa, 4)`
- **Sửa** (Dòng 157-161): Mở `configWindow()` để sửa thông tin

**Phương thức quan trọng:**
- `goixoadiadiem()` (Dòng 186-206): Lấy danh sách ID được chọn và xóa
- `refresh()` (Dòng 208-238): Làm mới danh sách hiển thị
- `configWindow()` (Dòng 273-357): Tạo dialog sửa thông tin địa điểm
  - Nút "Lưu thay đổi" (Dòng 329): Gọi `MainFunction.configurer(Container, 4)`
  - Xử lý lỗi 402: Sức chứa không phải số (Dòng 335-339)

---

#### **6.5. team_manage.java**
**Class:** `TeamDialog`

**Các nút chức năng:**
- **Làm mới** (Dòng 149-155): Reload danh sách đội
- **Thêm** (Dòng 166-170): Gọi `MainFunction.createAddDialog(parent, 6)`
- **Xóa** (Dòng 139-143): Gọi `goixoadoi()` → `MainFunction.deleter(id_can_xoa, 1)`
- **Sửa** (Dòng 157-161): Mở `configWindow()` để sửa thông tin

**Phương thức quan trọng:**
- `goixoadoi()` (Dòng 186-206): Lấy danh sách ID được chọn và xóa
- `refresh()` (Dòng 208-238): Làm mới danh sách hiển thị
- `configWindow()` (Dòng 273-343): Tạo dialog sửa thông tin đội
  - Nút "Lưu thay đổi" (Dòng 335): Gọi `MainFunction.configurer(Container, 4)`
---

#### **6.6. schedule_manage.java**
**Class:** `ScheduleDialog`

**Đặc biệt:** Không có nút Thêm và Sửa trực tiếp, chỉ có:
- **Làm mới** (Dòng 88-94): Reload danh sách lịch trình
- **Thêm** (Dòng 105-109): Gọi `MainFunction.function.Function_Dialog(null, null, 3)`
- **Xóa** (Dòng 78-82): Gọi `goixoalichtrinh()` → `MainFunction.deleter(id_can_xoa, 5)`

**Lưu ý:** Mỗi lịch trình có 2 nút:
- **Xem thông tin chi tiết lịch trình**: Mở `Function_Dialog` với `chedo = 1`
- **Sửa lịch trình**: Mở `Function_Dialog` với `chedo = 2`

---

## 🔒 RÀNG BUỘC DỮ LIỆU CƠ BẢN

Các quy tắc cốt lõi khi thêm và sửa dữ liệu trong hệ thống, tập trung vào tính duy nhất và tính hợp lệ.

---

### ➕ 1. RÀNG BUỘC KHI THÊM (Tạo mới)

* **Tính Duy Nhất (Không trùng lặp):**
    * **ID** (Mã định danh) **không được trùng** đối với tất cả các Module (Nghệ sĩ, Tiết mục, Nhân viên, Địa điểm, Đội).
    * **Tên** của Nghệ sĩ, Tiết mục và Địa điểm **không được trùng**.
* **Tính Đầy Đủ (Bắt buộc nhập):**
    * **Phải nhập đủ thông tin** cần thiết (Không được để trống) cho tất cả các Module.
* **Tính Hợp Lệ (Định dạng):**
    * Các trường như **Giá thành**, **Thời lượng** và **Sức chứa** (trong các Module tương ứng) **phải là giá trị số** hợp lệ.

---

### ✏️ 2. RÀNG BUỘC KHI SỬA (Cập nhật)

* **Tính Tồn Tại:**
    * **ID của bản ghi cần sửa phải tồn tại** trong hệ thống (Áp dụng cho tất cả các Module, bao gồm cả Lịch trình và Sự kiện).
* **Tính Hợp Lệ:**
    * Các trường số (Giá thành, Thời lượng, Sức chứa) **phải là giá trị số** hợp lệ khi cập nhật.
    * Đối với Nhân viên, **phải nhập Vai trò** khi cập nhật.

---

### 🗑️ 3. RÀNG BUỘC KHI XÓA

* **Không có ràng buộc:** Hệ thống cho phép **xóa bất kỳ bản ghi nào** trong tất cả các Module mà không cần kiểm tra ràng buộc khóa ngoại.
---

## 📖 HƯỚNG DẪN SỬ DỤNG

### **1. Khởi chạy ứng dụng**

```bash
# Biên dịch
javac do_an_nhom_2.java

# Chạy
java do_an_nhom_2
```

### **2. Giao diện chính**

Khi khởi động, bạn sẽ thấy 2 tab:

#### **Tab "Sự kiện"**
- Hiển thị danh sách tất cả các sự kiện
- Mỗi sự kiện hiển thị:
  - ID sự kiện, Tên sự kiện
  - Ngày tổ chức
  - Danh sách tiết mục
  - Thông tin địa điểm (tên, sức chứa)
  - ID đội phụ trách, ID trưởng nhóm
  - 2 nút: "Xem thông tin chi tiết lịch trình" và "Sửa lịch trình"

**Các nút chức năng:**
- **Làm mới**: Reload danh sách sự kiện
- **Thêm sự kiện**: Mở dialog thêm sự kiện mới
- **Xóa**: Xóa các sự kiện đã chọn (checkbox)

#### **Tab "Cơ sở dữ liệu"**
Hiển thị 6 nút truy cập các module quản lý:
1. **Dữ liệu ca / nghệ sĩ**
2. **Dữ liệu các tiết mục đã đăng ký**
3. **Dữ liệu nhân viên**
4. **Dữ liệu đội ngũ quản lý sự kiện**
5. **Dữ liệu địa điểm tổ chức sự kiện**
6. **Dữ liệu lịch trình cài đặt sẵn**

---

### **3. Quản lý Nghệ sĩ**

**Bước 1:** Click nút "Dữ liệu ca / nghệ sĩ"

**Bước 2:** Cửa sổ quản lý nghệ sĩ hiển thị:
- Danh sách tất cả nghệ sĩ với thông tin:
  - ID nghệ sĩ, Tên, Vai trò
  - Công ty quản lý
  - Giá thành 1 lần diễn
  - ID các tiết mục có thể biểu diễn
- Checkbox để chọn nghệ sĩ

**Thêm nghệ sĩ mới:**
1. Click nút "Thêm"
2. Nhập thông tin:
   - ID nghệ sĩ (không được trùng)
   - Tên nghệ sĩ
   - Vai trò (ca sĩ, rapper, dancer...)
   - Công ty
   - Giá thành (phải là số)
   - ID tiết mục (cách nhau bởi dấu phẩy, ví dụ: P001,P002,P003)
3. Click "Lưu"
   - Nếu thành công: Hiển thị "Thêm thành công" và đóng dialog
   - Nếu lỗi: Hiển thị thông báo lỗi, dialog vẫn mở để sửa

**Sửa nghệ sĩ:**
1. Chọn checkbox của nghệ sĩ cần sửa
2. Click nút "Sửa"
3. Sửa thông tin trong dialog
4. Click "Lưu thay đổi"

**Xóa nghệ sĩ:**
1. Chọn checkbox của nghệ sĩ cần xóa
2. Click nút "Xóa"
3. Xác nhận trong dialog thông báo

---

### **4. Quản lý Tiết mục**

**Bước 1:** Click nút "Dữ liệu các tiết mục đã đăng ký"

**Bước 2:** Cửa sổ quản lý tiết mục hiển thị:
- Danh sách tất cả tiết mục với thông tin:
  - ID tiết mục, Tên tiết mục
  - Thời lượng (phút)

**Thêm tiết mục mới:**
1. Click nút "Thêm"
2. Nhập thông tin:
   - ID tiết mục (không được trùng)
   - Tên tiết mục
   - Thời lượng (phải là số, đơn vị: phút)
3. Click "Lưu"

**Sửa/Xóa:** Tương tự như Nghệ sĩ

---

### **5. Quản lý Nhân viên**

**Bước 1:** Click nút "Dữ liệu nhân viên"

**Bước 2:** Cửa sổ quản lý nhân viên hiển thị:
- Danh sách tất cả nhân viên với thông tin:
  - ID nhân viên, Tên nhân viên
  - Vai trò nhân viên
  - Ca làm việc (sáng/chiều/tối)
  - ID đội phụ trách sự kiện

**Thêm nhân viên mới:**
1. Click nút "Thêm"
2. Nhập thông tin:
   - ID nhân viên (không được trùng)
   - Tên nhân viên
   - Vai trò (giám đốc, quản lý, nhân viên...)
   - Ca làm việc (chọn từ dropdown: sáng/chiều/tối)
   - ID đội (chọn từ dropdown)
3. Click "Lưu"

**Sửa/Xóa:** Tương tự như Nghệ sĩ

---

### **6. Quản lý Đội ngũ**

**Bước 1:** Click nút "Dữ liệu đội ngũ quản lý sự kiện"

**Bước 2:** Cửa sổ quản lý đội hiển thị:
- Danh sách tất cả đội với thông tin:
  - ID đội
  - ID đội trưởng, Tên đội trưởng
  - Danh sách ID của đội viên

**Thêm đội mới:**
1. Click nút "Thêm"
2. Nhập thông tin:
   - ID đội (không được trùng)
   - ID đội trưởng
   - ID nhân viên (cách nhau bởi dấu phẩy, ví dụ: E001,E002,E003)
3. Click "Lưu"

**Sửa/Xóa:** Tương tự như Nghệ sĩ

---

### **7. Quản lý Địa điểm**

**Bước 1:** Click nút "Dữ liệu địa điểm tổ chức sự kiện"

**Bước 2:** Cửa sổ quản lý địa điểm hiển thị:
- Danh sách tất cả địa điểm với thông tin:
  - ID địa điểm
  - Tên địa điểm
  - Sức chứa (số người)

**Thêm địa điểm mới:**
1. Click nút "Thêm"
2. Nhập thông tin:
   - ID địa điểm (không được trùng)
   - Tên địa điểm
   - Sức chứa (phải là số)
3. Click "Lưu"

**Sửa/Xóa:** Tương tự như Nghệ sĩ

---

### **8. Quản lý Lịch trình**

**Bước 1:** Click nút "Dữ liệu lịch trình cài đặt sẵn"

**Bước 2:** Cửa sổ quản lý lịch trình hiển thị:
- Danh sách tất cả lịch trình với thông tin:
  - ID lịch trình
  - Danh sách ID tiết mục trong lịch trình
  - 2 nút: "Xem thông tin chi tiết lịch trình" và "Sửa lịch trình"

**Xem chi tiết lịch trình:**
1. Click nút "Xem thông tin chi tiết lịch trình"
2. Dialog hiển thị:
   - ID lịch trình
   - Danh sách các tiết mục với thông tin chi tiết:
     - ID tiết mục, Tên tiết mục
     - Thời lượng
     - Nghệ sĩ biểu diễn
   - Khoảng nghỉ (nếu có)

**Thêm lịch trình mới:**
1. Click nút "Thêm"
2. Nhập ID lịch trình
3. Chọn tiết mục từ dropdown và click "➕ Thêm tiết mục"
4. Sắp xếp thứ tự tiết mục bằng nút "⬆️ Lên" và "⬇️ Xuống"
5. Xóa tiết mục bằng nút "🚮 Xóa"
6. Click "Lưu"

**Sửa lịch trình:**
1. Click nút "Sửa lịch trình" trên lịch trình cần sửa
2. Thêm/xóa/sắp xếp tiết mục
3. Click "Lưu"

**Xóa lịch trình:**
1. Chọn checkbox của lịch trình cần xóa
2. Click nút "Xóa"

---

### **9. Quản lý Sự kiện**

**Thêm sự kiện mới:**
1. Ở tab "Sự kiện", click nút "Thêm sự kiện"
2. Nhập thông tin:
   - ID sự kiện
   - Tên sự kiện
   - Chọn lịch trình (từ dropdown)
   - Chọn ngày tổ chức (từ date picker)
   - Chọn địa điểm (từ dropdown)
   - Chọn đội phụ trách (từ dropdown)
3. Xem trước thông tin:
   - Thông tin địa điểm (tên, sức chứa)
   - Danh sách nhân viên trong đội
4. Click "Lưu"

**Xem chi tiết sự kiện:**
1. Click nút "Xem thông tin chi tiết lịch trình" trên sự kiện
2. Dialog hiển thị:
   - Thông tin sự kiện đầy đủ
   - Lịch trình chi tiết với các tiết mục
   - Thông tin địa điểm
   - Thông tin đội phụ trách

**Sửa sự kiện:**
1. Click nút "Sửa lịch trình" trên sự kiện cần sửa
2. Sửa thông tin:
   - Tên sự kiện
   - Chọn lịch trình khác
   - Đổi ngày tổ chức
   - Đổi địa điểm
   - Đổi đội phụ trách
3. Click "Lưu"

**Xóa sự kiện:**
1. Chọn checkbox của sự kiện cần xóa
2. Click nút "Xóa" ở thanh công cụ phía dưới


---
