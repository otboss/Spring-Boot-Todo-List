package com.otboss.todo.service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.otboss.todo.model.Customer;

@Service
public class CustomerService {
    
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<Customer>();

        Stream<Customer> customerS = customers.stream();
        // customerS

        return customers;
    }

}
