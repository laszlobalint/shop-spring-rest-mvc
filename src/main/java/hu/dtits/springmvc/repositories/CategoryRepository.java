package hu.dtits.springmvc.repositories;

import hu.dtits.springmvc.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
