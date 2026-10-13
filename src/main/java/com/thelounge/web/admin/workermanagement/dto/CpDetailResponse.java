package com.thelounge.web.admin.workermanagement.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

/** CP 관리자 등록/수정 폼 바인딩용 응답 DTO. 비밀번호는 화면에 내려보내지 않는다. */
@Getter
@Setter
public class CpDetailResponse {
    private Long uid;
    private String cpGroup;
    private String cpAccountId;
    private String cpName;
    private Long loungeUid;
    private String supervisor;
    private String userName;
    private String phoneNum;
    private String telephone;
    private String emailAddress;
    private String autoLogin;
    private String allowOnlyOneLogin;
    private Integer serviceStatus;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
}
