package com.project.service;

import com.project.entity.Customer;
import com.project.user.UserInfo;

import java.util.List;


public interface ICustomerService {

    public String registerCustomer(Customer customer);
    public List<Customer> fetchAllCustomer();
    public Customer fetchCustomerByfirstName(String firstName);
    public String updateCustomerByDetails(Customer customer);
    public String deleteCustomerById(Integer id);
    public String addUser(UserInfo userInfo);
}
