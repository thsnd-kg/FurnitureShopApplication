package com.furnitureshop.order.controller;

import com.furnitureshop.order.entity.Voucher;
import com.furnitureshop.order.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Voucher> getVouchers() {
        return service.getVouchers();
    }

    @GetMapping("/id")
    public Voucher getVoucher(@RequestParam Long voucherId) {
        return service.getVoucherById(voucherId);
    }
}
