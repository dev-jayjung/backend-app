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
public class WorkerSmsConcernPageSaveRequest {

    public interface Create {
    }

    @NotBlank(message = "코드를 입력해 주세요.")
    @Size(max = 255, message = "코드는 255자 이내로 입력해 주세요.")
    @Pattern(regexp = MessageValidationPattern.CONCERN_PAGE_CODE, groups = Create.class,
            message = "코드는 영문/숫자와 _ - 만 사용할 수 있습니다.")
    private String code;

    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(max = 255, message = "제목은 255자 이내로 입력해 주세요.")
    private String title;

    @NotBlank(message = "소스 위치를 입력해 주세요.")
    @Size(max = 255, message = "소스 위치는 255자 이내로 입력해 주세요.")
    private String sourcePosition;

    @NotNull(message = "사용 여부를 선택해 주세요.")
    private ServiceStatus onService;

    @Size(max = 255, message = "메모는 255자 이내로 입력해 주세요.")
    private String memo;
}
