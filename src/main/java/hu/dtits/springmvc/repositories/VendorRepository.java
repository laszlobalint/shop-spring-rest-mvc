package hu.dtits.springmvc.repositories;

import hu.dtits.springmvc.domains.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
