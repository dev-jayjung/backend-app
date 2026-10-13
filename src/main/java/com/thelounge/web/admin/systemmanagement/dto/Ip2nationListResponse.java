package com.thelounge.web.admin.systemmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ip2nationListResponse {
    private Long uid;
    private Long ipStart;
    private Long ipEnd;

    private String ipStartText;
    private String ipEndText;

    private String country;
    private String countryNameCode;
    private String countryShortName;
    private String countryFullName;
}
