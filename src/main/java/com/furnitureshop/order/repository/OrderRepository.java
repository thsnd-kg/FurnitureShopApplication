package com.furnitureshop.order.repository;

import com.furnitureshop.order.entity.Order;
import com.furnitureshop.order.entity.OrderStatus;
import com.furnitureshop.user.entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByIsDeletedFalse();
    List<Order> findAllByOrderStatusAndIsDeletedFalse(OrderStatus orderStatus);
    List<Order> findByUser(User user);
    List<Order> findByCreatedAtBetweenOrderByCreatedAt(LocalDateTime createdAt, LocalDateTime createdAt2);
}
