# PayFlow

Hệ thống ví điện tử mô phỏng, phục vụ học tập và xây dựng
portfolio Java Backend.

## Công nghệ

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Data JPA
- PostgreSQL 17 chạy bằng Docker
- Flyway
- Postman

## Phạm vi MVP

- Đăng ký và đăng nhập
- Access Token và Refresh Token
- Quản lý ví
- Nạp tiền mô phỏng
- Chuyển tiền giữa các ví
- Xem lịch sử giao dịch

## Chạy trên máy cá nhân

1. Mở Docker Desktop và khởi động container payflow-postgres.
2. Cấu hình JDK 21 cho project.
3. Cung cấp DB_PASSWORD trong cấu hình chạy.
4. Chạy PayflowApplication trong NetBeans.

Thông tin database:
- Host: localhost
- Port: 5432
- Database: payflow_db
- Username: payflow
- Password: cung cấp qua DB_PASSWORD, không lưu trong repository.

## API hiện có

| Method | Endpoint | Chức năng |
|---|---|---|
| GET | /api/v1/health | Kiểm tra ứng dụng trả lời HTTP request |

Health API đã được kiểm thử bằng Postman, trả HTTP 200
với status UP và application PayFlow.

## Database migration

- V1__create_roles.sql: tạo bảng roles và thêm USER, ADMIN.
- Migration V1 đã chạy thành công.
- Các thay đổi tiếp theo dùng migration mới; giữ nguyên V1 đã chạy.

## Tiến độ

### Phase 0 — Planning and Design

Đã có tài liệu Project Blueprint làm định hướng.

### Phase 1 — Project Setup

Đã xác nhận:
- Tạo project Spring Boot.
- Kết nối PostgreSQL Docker và pgAdmin.
- Chạy ứng dụng trong NetBeans.
- Kiểm thử Health API bằng Postman.
- Chạy migration Flyway đầu tiên.
- Đưa project lên GitHub.

Cần xác nhận:
- Cấu trúc package theo chức năng.
- Swagger UI.

### Phase 2 — User and Authentication

Chưa bắt đầu triển khai.
