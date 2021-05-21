package hu.dtits.springmvc.services.interfaces;

import hu.dtits.springmvc.models.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();

    CustomerDTO findById(Long id);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO saveByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patch(Long id, CustomerDTO customerDTO);

    void deleteById(Long id);
}
