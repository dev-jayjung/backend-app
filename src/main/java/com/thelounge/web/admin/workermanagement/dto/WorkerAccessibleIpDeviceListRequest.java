package com.thelounge.web.admin.workermanagement.dto;

import java.util.Set;

import com.thelounge.core.common.dto.PageRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerAccessibleIpDeviceListRequest extends PageRequest {
    /** 헤더 클릭 정렬을 허용하는 컬럼. 공통 PageRequest.getOrderBy() 는 컬럼 검증을 하지 않으므로 여기서 검증한다. */
    private static final Set<String> SORT_FIELDS = Set.of("uid", "regDate", "updateDate");

    private String onService;

    @Override
    public String getSortBy() {
        return SORT_FIELDS.contains(super.getSortBy()) ? super.getSortBy() : "uid";
    }
}
