package com.dell.cloud.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dell.cloud.orderservice.entity.Order;
import com.dell.cloud.orderservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public Order placeOrder(@RequestBody Order order) {
		return orderService.placeOrder(order);
	}

	@GetMapping("/{orderId}")
	public Order getOrder(@PathVariable Long orderID) {
		return orderService.getOrderById(orderID);
	}

	@GetMapping("/user/{userId}")
	public List<Order> getUserOrders(@PathVariable String userId) {
		return orderService.getOrderByuserId(userId);
	}

	@PutMapping("/{orderId}/cancel")
	public Order cancelOrder(@PathVariable Long orderId) {
		return orderService.cancelOrder(orderId);
	}

}
