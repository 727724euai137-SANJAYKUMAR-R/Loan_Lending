package com.examly.springapp.controller;

import com.examly.springapp.model.Loan;
import com.examly.springapp.repository.LoanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanRepo loanRepo;

    @PostMapping
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan) {
        Loan savedLoan = loanRepo.save(loan);
        return new ResponseEntity<>(savedLoan, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanRepo.findAll();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        Optional<Loan> loan = loanRepo.findById(id);
        return loan.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id, @RequestBody Loan loan) {
        if (!loanRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        loan.setLoanId(id);
        Loan updatedLoan = loanRepo.save(loan);
        return ResponseEntity.ok(updatedLoan);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<?> getLoansByStatus(@PathVariable String status) {
        List<Loan> loans = loanRepo.findByStatus(status);
        if (loans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No loans found with status: " + status);
        }
        return ResponseEntity.ok(loans);
    }
}
