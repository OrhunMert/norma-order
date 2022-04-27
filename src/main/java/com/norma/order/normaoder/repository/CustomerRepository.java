package com.norma.order.normaoder.repository;

import com.norma.order.normaoder.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Collection<Customer> findAllByIsDeleted(boolean isDeleted);

}
