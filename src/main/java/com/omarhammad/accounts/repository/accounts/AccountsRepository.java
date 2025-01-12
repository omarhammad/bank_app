package com.omarhammad.accounts.repository.accounts;

import com.omarhammad.accounts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountsByCustomer_Id(Long customerId);

    void deleteAccountByCustomer_Id(Long customerId);
}
