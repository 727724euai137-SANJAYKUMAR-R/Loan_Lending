package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")  
public class PaymentController {

    @GetMapping
    public String getPayments() {
        return "payments";
    }
}
