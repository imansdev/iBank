package com.ibank.bankingprocess.repository;

import com.ibank.bankingprocess.model.DecryptedAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DecryptedAccountRepository extends JpaRepository<DecryptedAccount, Long> {
    List<DecryptedAccount> findByDecryptedBalanceGreaterThan(Long balance);
}
