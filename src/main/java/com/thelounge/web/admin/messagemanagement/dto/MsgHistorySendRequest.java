package com.thelounge.web.admin.messagemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgHistorySendRequest {

    @NotBlank(message = "수신자를 입력해 주세요.")
    @Size(max = 255, message = "수신자는 255자 이내로 입력해 주세요.")
    private String receiverInfo;

    @NotBlank(message = "수신자 폰번호를 입력해 주세요.")
    @Pattern(regexp = MessageValidationPattern.MOBILE_PHONE,
            message = "수신자 폰번호 형식이 올바르지 않습니다. (예: 01012345678)")
    private String phoneNumReceiver;

    @NotBlank(message = "회신 폰번호를 선택해 주세요.")
    private String phoneNumSender;

    @Size(max = 40, message = "LMS 제목은 40자 이내로 입력해 주세요.")
    private String lmsTitle = "";

    @NotBlank(message = "메시지를 입력해 주세요.")
    private String msg;

    private Long bbsFileUidMmsImg = 0L;
}
