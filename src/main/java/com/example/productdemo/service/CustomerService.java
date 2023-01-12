package com.example.productdemo.service;


import com.example.productdemo.dto.PersistableCustomer;
import com.example.productdemo.dto.ReadableCustomer;
import com.example.productdemo.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<ReadableCustomer> getAllCustomers();
    ReadableCustomer findCustomerById(int customerId);

    Customer findCustomerByIdForPopulator(int customerId);

    void save(PersistableCustomer persistableCustomer);

    void deleteCustomerById(int customerId);


}