package hu.dtits.springmvc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

}