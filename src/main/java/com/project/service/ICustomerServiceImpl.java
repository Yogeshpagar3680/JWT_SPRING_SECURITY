package com.project.service;

import com.project.dao.ICustomerRepo;
import com.project.dao.UserInfoRepository;
import com.project.entity.Customer;
import com.project.exception.CustomerNotFoundException;
import com.project.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    ICustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public String registerCustomer(Customer customer) {
        Integer customerId = customerRepo.save(customer).getCustomerId();
        log.info("Customer is saved with customer id :: " + customerId);
        return log.toString();
    }

    @Override
    public List<Customer> fetchAllCustomer() {

        List<Customer> customerList = customerRepo.findAll();
        return customerList;
    }

    @Override
    public Customer fetchCustomerByfirstName(String firstName) {

        return customerRepo.fetchCustomerByfirstName(firstName);
    }

    @Override
    public String updateCustomerByDetails(Customer customer) {
        Optional<Customer> optional = customerRepo.findById(customer.getCustomerId());
        if(optional.isPresent()){
            customerRepo.save(customer);
            log.info("Customer with id :: " + customer.getCustomerId() + " updated");
            return log.toString();
        }
        else
            throw new CustomerNotFoundException("Customer with id " + customer.getCustomerId() + " not available for updation");

    }

    @Override
    public String deleteCustomerById(Integer id) {
        Optional<Customer> optional = customerRepo.findById(id);
        if(optional.isPresent()){
            customerRepo.delete(optional.get());
            log.info("Customer with id :: " + id + " deleted");
            return log.toString();
        }
        else
          throw new CustomerNotFoundException("Customer with id " + id + " not available for deletion");
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return "user added to system ";
    }
}
