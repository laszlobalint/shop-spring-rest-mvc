package hu.dtits.springmvc.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @ApiModelProperty(value = "Category ID", required = true)
    private Long id;

    @ApiModelProperty(value = "Category Name", required = true)
    private String name;
}