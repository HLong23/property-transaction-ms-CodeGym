package com.codegym.bds.repository;

import com.codegym.bds.entity.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {

    /* Tim kiem theo ten khach hang (tuong doi) va/hoac loai dich vu. */
    @Query("select g from GiaoDich g join fetch g.khachHang k "
            + "where (:ten = '' or lower(k.tenKhachHang) like lower(concat('%', :ten, '%'))) "
            + "and (:loai = '' or g.loaiDichVu = :loai) "
            + "order by g.maGiaoDich")
    List<GiaoDich> search(@Param("ten") String ten, @Param("loai") String loai);

    @Query("select g from GiaoDich g join fetch g.khachHang where g.maGiaoDich = :ma")
    Optional<GiaoDich> findByIdWithKhachHang(@Param("ma") String ma);
}
