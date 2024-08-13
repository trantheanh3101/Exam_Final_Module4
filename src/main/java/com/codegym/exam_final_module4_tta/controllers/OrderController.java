package com.codegym.exam_final_module4_tta.controllers;

import com.codegym.exam_final_module4_tta.model.Orders;
import com.codegym.exam_final_module4_tta.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public String showList(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("orderDate").descending());
        Page<Orders> orders = orderService.findAll(pageable);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Orders order : orders) {
            String formattedDate = order.getOrderDate().format(formatter);
            order.setFormattedDate(formattedDate);
        }

        model.addAttribute("orders", orders);
        return "list";
    }

    @GetMapping("/filter")
    public String filterOrders(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("orderDate").descending());

        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : null;

        Page<Orders> orders;
        if (start != null && end != null) {
            LocalDateTime startDateTime = start.atStartOfDay();
            LocalDateTime endDateTime = end.plusDays(1).atStartOfDay();
            orders = orderService.findByOrderDateBetween(startDateTime, endDateTime, pageable);
        } else {
            orders = orderService.findAll(pageable);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Orders order : orders) {
            String formattedDate = order.getOrderDate().format(formatter);
            order.setFormattedDate(formattedDate);
        }

        model.addAttribute("orders", orders);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "list";
    }

//    @GetMapping("/top")
//    public String viewTopOrders(
//            Model model,
//            @RequestParam(defaultValue = "1") int top,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "3") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<Orders> topOrders = orderService.findTopOrders(top, pageable);
//
//        model.addAttribute("orders", topOrders);
//        model.addAttribute("top", top);
//        return "top";
//    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Orders order = (Orders) orderService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order Id:" + id));
        model.addAttribute("order", order);
        return "editOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Orders order) {
        orderService.save(order);
        return "redirect:/order";
    }
}
