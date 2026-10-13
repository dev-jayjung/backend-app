package com.thelounge.web.admin.messagemanagement.dto;

public final class MessageValidationPattern {

    // 발신번호
    public static final String SENDER_PHONE = "^[0-9]{4,11}$|^[0-9]{2,4}-[0-9]{3,4}(-[0-9]{4})?$";

    // 서비스 업체 코드
    public static final String COMPANY_CODE = "^[A-Za-z0-9_]+$";

    // 수신 코드
    public static final String CONCERN_PAGE_CODE = "^[0-9a-zA-Z_-]+$";

    // 수신자 폰번호
    public static final String MOBILE_PHONE = "^01[016789]-?[0-9]{3,4}-?[0-9]{4}$";

    private MessageValidationPattern() {
    }
}
