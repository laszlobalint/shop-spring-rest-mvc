package hu.dtits.springmvc.services;

import hu.dtits.springmvc.controllers.CustomerController;
import hu.dtits.springmvc.domains.Customer;
import hu.dtits.springmvc.exceptions.ResourceNotFoundException;
import hu.dtits.springmvc.mappers.CustomerMapper;
import hu.dtits.springmvc.models.CustomerDTO;
import hu.dtits.springmvc.repositories.CustomerRepository;
import hu.dtits.springmvc.services.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(Long id) {

        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .map(customerDTO -> {
                    customerDTO.setCustomerUrl(getUrl(id));
                    return customerDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {

        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }


    @Override
    public CustomerDTO saveByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patch(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {

                    if (customerDTO.getFirstname() != null)
                        customer.setFirstname(customerDTO.getFirstname());

                    if (customerDTO.getLastname() != null)
                        customer.setLastname(customerDTO.getLastname());

                    CustomerDTO returnDto = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
                    returnDto.setCustomerUrl(getUrl(id));

                    return returnDto;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);
        returnDto.setCustomerUrl(getUrl(savedCustomer.getId()));

        return returnDto;
    }

    private String getUrl(Long id) {

        return CustomerController.BASE_URL + "/" + id;
    }
}