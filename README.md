# Hệ Thống Quản Lý Tiêm Chủng (Vaccination Management System)

## Mô tả
Ứng dụng Android để quản lý tiêm chủng với các tính năng:
- ✅ Đăng nhập/Đăng xuất
- ✅ Đặt lịch tiêm chủng
- ✅ Xem chứng nhận tiêm chủng
- ✅ Quét mã QR định danh
- ✅ Khảo sát phản ứng sau tiêm
- ✅ Xem thống kê tiêm chủng
- ✅ Quản lý vaccine tồn kho
- ✅ Nhận thông báo nhắc lịch
- ✅ Hủy/Đặt lại lịch hẹn
- ✅ Cập nhật trạng thái tiêm
- ✅ Quản lý tài khoản cá nhân

## Yêu cầu hệ thống
- Android Studio Arctic Fox hoặc mới hơn
- Android SDK API 36
- JDK 11 hoặc mới hơn
- Gradle 8.13

## Cài đặt

### 1. Clone repository
```bash
git clone <repository-url>
cd VaccinationManagementSystem
```

### 2. Cấu hình Android SDK
Tạo file `local.properties` trong thư mục gốc của project:
```properties
sdk.dir=/path/to/your/Android/sdk
```

Ví dụ:
- Windows: `sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk`
- macOS: `sdk.dir=/Users/YourUsername/Library/Android/sdk`
- Linux: `sdk.dir=/home/YourUsername/Android/Sdk`

### 3. Build project
```bash
./gradlew build
```

### 4. Chạy ứng dụng
Mở project trong Android Studio và nhấn **Run** hoặc dùng lệnh:
```bash
./gradlew installDebug
```

## Tài khoản đăng nhập mặc định
- **Username**: admin
- **Password**: 123456

## Cấu trúc thư mục
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/vaccinationmanagementsystem/
│   │   │   ├── LoginActivity.java          # Màn hình đăng nhập
│   │   │   ├── MainActivity.java           # Màn hình chính với grid features
│   │   │   ├── Feature.java                # Model cho tính năng
│   │   │   └── FeatureAdapter.java         # Adapter cho GridView
│   │   ├── res/
│   │   │   ├── layout/                     # Các file layout XML
│   │   │   ├── drawable/                   # Icons và hình ảnh
│   │   │   └── values/                     # Colors, strings, themes
│   │   └── AndroidManifest.xml
│   └── androidTest/                        # Test cases
└── build.gradle.kts

```

## Công nghệ sử dụng
- Java
- Android SDK
- Material Design Components
- CardView
- GridView

## Tác giả
Phát triển cho hệ thống quản lý tiêm chủng

## License
MIT License
