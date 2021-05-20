package hu.dtits.springmvc.repositories;

import hu.dtits.springmvc.domains.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
