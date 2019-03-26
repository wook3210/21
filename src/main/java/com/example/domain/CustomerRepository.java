package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.example.domain.Customer;

import org.springframework.stereotype.Repository;

/**
 * 
 * @author wook
 *
 */
@Repository
public class CustomerRepository {
 
	private final ConcurrentMap<Integer, Customer> customers = new ConcurrentHashMap<>();
	
	public List<Customer> findAll(){
		return new ArrayList<>(customers.values());
	}
	
	public Customer fineOne(Integer customerId){
		return customers.get(customerId);
	}
	public Customer save(Customer customer){
		return customers.put(customer.getId(), customer);
	}
	public void delete(Integer customerId){
		customers.remove(customerId);
	}
	 
}
