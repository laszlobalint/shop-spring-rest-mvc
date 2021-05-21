package hu.dtits.springmvc.services.interfaces;

import hu.dtits.springmvc.models.VendorDTO;
import hu.dtits.springmvc.models.VendorListDTO;

public interface VendorService {
    VendorDTO findById(Long id);

    VendorListDTO findAll();

    VendorDTO save(VendorDTO vendorDTO);

    VendorDTO saveByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patch(Long id, VendorDTO vendorDTO);

    void deleteById(Long id);
}
