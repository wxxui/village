package com.huaxin.service;

import com.huaxin.pojo.Customer;
import com.huaxin.pojo.QueryVo;
import com.huaxin.util.Page;

public interface CustomerService {

	Page<Customer> getCustomerList(QueryVo queryVo);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(Long id);
}
