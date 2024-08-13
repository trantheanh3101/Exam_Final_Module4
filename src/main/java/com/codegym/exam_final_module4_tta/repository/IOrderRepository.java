package com.codegym.exam_final_module4_tta.repository;

import com.codegym.exam_final_module4_tta.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Page<Orders> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

//    @Query("SELECT o FROM Orders o ORDER BY o.totalPrice DESC")
//    Page<Orders> findTopOrders(Pageable pageable);
//
}
