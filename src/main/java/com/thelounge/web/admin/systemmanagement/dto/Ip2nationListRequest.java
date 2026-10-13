package com.thelounge.web.admin.systemmanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ip2nationListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of(
            "uid", "ipStart", "ipEnd", "country", "countryNameCode", "countryShortName", "countryFullName");

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
