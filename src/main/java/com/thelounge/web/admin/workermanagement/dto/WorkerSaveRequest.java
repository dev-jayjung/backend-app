package com.thelounge.web.admin.workermanagement.dto;

import com.thelounge.core.common.type.ServiceStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/** 작업자 등록/수정 공용 요청 DTO. 수정 시 password 가 비어 있으면 기존 값을 유지한다. */
@Getter
@Setter
public class WorkerSaveRequest {

    /** 등록에만 적용할 제약을 구분하는 검증 그룹. */
    public interface Create {
    }

    /** 수정 시에는 화면에서 disabled 라 전송되지 않는다. 등록에만 검증한다. */
    @NotBlank(groups = Create.class, message = "작업자 ID를 입력해 주세요.")
    @Pattern(regexp = ValidationPattern.ACCOUNT_ID, groups = Create.class,
            message = "작업자 ID 형식이 올바르지 않습니다. (영문으로 시작, 5~30자)")
    private String workerId;

    /** 수정 시에는 화면에서 disabled 라 전송되지 않는다. 등록에만 검증한다. */
    @NotBlank(groups = Create.class, message = "작업자 이름을 입력해 주세요.")
    private String workerName;

    @NotBlank(groups = Create.class, message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = ValidationPattern.PASSWORD,
            message = "비밀번호를 올바로 입력해 주세요. (영문+숫자+특수문자, 8자 이상)")
    private String password;

    @NotBlank(message = "휴대폰 번호를 입력해 주세요.")
    private String phoneNum;

    @NotBlank(message = "이메일 주소를 입력해 주세요.")
    @Email(message = "이메일 주소 형식이 올바르지 않습니다.")
    private String emailAddress;

    private String defaultMenuCode;

    /** 메뉴 권한. 하단의 관리자 메뉴를 지정할 수 있는 권한 (AS-IS worker_detail.html 의 menu_auth). */
    private String menuAuth;

    /**
     * 임시 : menu_all_auth 는 화면에 대응 항목이 없는데 NOT NULL 이라 화면에서 0 을 고정으로 보낸다.
     * 이 컬럼의 실제 용도를 확인한 뒤 화면 항목을 만들거나 제거한다.
     */
    private Short menuAllAuth;

    private String serviceOperator;
    private Integer workerMaxIssueCount;
    private String saleChannel;
    private String autoLogin;
    private String allowOnlyOneLogin;
    private String memo;

    @NotNull(message = "사용 여부를 선택해 주세요.")
    private ServiceStatus onService;
}
