package com.furnitureshop.order.controller;

import com.furnitureshop.order.entity.Voucher;
import com.furnitureshop.order.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/voucher")
public class VoucherController {
    private final VoucherService service;

    @Autowired
    public VoucherController(VoucherService service) {
        this.service = service;
    }

    @GetMapping
    public Page<Voucher> getVouchers() {
        return service.getVouchers();
    }

    @GetMapping("/{voucher-id}")
    public Voucher getVoucher(@PathVariable("voucher-id") Long voucherId) {
        return service.getVoucherById(voucherId);
    }
}
