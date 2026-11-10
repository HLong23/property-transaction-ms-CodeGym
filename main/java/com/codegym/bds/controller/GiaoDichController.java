package com.codegym.bds.controller;

import com.codegym.bds.dto.GiaoDichDto;
import com.codegym.bds.entity.GiaoDich;
import com.codegym.bds.service.GiaoDichService;
import com.codegym.bds.service.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {

    private static final List<String> LOAI_DICH_VU = List.of("Đất", "Nhà");

    private final GiaoDichService giaoDichService;
    private final KhachHangService khachHangService;

    public GiaoDichController(GiaoDichService giaoDichService, KhachHangService khachHangService) {
        this.giaoDichService = giaoDichService;
        this.khachHangService = khachHangService;
    }

    @ModelAttribute("dsLoaiDichVu")
    public List<String> dsLoaiDichVu() {
        return LOAI_DICH_VU;
    }

    @GetMapping("")
    public String list(@RequestParam(defaultValue = "") String ten,
                       @RequestParam(defaultValue = "") String loai,
                       Model model) {
        model.addAttribute("dsGiaoDich", giaoDichService.search(ten, loai));
        model.addAttribute("ten", ten);
        model.addAttribute("loai", loai);
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("giaoDichDto", new GiaoDichDto());
        model.addAttribute("dsKhachHang", khachHangService.findAll());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("giaoDichDto") GiaoDichDto giaoDichDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasFieldErrors("maGiaoDich")
                && giaoDichService.existsById(giaoDichDto.getMaGiaoDich())) {
            bindingResult.rejectValue("maGiaoDich", "duplicate", "Mã giao dịch đã tồn tại");
        }
        if (!bindingResult.hasFieldErrors("loaiDichVu")
                && !LOAI_DICH_VU.contains(giaoDichDto.getLoaiDichVu())) {
            bindingResult.rejectValue("loaiDichVu", "invalid", "Loại dịch vụ không hợp lệ");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("dsKhachHang", khachHangService.findAll());
            return "create";
        }
        giaoDichService.save(giaoDichDto);
        redirectAttributes.addFlashAttribute("message", "Thêm mới giao dịch thành công!");
        return "redirect:/giao-dich";
    }

    @GetMapping("/{ma}")
    public String detail(@PathVariable("ma") String ma,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        Optional<GiaoDich> giaoDich = giaoDichService.findById(ma);
        if (giaoDich.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy giao dịch " + ma);
            return "redirect:/giao-dich";
        }
        model.addAttribute("giaoDich", giaoDich.get());
        return "detail";
    }

    @PostMapping("/{ma}/delete")
    public String delete(@PathVariable("ma") String ma, RedirectAttributes redirectAttributes) {
        if (giaoDichService.existsById(ma)) {
            giaoDichService.delete(ma);
            redirectAttributes.addFlashAttribute("message", "Xóa giao dịch thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy giao dịch " + ma);
        }
        return "redirect:/giao-dich";
    }
}
