package com.furnitureshop.order.repository;

import com.furnitureshop.order.entity.OrderDetail;
import com.furnitureshop.order.entity.OrderDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPK> {
    List<OrderDetail> getById_OrderId(Long orderId);
}
