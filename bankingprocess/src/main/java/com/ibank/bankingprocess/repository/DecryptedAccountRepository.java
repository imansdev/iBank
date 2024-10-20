package com.ibank.bankingprocess.repository;

import com.ibank.bankingprocess.model.DecryptedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecryptedAccountRepository extends JpaRepository<DecryptedAccount, Long> {

    @Query("SELECT da FROM DecryptedAccount da WHERE CAST(da.decryptedBalance AS double) > 1000")
    List<DecryptedAccount> findAccountsWithDecryptedBalanceGreaterThan1000();
}
