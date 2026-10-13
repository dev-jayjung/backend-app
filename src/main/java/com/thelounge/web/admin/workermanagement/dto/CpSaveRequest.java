package com.thelounge.web.admin.workermanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/** CP 관리자 등록/수정 공용 요청 DTO. 수정 시 cpAccountPwd 가 비어 있으면 기존 값을 유지한다. */
@Getter
@Setter
public class CpSaveRequest {

    /** 등록에만 적용할 제약을 구분하는 검증 그룹. */
    public interface Create {
    }

    @NotBlank(message = "제공 서비스를 선택해 주세요.")
    private String cpGroup;

    @NotBlank(message = "아이디를 입력해 주세요.")
    @Pattern(regexp = ValidationPattern.ACCOUNT_ID,
            message = "아이디 형식이 올바르지 않습니다. (영문으로 시작, 5~30자)")
    private String cpAccountId;

    @NotBlank(groups = Create.class, message = "비밀번호를 입력해 주세요.")
    @Pattern(regexp = ValidationPattern.PASSWORD,
            message = "비밀번호를 올바로 입력해 주세요. (영문+숫자+특수문자, 8자 이상)")
    private String cpAccountPwd;

    @NotBlank(message = "업체 이름을 입력해 주세요.")
    private String cpName;

    @NotBlank(message = "담당자 명을 입력해 주세요.")
    private String userName;

    private String phoneNum;
    private String telephone;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String emailAddress;

    /**
     * 계정 구분 : 1=본사, 0=일반. 등록 시 어느 버튼으로 들어왔는지에 따라 화면이 정하고,
     * 수정 시에는 기존 값을 그대로 되돌려 보낸다. 사용자가 고르는 항목이 아니다.
     */
    @NotBlank(message = "계정 구분이 지정되지 않았습니다.")
    private String supervisor;

    /**
     * 매장 연결. 매장 선택 기능이 아직 없어 화면이 0 을 고정으로 보낸다.
     * 매장 선택이 생기면 일반 계정은 선택한 매장 uid, 본사 계정은 0 을 보낸다.
     */
    @NotNull(message = "매장 정보가 지정되지 않았습니다.")
    private Long loungeUid;

    private String autoLogin;
    private String allowOnlyOneLogin;

    @NotNull(message = "서비스 상태를 선택해 주세요.")
    private Integer serviceStatus;

    private String memo;
}
