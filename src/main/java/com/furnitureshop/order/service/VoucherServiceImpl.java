package com.furnitureshop.order.service;

import com.furnitureshop.order.entity.Voucher;
import com.furnitureshop.order.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {
    private final VoucherRepository repository;

    @Autowired
    public VoucherServiceImpl(VoucherRepository repository) {
        this.repository = repository;
    }

    @Override
    public Voucher getVoucherById(Long voucherId) {
        Optional<Voucher> voucher = repository.findById(voucherId);

        if (!voucher.isPresent()) {
            throw new IllegalStateException("Voucher not exists");
        }

        return voucher.get();
    }

    @Override
    public Page<Voucher> getVouchers() {
        return repository.findAll(PageRequest.of(0, 5));
    }
}
