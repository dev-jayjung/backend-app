package com.thelounge.web.admin.workermanagement.dto;

/** 작업자관리 요청 DTO 에서 공용으로 쓰는 검증 정규식. */
public final class ValidationPattern {

    /** 옥텟 자리에 와일드카드(*)를 허용한다 — '*.*.*.*'(전체 오픈) 같은 값이 실제로 쓰인다. */
    public static final String IP_ADDRESS = "^(\\d{1,3}|\\*)\\.(\\d{1,3}|\\*)\\.(\\d{1,3}|\\*)\\.(\\d{1,3}|\\*)$";

    public static final String DEVICE_SN = "^[A-Za-z0-9]+$";

    /** AS-IS InputDataType::WORKER_ID — 영문 시작, 숫자·_·-·.·+·@ 허용, 5~30자(이메일도 허용) */
    public static final String ACCOUNT_ID = "^[A-Za-z][A-Za-z0-9._+@-]{4,29}$";

    /**
     * AS-IS InputDataType::WORKER_PASSWORD — 영문+숫자+특수문자, 8자 이상.
     * 수정 시에는 비워서 보낼 수 있으므로 빈 값도 통과시킨다.
     */
    public static final String PASSWORD = "^$|^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])"
            + "[0-9a-zA-Z\\\\~`!@#$%^&*()_\\-+=|\"'?/<>,.;:\\[\\]{}]{8,}$";

    private ValidationPattern() {
    }
}
