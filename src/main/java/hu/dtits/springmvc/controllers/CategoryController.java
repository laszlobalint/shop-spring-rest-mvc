package hu.dtits.springmvc.controllers;

import hu.dtits.springmvc.models.CategoryDTO;
import hu.dtits.springmvc.models.CategoryListDTO;
import hu.dtits.springmvc.services.interfaces.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("Category Controller")
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ApiOperation("Get all Categories in a list")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories() {
        return new CategoryListDTO(categoryService.findAll());
    }

    @ApiOperation("Get a Category by name property")
    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }
}
