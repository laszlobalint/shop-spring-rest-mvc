package hu.dtits.springmvc.services;

import hu.dtits.springmvc.bootstrap.Bootstrap;
import hu.dtits.springmvc.domains.Customer;
import hu.dtits.springmvc.mappers.CustomerMapper;
import hu.dtits.springmvc.models.CustomerDTO;
import hu.dtits.springmvc.repositories.CategoryRepository;
import hu.dtits.springmvc.repositories.CustomerRepository;
import hu.dtits.springmvc.repositories.VendorRepository;
import hu.dtits.springmvc.services.interfaces.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CustomerServiceImplTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Mock
    CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void testPatchCustomerUpdateFirstName() {
        String updatedName = "Name Updated";
        Long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(updatedName);

        customerService.patch(id, customerDTO);

        if (customerRepository.findById(id).isPresent()) {
            Customer updatedCustomer = customerRepository.findById(id).get();

            assertNotNull(updatedCustomer);
            assertEquals(updatedName, updatedCustomer.getFirstname());
            assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstname())));
            assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));
        }
    }

    @Test
    public void testPatchCustomerUpdateLastName() {
        String updatedName = "Name Updated";
        long id = getCustomerIdValue();

        Customer originalCustomer = customerRepository.getOne(id);
        assertNotNull(originalCustomer);

        String originalFirstName = originalCustomer.getFirstname();
        String originalLastName = originalCustomer.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(updatedName);

        customerService.patch(id, customerDTO);

        if (customerRepository.findById(id).isPresent()) {
            Customer updatedCustomer = customerRepository.findById(id).get();

            assertNotNull(updatedCustomer);
            assertEquals(updatedName, updatedCustomer.getLastname());
            assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
            assertThat(originalLastName, not(equalTo(updatedCustomer.getLastname())));
        }
    }

    private Long getCustomerIdValue() {
        List<Customer> customers = customerRepository.findAll();

        return customers.get(0).getId();
    }
}