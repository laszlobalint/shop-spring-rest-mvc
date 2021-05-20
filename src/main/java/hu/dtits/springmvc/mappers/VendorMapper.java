package hu.dtits.springmvc.mappers;

import hu.dtits.springmvc.domains.Vendor;
import hu.dtits.springmvc.models.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
