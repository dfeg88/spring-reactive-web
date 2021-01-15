package com.danielfegan.reactiveweb.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BordersPerCountry {
    private String country;
    private Integer borders;
}
