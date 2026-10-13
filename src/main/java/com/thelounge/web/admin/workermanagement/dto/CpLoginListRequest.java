package com.thelounge.web.admin.workermanagement.dto;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CpLoginListRequest extends PageRequest {
    /** 헤더 클릭 정렬을 허용하는 컬럼. 공통 PageRequest.getOrderBy() 는 컬럼 검증을 하지 않으므로 여기서 검증한다. */
    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate");

    private String loginSuccess;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate regDateFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate regDateTo;

    @Override
    public String getSortBy() {
        return SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
