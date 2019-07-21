package com.huaxin.mapper;

import java.util.List;

import com.huaxin.pojo.Customer;
import com.huaxin.pojo.QueryVo;

public interface CustomerDao {
	List<Customer> getCustomerList(QueryVo queryVo);
	Integer getCustomerListCount(QueryVo queryVo);
	Customer getCustomerById(Long id);
	void updateCustomer(Customer customer);
	void deleteCustomer(Long id);
}
