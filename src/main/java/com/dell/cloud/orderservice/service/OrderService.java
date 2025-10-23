package com.dell.cloud.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dell.cloud.orderservice.client.ProductClient;
import com.dell.cloud.orderservice.entity.Order;
import com.dell.cloud.orderservice.entity.Product;
import com.dell.cloud.orderservice.repository.OrderRepository;

@Service
public class OrderService {

	private final OrderRepository orderRepository;

	private final ProductClient productClient;
   
	public OrderService(OrderRepository orderRepository, ProductClient productClient) {
		this.orderRepository = orderRepository;
		this.productClient = productClient;
	}

	public Order placeOrder(Order request) {
		Product product = productClient.getProductById(request.getOrderId());
		if (product.getStock() < request.getQuantity()) {
			throw new RuntimeException("Product stock insufficient.");
		}
		//Save the Order when stock is available
		Order order = new Order();
		order.setProductId(request.getProductId());
		order.setQuantity(request.getQuantity());
		order.setStatus("PLACED");
		return orderRepository.save(order);
	}

	public Order getOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	public List<Order> getOrdersByUserId(Long userId) {
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
