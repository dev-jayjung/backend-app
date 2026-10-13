package com.thelounge.web.admin.messagemanagement.dto;

import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.web.admin.workermanagement.dto.ValidationPattern;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerSmsReceiverSaveRequest {

    @NotBlank(message = "사용자 ID를 입력해 주세요.")
    @Size(max = 100, message = "사용자 ID는 100자 이내로 입력해 주세요.")
    @Pattern(regexp = ValidationPattern.ACCOUNT_ID,
            message = "사용자 ID 형식이 올바르지 않습니다. (영문으로 시작하는 5~30자 또는 이메일)")
    private String accountId;

    @NotBlank(message = "사용자 이름을 입력해 주세요.")
    @Size(max = 25, message = "사용자 이름은 25자 이내로 입력해 주세요.")
    private String userName;

    @NotBlank(message = "핸드폰 번호를 입력해 주세요.")
    @Pattern(regexp = MessageValidationPattern.MOBILE_PHONE,
            message = "핸드폰 번호 형식이 올바르지 않습니다. (예: 01012345678)")
    private String phoneNum;

    @NotBlank(message = "이메일 주소를 입력해 주세요.")
    @Email(message = "이메일 주소 형식이 올바르지 않습니다.")
    @Size(max = 100, message = "이메일 주소는 100자 이내로 입력해 주세요.")
    private String emailAddress;

    @Size(max = 100, message = "역할은 100자 이내로 입력해 주세요.")
    private String role = "";

    @NotNull(message = "사용 여부를 선택해 주세요.")
    private ServiceStatus onService;
}
