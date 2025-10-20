# 🚀 HƯỚNG DẪN CHẠY ỨNG DỤNG

## CÁCH 1: Mở bằng Android Studio (Dễ nhất - Khuyến nghị)

### Bước 1: Cài đặt Android Studio
1. Tải Android Studio tại: https://developer.android.com/studio
2. Cài đặt Android Studio (bao gồm cả Android SDK)
3. Mở Android Studio và làm theo hướng dẫn cài đặt lần đầu

### Bước 2: Tải project về máy
```bash
# Nếu đang ở workspace, copy toàn bộ folder này về máy tính của bạn
# Hoặc clone từ git repository
```

### Bước 3: Mở project trong Android Studio
1. Mở Android Studio
2. Chọn "Open an Existing Project"
3. Chọn thư mục project (thư mục chứa file build.gradle.kts)
4. Đợi Android Studio sync Gradle (có thể mất vài phút lần đầu)

### Bước 4: Chạy ứng dụng
**CÓ 2 LỰA CHỌN:**

#### A. Chạy trên máy ảo (Emulator)
1. Trong Android Studio, click vào "Device Manager" (biểu tượng điện thoại)
2. Click "Create Device"
3. Chọn một thiết bị (ví dụ: Pixel 6)
4. Chọn System Image (ví dụ: Android 13/API 33)
5. Click "Finish"
6. Click nút ▶️ "Run" màu xanh lá
7. Chọn emulator vừa tạo

#### B. Chạy trên điện thoại thật
1. Bật "Developer Options" trên điện thoại:
   - Vào Settings → About Phone
   - Nhấn 7 lần vào "Build Number"
2. Bật "USB Debugging":
   - Vào Settings → Developer Options
   - Bật "USB Debugging"
3. Cắm điện thoại vào máy tính bằng cáp USB
4. Click nút ▶️ "Run" trong Android Studio
5. Chọn điện thoại của bạn

### Tài khoản đăng nhập:
- Username: `admin`
- Password: `123456`

---

## CÁCH 2: Chạy bằng Command Line (Nâng cao)

### Yêu cầu:
- Đã cài đặt Android SDK
- Đã cài đặt JDK 11+

### Bước 1: Cài đặt Android SDK Command Line Tools
```bash
# Download từ: https://developer.android.com/studio#command-tools
# Giải nén vào thư mục (ví dụ: ~/Android/Sdk)
```

### Bước 2: Tạo file local.properties
```bash
# Tạo file local.properties trong thư mục gốc của project
# Thêm dòng sau (thay đổi đường dẫn cho phù hợp):
sdk.dir=/home/yourusername/Android/Sdk
```

**Ví dụ đường dẫn:**
- Windows: `sdk.dir=C\:\\Users\\YourName\\AppData\\Local\\Android\\Sdk`
- macOS: `sdk.dir=/Users/YourName/Library/Android/sdk`
- Linux: `sdk.dir=/home/YourName/Android/Sdk`

### Bước 3: Build project
```bash
chmod +x gradlew
./gradlew build
```

### Bước 4: Chạy trên emulator hoặc điện thoại
```bash
# Cài đặt APK trên thiết bị đã kết nối
./gradlew installDebug

# Hoặc tìm file APK tại:
# app/build/outputs/apk/debug/app-debug.apk
# Sau đó cài đặt thủ công
```

---

## ⚠️ Xử lý lỗi thường gặp

### Lỗi: "SDK location not found"
**Giải pháp:** Tạo file `local.properties` với nội dung:
```
sdk.dir=/path/to/your/Android/Sdk
```

### Lỗi: "Gradle sync failed"
**Giải pháp:** 
1. Kiểm tra kết nối internet
2. Trong Android Studio: File → Invalidate Caches → Restart

### Lỗi: "compileSdkVersion is not specified"
**Giải pháp:** Đã được fix trong code, chạy lại sync

---

## 📱 Screenshots các bước (Mô tả)

1. **Màn hình đăng nhập**: Nhập admin/123456
2. **Màn hình chính**: Grid với 10 tính năng
3. **Click vào feature**: Hiển thị Toast thông báo

---

## 💡 Lưu ý quan trọng

- Lần đầu build có thể mất 5-10 phút để download dependencies
- Cần internet để download Gradle và dependencies
- Emulator cần ít nhất 8GB RAM để chạy mượt
- Nếu dùng điện thoại thật, đảm bảo đã bật USB Debugging

---

## 🆘 Cần hỗ trợ?

Nếu gặp vấn đề, hãy:
1. Kiểm tra Android Studio đã cài đúng chưa
2. Kiểm tra có kết nối internet
3. Xem log lỗi trong Logcat (Android Studio)
4. Google lỗi cụ thể nếu có

---

## 🎉 Kết quả mong đợi

Sau khi chạy thành công, bạn sẽ thấy:
- Màn hình đăng nhập với form đẹp mắt
- Sau khi đăng nhập, hiển thị grid 2 cột với 10 tính năng
- Click vào mỗi tính năng sẽ hiện Toast thông báo
- UI Material Design với màu sắc tươi sáng

Chúc bạn thành công! 🚀
