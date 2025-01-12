package com.omarhammad.accounts.repository.customers;

import com.omarhammad.accounts.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByMobileNumber(String mobileNumber);
}
