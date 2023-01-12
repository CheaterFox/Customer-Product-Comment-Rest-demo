package com.example.productdemo.populator;

import com.example.productdemo.dto.PersistableCustomer;
import com.example.productdemo.dto.ReadableCustomer;
import com.example.productdemo.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

//Converting persistable model to domain model or domain model to readable model
@Component
public class CustomerPopulator {

    public Customer convertPersistableCustomerToCustomer(PersistableCustomer persistableCustomer) {

        Customer customer = new Customer();
        customer.setId(persistableCustomer.getCustomerId());
        customer.setFirstName(persistableCustomer.getFirstName());
        customer.setLastName(persistableCustomer.getLastName());
        customer.setEmail(persistableCustomer.getEmail());

        return customer;
    }

    public ReadableCustomer convertCustomerToReadableCustomer(Customer customer) {

        ReadableCustomer readableCustomer = new ReadableCustomer();
        readableCustomer.setFirstName(customer.getFirstName());
        readableCustomer.setLastName(customer.getLastName());
        readableCustomer.setEmail(customer.getEmail());

        return readableCustomer;
    }

    public List<ReadableCustomer> convertCustomerListToReadableCustomerList(List<Customer> customerList) {

        return customerList.stream().map(this::convertCustomerToReadableCustomer).toList();
    }
}