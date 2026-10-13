package com.thelounge.web.admin.systemmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorCodeSaveRequest {

    @Pattern(regexp = "^[01]$", message = "중요도 값이 올바르지 않습니다.")
    private String critical = "0";

    @NotBlank(message = "에러 코드를 입력해 주세요.")
    @Size(max = 5, message = "에러 코드는 5자 이내로 입력해 주세요.")
    @Pattern(regexp = SystemValidationPattern.ERROR_CODE, message = "에러 코드는 대문자와 숫자만 사용할 수 있습니다.")
    private String errorCode;

    @NotBlank(message = "에러 내용(한국어)을 입력해 주세요.")
    @Size(max = 255, message = "에러 내용은 255자 이내로 입력해 주세요.")
    private String errorMeaningKo;

    @Size(max = 255, message = "에러 내용은 255자 이내로 입력해 주세요.")
    private String errorMeaningEn = "";

    @Size(max = 255, message = "에러 내용은 255자 이내로 입력해 주세요.")
    private String errorMeaningCn = "";

    @Size(max = 255, message = "메모는 255자 이내로 입력해 주세요.")
    private String memo = "";
}
