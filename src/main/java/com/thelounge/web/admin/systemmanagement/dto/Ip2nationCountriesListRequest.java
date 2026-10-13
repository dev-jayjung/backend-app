package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ip2nationCountriesListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "code", "isoCode2", "isoCode3", "isoCountry", "lat", "lon");

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
