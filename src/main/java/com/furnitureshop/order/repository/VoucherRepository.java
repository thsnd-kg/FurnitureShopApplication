package com.furnitureshop.order.repository;

import com.furnitureshop.order.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
}
