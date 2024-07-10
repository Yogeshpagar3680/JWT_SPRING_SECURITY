package com.project.controller;


import com.project.entity.Customer;
import com.project.service.ICustomerService;
import com.project.user.UserInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    ICustomerService customerService;

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return customerService.addUser(userInfo);
    }

    @PostMapping("/save")
    @ApiOperation("To enroll new customer")
    public ResponseEntity<String> enrollCustomer(@Valid @RequestBody Customer customer){
          String message = customerService.registerCustomer(customer);
          return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    @ApiOperation("To fetch all customers")
    public ResponseEntity<List> getAllCustomer(){
        List<Customer> customerList = customerService.fetchAllCustomer();
        return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/firstName/{firstName}")
    @ApiOperation("To search customer by first name")
    public ResponseEntity<Customer> getCustomerByFirstName(@PathVariable("firstName") String firstName){
        Customer customer = customerService.fetchCustomerByfirstName(firstName);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PutMapping("/modify")
    @ApiOperation("To modify customer details")
    public ResponseEntity<String> modifyCustomer(@RequestBody Customer customer){
        String message = customerService.updateCustomerByDetails(customer);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("To delete customer by id")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Integer id){
        String message = customerService.deleteCustomerById(id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
