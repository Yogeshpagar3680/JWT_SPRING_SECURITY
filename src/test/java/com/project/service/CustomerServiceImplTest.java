package com.project.service;

import com.project.dao.ICustomerRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ICustomerServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {

    @MockBean
    private ICustomerRepo iCustomerRepo;

    @Autowired
    private ICustomerServiceImpl iCustomerServiceImpl;

    @Test
    public void testGetCustomers(){
        when(iCustomerRepo.findAll()).thenReturn(new ArrayList<>());
        assert(iCustomerServiceImpl.fetchAllCustomer().isEmpty());
        verify(iCustomerRepo).findAll();
    }
}
