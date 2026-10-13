package com.thelounge.web.admin.messagemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import tools.jackson.databind.annotation.JsonDeserialize;
import tools.jackson.databind.deser.jdk.StringDeserializer;

@Getter
@Setter
public class MsgIssueRequest {

    @NotBlank(message = "입력 유형을 선택해 주세요.")
    private String inputType;

    @NotBlank(message = "발행 대상을 입력해 주세요.")
    @JsonDeserialize(using = StringDeserializer.class)
    private String inputData;

    @NotBlank(message = "회신 폰번호를 선택해 주세요.")
    private String phoneNumSender;
}
