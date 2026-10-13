package com.thelounge.web.admin.workermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 화면 선택 목록 한 건. 공통 selectBox 프래그먼트의 기본 키(code / description)에 맞춘다.
 * 코드값은 DB 에서 가져오고 표시명은 화면 쪽 매핑으로 채운다.
 */
@Getter
@AllArgsConstructor
public class CodeOption {
    private final String code;
    private final String description;
}
