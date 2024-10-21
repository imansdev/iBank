package com.ibank.bankingprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibank.bankingprocess.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByCustomerId(Long customerId);

    Customer findByCustomerId(Long customerId);
}
