package com.thelounge.web.admin.systemmanagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceEnvValueRequest {
    private String field;
    private String value;
}
