package com.thelounge.web.admin.messagemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgIssueHistorySaveRequest {

    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(max = 255, message = "제목은 255자 이내로 입력해 주세요.")
    private String title;

    @Size(max = 100, message = "구분 코드는 100자 이내로 입력해 주세요.")
    @Pattern(regexp = "^$|^[0-9a-zA-Z_-]+$", message = "구분 코드는 영문/숫자와 _ - 만 사용할 수 있습니다.")
    private String code = "";

    @NotNull(message = "발송 유형을 선택해 주세요.")
    private Integer sendTarget;

    @Size(max = 40, message = "LMS 제목은 40자 이내로 입력해 주세요.")
    private String lmsTitle = "";

    @NotBlank(message = "메시지를 입력해 주세요.")
    private String msgFormat;

    private Long bbsFileUidMmsImg = 0L;

    private String receiverCondition = "";

    private String memo = "";
}
