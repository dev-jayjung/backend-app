package com.thelounge.web.admin.systemmanagement.dto;

import com.thelounge.core.common.type.ServiceStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrossDomainAllowableSaveRequest {

    @NotBlank(message = "허용 도메인을 입력해 주세요.")
    @Size(max = 50, message = "허용 도메인은 50자를 넘을 수 없습니다.")
    @Pattern(regexp = SystemValidationPattern.DOMAIN,
            message = "도메인 형식이 올바르지 않습니다.")
    private String allowableDomain;

    @NotNull(message = "사용 여부를 선택해 주세요.")
    private ServiceStatus onService;

    @Size(max = 255, message = "메모는 255자를 넘을 수 없습니다.")
    private String memo;
}
