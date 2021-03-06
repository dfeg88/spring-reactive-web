package com.danielfegan.reactiveweb.core.model;

import lombok.Data;

import java.util.List;

@Data
public class RestCountriesClientResponse {
    private String name;
    private int population;
    private List<String> borders;
}
