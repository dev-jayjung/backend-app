package com.thelounge.web.admin.messagemanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmsHubStatisticsListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "statisticsYearMonth", "userId");

    private String userId;

    private Integer statisticsYear;

    private Integer statisticsMonth;

    @Override
    public String getSortBy() {
        return super.getSortBy() != null && SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
