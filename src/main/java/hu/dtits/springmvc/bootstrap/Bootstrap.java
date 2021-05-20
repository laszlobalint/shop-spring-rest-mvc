package hu.dtits.springmvc.bootstrap;

import hu.dtits.springmvc.domains.Category;
import hu.dtits.springmvc.domains.Customer;
import hu.dtits.springmvc.domains.Vendor;
import hu.dtits.springmvc.repositories.CategoryRepository;
import hu.dtits.springmvc.repositories.CustomerRepository;
import hu.dtits.springmvc.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {
        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        categoryRepository.save(new Category(1L, "Fruits"));
        categoryRepository.save(new Category(2L, "Dried"));
        categoryRepository.save(new Category(3L, "Fresh"));
        categoryRepository.save(new Category(4L, "Exotic"));
        categoryRepository.save(new Category(5L, "Nuts"));

        System.out.printf("Categories loaded: %s%n", categoryRepository.count());
    }

    private void loadCustomers() {
        customerRepository.save(new Customer(1L, "Michale", "Weston"));
        customerRepository.save(new Customer(2L, "Sam", "Axe"));

        System.out.printf("Customers loaded: %s%n", customerRepository.count());
    }

    private void loadVendors() {
        vendorRepository.save(new Vendor(1L, "Vendor 1"));
        vendorRepository.save(new Vendor(2L, "Vendor 2"));

        System.out.printf("Vendors loaded: %s%n", vendorRepository.count());
    }
}
