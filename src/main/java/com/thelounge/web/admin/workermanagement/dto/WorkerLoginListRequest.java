package com.thelounge.web.admin.workermanagement.dto;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerLoginListRequest extends PageRequest {
    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate");

    private String loginSuccess;
    private String workerId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate loginDateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate loginDateTo;

    @Override
    public String getSortBy() {
        return SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
