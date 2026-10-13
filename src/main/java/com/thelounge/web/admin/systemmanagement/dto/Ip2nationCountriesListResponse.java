package com.thelounge.web.admin.systemmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ip2nationCountriesListResponse {
    private Long uid;
    private String code;
    private String isoCode2;
    private String isoCode3;
    private String isoCountry;
    private String country;
    private Float lat;
    private Float lon;
}
