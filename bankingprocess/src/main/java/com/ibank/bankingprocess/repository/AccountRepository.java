package com.ibank.bankingprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibank.bankingprocess.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
