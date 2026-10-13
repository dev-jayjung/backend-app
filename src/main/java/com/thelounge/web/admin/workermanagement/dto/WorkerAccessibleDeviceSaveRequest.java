package com.thelounge.web.admin.workermanagement.dto;

import com.thelounge.core.common.type.ServiceStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerAccessibleDeviceSaveRequest {

    @NotBlank(message = "장치 번호를 입력해 주세요.")
    @Pattern(regexp = ValidationPattern.DEVICE_SN, message = "장치 번호는 영문·숫자만 입력할 수 있습니다.")
    private String deviceSn;

    @NotNull(message = "사용 여부를 선택해 주세요.")
    private ServiceStatus onService;

    private String memo;
}
