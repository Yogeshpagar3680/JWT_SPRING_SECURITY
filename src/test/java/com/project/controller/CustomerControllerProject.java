package com.project.controller;

import com.project.entity.Customer;
import com.project.service.ICustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerController.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerProject {

    @Autowired
    CustomerController controller;

    @MockBean
    private ICustomerService service;

    @Test
    public void testEnrollCustomer() throws Exception {

        Date dateOfBirth = new Date("2024/05/05");
        Date registrationDate = new Date("2024/05/05");
        Customer customer = new Customer(1, "Avi", "Korde", "avi@gmail.com", dateOfBirth, registrationDate, true, false);
        when(service.registerCustomer(customer)).thenReturn(String.valueOf(customer));
        controller.enrollCustomer(customer);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.post("/api/customer/save");
        System.out.println(getResult);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(getResult);
    }

    @Test
    public void testDisplayCustomerDetails() throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        when(service.fetchAllCustomer()).thenReturn(customers);
        MockHttpServletRequestBuilder requestBuilder =MockMvcRequestBuilders.get("/api/customer/findAll");
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                .string(
                        "[{\"customerId\":null,\"firstName\":null,\"lastName\":null,\"email\":null,\"dateOfBirth\":null,\"registrationDate\":null,\"isActive\":null,\"deleted\":false}]"
                ));
    }


/**
 * @Test
 *     public void testdisplayTouristDetails() throws Exception {
 *         ArrayList<Tourist> tourists = new ArrayList<>();
 *         tourists.add(new Tourist());
 *         when(iTouristMgmtService.fetchAllTourist()).thenReturn(tourists);
 *         MockHttpServletRequestBuilder requestBuilder =MockMvcRequestBuilders.get("/api/tourist/findAll");
 *         MockMvcBuilders.standaloneSetup(touristController)
 *                 .build()
 *                 .perform(requestBuilder)
 *                 .andExpect(MockMvcResultMatchers.status().isOk())
 *                 .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
 *                 .andExpect(MockMvcResultMatchers.content()
 *                 .string(
 *                      "[{\"ticketId\":null,\"city\":null,\"name\":null,\"packageType\":null,\"budget\":null}]"
 *                 ));
 *     }
 */



}
