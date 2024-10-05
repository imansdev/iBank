package com.ibank.bankingprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibank.bankingprocess.model.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

}
