package com.example.productdemo.rest;

import com.example.productdemo.dto.PersistableCustomer;
import com.example.productdemo.dto.ReadableCustomer;
import com.example.productdemo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<ReadableCustomer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customerId}")
    public ReadableCustomer findCustomerById(@PathVariable int customerId) {
        return customerService.findCustomerById(customerId);
    }

    @PostMapping
    public void saveCustomer(@RequestBody PersistableCustomer persistableCustomer) {

        persistableCustomer.setCustomerId(0);

        customerService.save(persistableCustomer);
    }

    @PutMapping
    public void updateCustomer(@RequestBody PersistableCustomer persistableCustomer) {

        customerService.save(persistableCustomer);
    }

    @DeleteMapping("{customerId}")
    public String deleteCustomerById(@PathVariable int customerId) {

        customerService.deleteCustomerById(customerId);

        return "Deleted Customer id - " + customerId;
    }
}