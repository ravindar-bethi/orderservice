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

	@PostMapping("/save")
	public Order placeOrder(@RequestBody Order order) {
		return orderService.placeOrder(order);
	}

	@GetMapping("/{orderId}")
	public Order getOrder(@PathVariable Long orderId) {
		return orderService.getOrderById(orderId);
	}

	@GetMapping("/user/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable Long userId) throws InterruptedException {
		System.out.println("Order-Controller:"+userId);
		if(Math.random() > 0.5) {
			throw new RuntimeException("Temporary Failure! Try Again.");
		}
		//Thread.sleep(15000);//Simulating 1 15 seconds delay
		return orderService.getOrdersByUserId(userId);
	}

	@PutMapping("/{orderId}/cancel")
	public Order cancelOrder(@PathVariable Long orderId) {
		return orderService.cancelOrder(orderId);
	}

}
