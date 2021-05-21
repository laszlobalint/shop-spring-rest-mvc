package hu.dtits.springmvc.services;

import hu.dtits.springmvc.domains.Vendor;
import hu.dtits.springmvc.mappers.VendorMapper;
import hu.dtits.springmvc.models.VendorDTO;
import hu.dtits.springmvc.models.VendorListDTO;
import hu.dtits.springmvc.repositories.VendorRepository;
import hu.dtits.springmvc.services.interfaces.VendorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class VendorServiceImplTest {
    public static final String NAME_1 = "Old Vendor";
    public static final long ID_1 = 1L;

    public static final String NAME_2 = "New Vendor";
    public static final long ID_2 = 2L;

    @Mock
    VendorRepository vendorRepository;

    @Mock
    VendorService vendorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void testGetVendorById() {
        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void testGetVendors() {
        List<Vendor> vendors = Arrays.asList(getVendor1(), getVendor2());

        given(vendorRepository.findAll()).willReturn(vendors);

        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        then(vendorRepository).should(times(1)).findAll();

        assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
    }

    @Test
    public void testCreateVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);
        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));

        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testSaveVendorByDTO() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);
        Vendor vendor = getVendor1();

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.saveVendorByDTO(ID_1, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));

        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testPatchVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);
        Vendor vendor = getVendor1();

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.patchVendor(ID_1, vendorDTO);

        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void testDeleteVendorById() {
        vendorService.deleteVendorById(1L);

        then(vendorRepository).should().deleteById(anyLong());
    }

    private Vendor getVendor1() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_1);
        vendor.setId(ID_1);
        return vendor;
    }

    private Vendor getVendor2() {
        Vendor vendor = new Vendor();
        vendor.setName(NAME_2);
        vendor.setId(ID_2);
        return vendor;
    }
}
