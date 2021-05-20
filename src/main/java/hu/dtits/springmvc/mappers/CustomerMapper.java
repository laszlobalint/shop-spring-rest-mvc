package hu.dtits.springmvc.mappers;

import hu.dtits.springmvc.domains.Customer;
import hu.dtits.springmvc.models.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);
}
