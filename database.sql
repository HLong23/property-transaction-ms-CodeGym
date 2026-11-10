-- Chay file nay neu muon tao san du lieu
CREATE DATABASE IF NOT EXISTS bds_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE bds_db;

CREATE TABLE IF NOT EXISTS khach_hang (
    ma_khach_hang INT AUTO_INCREMENT PRIMARY KEY,
    ten_khach_hang VARCHAR(100) NOT NULL,
    so_dien_thoai VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS giao_dich (
    ma_giao_dich VARCHAR(10) PRIMARY KEY,
    ma_khach_hang INT NOT NULL,
    ngay_giao_dich DATE NOT NULL,
    loai_dich_vu VARCHAR(20) NOT NULL,
    don_gia DOUBLE NOT NULL,
    dien_tich DOUBLE NOT NULL,
    CONSTRAINT fk_giao_dich_khach_hang FOREIGN KEY (ma_khach_hang) REFERENCES khach_hang (ma_khach_hang)
);

INSERT INTO khach_hang (ten_khach_hang, so_dien_thoai, email) VALUES
    ('Nguyễn Văn A', '0905973155', 'nguyenvana@gmail.com'),
    ('Nguyễn Văn B', '0912345678', 'nguyenvanb@gmail.com'),
    ('Nguyễn Văn C', '0987654321', 'nguyenvanc@gmail.com');

INSERT INTO giao_dich (ma_giao_dich, ma_khach_hang, ngay_giao_dich, loai_dich_vu, don_gia, dien_tich) VALUES
    ('MGD-0001', 1, '2020-12-12', 'Nhà', 2000000, 100),
    ('MGD-0002', 2, '2020-12-12', 'Đất', 3000000, 200),
    ('MGD-0003', 3, '2020-12-12', 'Nhà', 5000000, 100);
