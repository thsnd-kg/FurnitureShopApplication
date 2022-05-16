package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.voucher.CreateVoucherDto;
import com.furnitureshop.order.dto.voucher.UpdateVoucherDto;
import com.furnitureshop.order.entity.Voucher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VoucherService {
    Voucher getVoucherById(Long voucherId);

    List<Voucher> getVouchers();

    List<Voucher> getVoucherActive();

    Voucher createVoucher(CreateVoucherDto dto);

    Boolean deleteVoucher(Long voucherId);

    Voucher updateVoucher(UpdateVoucherDto dto);
}
