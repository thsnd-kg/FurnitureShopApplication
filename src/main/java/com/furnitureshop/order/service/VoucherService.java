package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.voucher.CreateVoucherDto;
import com.furnitureshop.order.entity.Voucher;
import org.springframework.data.domain.Page;

public interface VoucherService {
    Voucher getVoucherById(Long voucherId);

    Page<Voucher> getVouchers();

    Voucher createVoucher(CreateVoucherDto dto);
}
