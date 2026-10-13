package com.thelounge.web.admin.messagemanagement.dto;

import com.thelounge.core.common.type.ServiceStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgSenderAllowableSaveRequest {

    @NotBlank(message = "서비스 업체를 입력해 주세요.")
    @Size(max = 20, message = "서비스 업체는 20자 이내로 입력해 주세요.")
    @Pattern(regexp = MessageValidationPattern.COMPANY_CODE,
            message = "서비스 업체는 영문/숫자/언더스코어만 사용할 수 있습니다.")
    private String company;

    @NotBlank(message = "발신 번호를 입력해 주세요.")
    @Pattern(regexp = MessageValidationPattern.SENDER_PHONE,
            message = "발신 번호 형식이 올바르지 않습니다. (예: 01012345678, 1111-2222)")
    private String phoneNum;

    @Pattern(regexp = "^$|" + MessageValidationPattern.SENDER_PHONE,
            message = "콜백 CTN 형식이 올바르지 않습니다.")
    private String callbackCtn;

    @NotBlank(message = "사용자 이름을 입력해 주세요.")
    @Size(max = 25, message = "사용자 이름은 25자 이내로 입력해 주세요.")
    private String userName;

    @NotNull(message = "디폴트 발신자 여부를 선택해 주세요.")
    @Pattern(regexp = "^[01]$", message = "디폴트 발신자 값이 올바르지 않습니다.")
    private String defaultSender;

    @NotNull(message = "사용 여부를 선택해 주세요.")
    private ServiceStatus onService;

    @Size(max = 255, message = "메모는 255자 이내로 입력해 주세요.")
    private String memo;
}
