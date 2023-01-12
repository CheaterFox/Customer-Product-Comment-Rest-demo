package com.example.productdemo.service;

import com.example.productdemo.dao.CustomerRepository;
import com.example.productdemo.dto.PersistableCustomer;
import com.example.productdemo.dto.ReadableCustomer;
import com.example.productdemo.entity.Customer;
import com.example.productdemo.populator.CustomerPopulator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    private final CustomerPopulator customerPopulator;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerPopulator customerPopulator) {
        this.customerRepository = customerRepository;
        this.customerPopulator = customerPopulator;
    }

    @Override
    public List<ReadableCustomer> getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();
        List<ReadableCustomer> readableCustomerList = customerPopulator.convertCustomerListToReadableCustomerList(customerList);
        return readableCustomerList;
    }

    @Override
    public ReadableCustomer findCustomerById(int customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        ReadableCustomer readableCustomer = null;

        if (optionalCustomer.isPresent()) {
            readableCustomer = customerPopulator.convertCustomerToReadableCustomer(optionalCustomer.get());
        }
        else {
            throw new RuntimeException("Did not find customer id - " + customerId);
        }
        return readableCustomer;
    }

    // findCustomerByIdForPopulator this method is for dto converter class (CommentPopulator line: 32)
    @Override
    public Customer findCustomerByIdForPopulator(int customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        Customer customer = null;

        if (optionalCustomer.isPresent()) {
            customer = optionalCustomer.get();
        }
        else {
            throw new RuntimeException("Did not find customer id - " + customerId);
        }
        return customer;
    }

    @Override
    public void save(PersistableCustomer persistableCustomer) {
        Customer customer = customerPopulator.convertPersistableCustomerToCustomer(persistableCustomer);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(int customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
        }
        else {
            throw new RuntimeException("Did not find customer id - " + customerId);
        }
    }
}