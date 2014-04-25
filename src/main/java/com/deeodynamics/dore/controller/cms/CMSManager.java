package com.deeodynamics.dore.controller.cms;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.deeodynamics.dore.domain.Customer;
import com.deeodynamics.dore.service.IGenericDBServ;

@Controller
public class CMSManager {
	@Resource(name="customerServ")
	private IGenericDBServ<Customer, Integer> customerServ;
	
  @RequestMapping(value="/cms", method=RequestMethod.GET)
  public String selectAllCategories(ModelMap model) {
    model.addAttribute("newCustomer", new Customer());
    model.addAttribute("customerList", customerServ.selectAll());
    
    return "retroCMS";
  }
    
  @RequestMapping(value = "/cms/customer/add", method = RequestMethod.POST)
  public String addCategory(@ModelAttribute("customer") Customer customer, BindingResult result) {
  	customerServ.makePersist(customer);
 
    return "redirect:/retroCMS";
  }
 
  @RequestMapping("/cms/customer/del/{customerId}")
  public String deleteContact(@PathVariable("customerId") Integer customerId) {
  	customerServ.delete(customerId);
 
    return "redirect:/retroCMS";
  }
}