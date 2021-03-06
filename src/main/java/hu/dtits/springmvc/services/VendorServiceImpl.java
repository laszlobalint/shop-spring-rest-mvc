package hu.dtits.springmvc.services;

import hu.dtits.springmvc.controllers.VendorController;
import hu.dtits.springmvc.domains.Vendor;
import hu.dtits.springmvc.exceptions.ResourceNotFoundException;
import hu.dtits.springmvc.mappers.VendorMapper;
import hu.dtits.springmvc.models.VendorDTO;
import hu.dtits.springmvc.models.VendorListDTO;
import hu.dtits.springmvc.repositories.VendorRepository;
import hu.dtits.springmvc.services.interfaces.VendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO findById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO findAll() {
        List<VendorDTO> vendorDTOs = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());

        return new VendorListDTO(vendorDTOs);
    }

    @Override
    public VendorDTO save(VendorDTO vendorDTO) {

        return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patch(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if (vendorDTO.getName() != null)
                        vendor.setName(vendorDTO.getName());

                    return saveAndReturnDTO(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);
        returnDto.setVendorUrl(getUrl(savedVendor.getId()));

        return returnDto;
    }

    private String getUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }
}