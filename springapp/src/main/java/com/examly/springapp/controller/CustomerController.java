package com.examly.springapp.controller;

import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepo customerRepo;

    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        if (customer == null) {
            return ResponseEntity.badRequest().build();
        }
        Customer savedCustomer = customerRepo.save(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!customerRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customer.setCustomerId(id);
        Customer updatedCustomer = customerRepo.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (!customerRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        customerRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Customer>> getCustomersWithPagination(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerRepo.findAll(pageable);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerRepo.findByEmail(email);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Customer not found with email: " + email);
    }

    @GetMapping("/creditScore/{score}")
    public ResponseEntity<?> getCustomersByCreditScore(@PathVariable Double score) {
        List<Customer> customers = customerRepo.findByCreditScoreGreaterThanEqual(score);
        if (customers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No customers found with credit score >= " + score);
        }
        return ResponseEntity.ok(customers);
    }
}
