package com.kang.jpa.hibernate.entity;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class PartTimeEmployee extends Employee {

    protected PartTimeEmployee() {
    }

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        super(name); // calling the Employee constructor with the name;
        this.hourlyWage = hourlyWage;
    }

    private BigDecimal hourlyWage;
}
