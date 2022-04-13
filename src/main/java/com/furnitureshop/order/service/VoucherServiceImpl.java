package com.furnitureshop.order.service;

import com.furnitureshop.order.entity.Voucher;
import com.furnitureshop.order.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository repository;

    @Autowired
    public VoucherServiceImpl(VoucherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Voucher> getVouchers() {
        return null;
    }
}
