package com.furnitureshop.order.service;

import com.furnitureshop.order.entity.Voucher;

import java.util.List;

public interface VoucherService {
    Voucher getVoucherById(Long voucherId);

    List<Voucher> getVouchers();
}
