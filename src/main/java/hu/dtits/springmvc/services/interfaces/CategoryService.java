package hu.dtits.springmvc.services.interfaces;

import hu.dtits.springmvc.models.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    CategoryDTO findByName(String name);
}
