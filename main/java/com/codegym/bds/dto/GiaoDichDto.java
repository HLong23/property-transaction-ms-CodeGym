package com.codegym.bds.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class GiaoDichDto {

    @NotBlank(message = "Mã giao dịch không được để trống")
    @Pattern(regexp = "^MGD-\\d{4}$", message = "Mã giao dịch phải đúng định dạng MGD-XXXX (X là chữ số 0-9)")
    private String maGiaoDich;

    @NotNull(message = "Vui lòng chọn khách hàng")
    private Integer khachHangId;

    @NotBlank(message = "Vui lòng chọn loại dịch vụ")
    private String loaiDichVu;

    @NotNull(message = "Ngày giao dịch không được để trống")
    @Future(message = "Ngày giao dịch phải lớn hơn ngày hiện tại")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate ngayGiaoDich;

    @NotNull(message = "Đơn giá không được để trống")
    @DecimalMin(value = "500000", inclusive = false, message = "Đơn giá phải lớn hơn 500.000 VND")
    private Double donGia;

    @NotNull(message = "Diện tích không được để trống")
    @DecimalMin(value = "20", inclusive = false, message = "Diện tích phải lớn hơn 20 m²")
    private Double dienTich;

    public GiaoDichDto() {
    }

    public String getMaGiaoDich() {
        return maGiaoDich;
    }

    public void setMaGiaoDich(String maGiaoDich) {
        this.maGiaoDich = maGiaoDich;
    }

    public Integer getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(Integer khachHangId) {
        this.khachHangId = khachHangId;
    }

    public String getLoaiDichVu() {
        return loaiDichVu;
    }

    public void setLoaiDichVu(String loaiDichVu) {
        this.loaiDichVu = loaiDichVu;
    }

    public LocalDate getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public void setNgayGiaoDich(LocalDate ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }
}
