package com.codegym.bds.service;

import com.codegym.bds.entity.KhachHang;
import com.codegym.bds.repository.KhachHangRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhachHangService {

    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    public KhachHang findById(Integer id) {
        return khachHangRepository.findById(id).orElse(null);
    }
}
