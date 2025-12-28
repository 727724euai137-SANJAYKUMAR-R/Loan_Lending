package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Guarantor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guarantorId;
    private String name;
    private String phone;

    public Guarantor() {
    }

    public Guarantor(Long guarantorId, String name, String phone) {
        this.guarantorId = guarantorId;
        this.name = name;
        this.phone = phone;
    }

    public Long getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(Long guarantorId) {
        this.guarantorId = guarantorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
