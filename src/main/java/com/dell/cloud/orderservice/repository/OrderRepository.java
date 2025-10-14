package com.dell.cloud.orderservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dell.cloud.orderservice.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	List<Order> findByUserId(String userId);

}
