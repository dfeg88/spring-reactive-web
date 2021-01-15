package com.danielfegan.reactiveweb.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryPopulation {
    private String country;
    private int population;
}
