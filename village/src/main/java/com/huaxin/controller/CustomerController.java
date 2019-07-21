package com.huaxin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxin.pojo.BaseDict;
import com.huaxin.pojo.Customer;
import com.huaxin.pojo.QueryVo;
import com.huaxin.service.BaseDictService;
import com.huaxin.service.CustomerService;
import com.huaxin.util.Page;

/**
 * 客户管理Controller
 * <p>Title: CustomerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class CustomerController {
	@Autowired
	private BaseDictService baseDictService;
	@Autowired
	private CustomerService customerService;
	@Value("${customer.source.code}")
	private String custSorceCode;
	@Value("${customer.industory.code}")
	private String custIndustoryCode;
	@Value("${customer.level.code}")
	private String custLevelCode;

	
	@RequestMapping("/aaa")
	public String showCustomerList2() {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		return "customer";
	}
	
	
	@RequestMapping("/customer/list")
	public String showCustomerList(QueryVo queryVo,Model model) throws Exception {
		//乱码处理   StringUtils不好用
//		if (StringUtils.isEmpty(queryVo.getCustName())) {
//			String s1 = new String(queryVo.getCustName().getBytes("iso8859-1"), "utf-8");
//			queryVo.setCustName(new String(queryVo.getCustName().getBytes("iso8859-1"), "utf-8"));
//		}
		if (queryVo.getCustName()!=null && !"".equals(queryVo.getCustName())) {
			String s1 = new String(queryVo.getCustName().getBytes("iso8859-1"), "utf-8");
			queryVo.setCustName(new String(queryVo.getCustName().getBytes("iso8859-1"), "utf-8"));
		}
		//初始化客户来源
		List<BaseDict> sourceList = baseDictService.getDictListByTypeCode(custSorceCode);
		//所属行业
		List<BaseDict> industoryList = baseDictService.getDictListByTypeCode(custIndustoryCode);
		//客户级别
		List<BaseDict> levelList = baseDictService.getDictListByTypeCode(custLevelCode);
		//把字典信息传递给页面
		model.addAttribute("fromType", sourceList);
		model.addAttribute("industryType", industoryList);
		model.addAttribute("levelType", levelList);
		//根据查询条件查询客户列表
		Page<Customer> page = customerService.getCustomerList(queryVo);
		//把客户列表传递给页面
		model.addAttribute("page", page);
		//参数回显
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustory());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		
		return "customer";
	}
	
	@RequestMapping("/customer/edit")
	@ResponseBody
	public Customer getCustomerById(Long id) {
		Customer customer = customerService.getCustomerById(id);
		return customer;
	}
	
	@RequestMapping(value="/customer/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateCustomer(Customer customer) {
		customerService.updateCustomer(customer);
		return "OK";
	}
	
	@RequestMapping("/customer/delete")
	@ResponseBody
	public String deleteCustomer(Long id) {
		customerService.deleteCustomer(id);
		return "OK";
	}
}
