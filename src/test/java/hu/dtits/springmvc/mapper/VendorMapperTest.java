package hu.dtits.springmvc.mapper;

import hu.dtits.springmvc.domains.Vendor;
import hu.dtits.springmvc.mappers.VendorMapper;
import hu.dtits.springmvc.models.VendorDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorMapperTest {

    public static final String NAME = "John Doe Shop";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void testVendorToVendorDTO() throws Exception {
        Vendor vendor = new Vendor();
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertEquals(vendor.getName(), vendorDTO.getName());
    }

    @Test
    public void testVendorDTOtoVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOtoVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), vendor.getName());
    }
}