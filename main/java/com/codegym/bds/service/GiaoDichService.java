package com.codegym.bds.service;

import com.codegym.bds.dto.GiaoDichDto;
import com.codegym.bds.entity.GiaoDich;
import com.codegym.bds.entity.KhachHang;
import com.codegym.bds.repository.GiaoDichRepository;
import com.codegym.bds.repository.KhachHangRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GiaoDichService {

    private final GiaoDichRepository giaoDichRepository;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichService(GiaoDichRepository giaoDichRepository, KhachHangRepository khachHangRepository) {
        this.giaoDichRepository = giaoDichRepository;
        this.khachHangRepository = khachHangRepository;
    }

    @Transactional(readOnly = true)
    public List<GiaoDich> search(String ten, String loai) {
        String tenKhach = ten == null ? "" : ten.trim();
        String loaiDichVu = loai == null ? "" : loai.trim();
        return giaoDichRepository.search(tenKhach, loaiDichVu);
    }

    @Transactional(readOnly = true)
    public Optional<GiaoDich> findById(String maGiaoDich) {
        return giaoDichRepository.findByIdWithKhachHang(maGiaoDich);
    }

    public boolean existsById(String maGiaoDich) {
        return giaoDichRepository.existsById(maGiaoDich);
    }

    @Transactional
    public void save(GiaoDichDto dto) {
        KhachHang khachHang = khachHangRepository.findById(dto.getKhachHangId())
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng không tồn tại"));

        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGiaoDich(dto.getMaGiaoDich());
        giaoDich.setKhachHang(khachHang);
        giaoDich.setLoaiDichVu(dto.getLoaiDichVu());
        giaoDich.setNgayGiaoDich(dto.getNgayGiaoDich());
        giaoDich.setDonGia(dto.getDonGia());
        giaoDich.setDienTich(dto.getDienTich());
        giaoDichRepository.save(giaoDich);
    }

    @Transactional
    public void delete(String maGiaoDich) {
        giaoDichRepository.deleteById(maGiaoDich);
    }
}
