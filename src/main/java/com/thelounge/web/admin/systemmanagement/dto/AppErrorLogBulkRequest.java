package com.thelounge.web.admin.systemmanagement.dto;

import java.util.List;

import com.thelounge.core.common.type.ConfirmStatus;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppErrorLogBulkRequest {

    @NotEmpty(message = "대상을 선택해 주세요.")
    private List<@NotNull(message = "대상 코드가 올바르지 않습니다.") Long> uids;

    private ConfirmStatus confirmStatus;
}
