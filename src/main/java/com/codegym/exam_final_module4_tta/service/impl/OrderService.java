package com.codegym.exam_final_module4_tta.service.impl;

import com.codegym.exam_final_module4_tta.model.Orders;
import com.codegym.exam_final_module4_tta.repository.IOrderRepository;
import com.codegym.exam_final_module4_tta.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
    public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Page<Orders> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return orderRepository.findByOrderDateBetween(startDate, endDate, pageable);
    }

    @Override
    public Optional<Object> findById(Long id) {
        return Optional.of(orderRepository.findById(id));
    }

    @Override
    public Orders save(Orders order) {
        return orderRepository.save(order);
    }


//    @Override
//    public Page<Orders> findTopOrders(int top, Pageable pageable) {
//        Pageable topPageable = PageRequest.of(0, top, pageable.getSort());
//        return orderRepository.findTopOrders(topPageable);
//    }
}
