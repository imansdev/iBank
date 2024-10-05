package com.ibank.bankingprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ibank.bankingprocess.model.Accounts;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {

}
