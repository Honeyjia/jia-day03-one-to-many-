package com.jiacool.dao;

import com.jiacool.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CstDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

}
