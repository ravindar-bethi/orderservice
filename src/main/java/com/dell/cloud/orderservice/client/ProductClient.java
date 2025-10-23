package com.dell.cloud.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dell.cloud.orderservice.entity.Product;

@FeignClient(name="productservice")
public interface ProductClient {
	@GetMapping("/products/{id}")
   public Product getProductById(@PathVariable("id") Long id);
}
