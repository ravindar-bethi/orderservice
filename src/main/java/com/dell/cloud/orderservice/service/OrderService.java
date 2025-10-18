package com.dell.cloud.orderservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dell.cloud.orderservice.entity.Order;
import com.dell.cloud.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order placeOrder(Order order) {
		order.setStatus("PLACED");
		return orderRepository.save(order);
	}

	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	public List<Order> getOrderByuserId(String userId) {
		return orderRepository.findByUserId(userId);
	}

	public Order cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId).orElse(null);
		if (order != null) {
			order.setStatus("CANCELLED");
			return orderRepository.save(order);
		}
		return null;
	}

}
