package com.codegym.exam_final_module4_tta.service;

import com.codegym.exam_final_module4_tta.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IOrderService {

    Page<Orders> findAll(Pageable pageable);

    Page<Orders> findByOrderDateBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable pageable);

    Optional<Object> findById(Long id);

    Orders save(Orders order);

//    Page<Orders> findTopOrders(int top, Pageable pageable);
}
