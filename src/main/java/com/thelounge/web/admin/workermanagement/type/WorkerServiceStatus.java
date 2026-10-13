package com.thelounge.web.admin.workermanagement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 서비스 상태 (WORKER / CP / STORE 의 service_status 컬럼값)의 표시명 매핑.
 */
@Getter
@AllArgsConstructor
public enum WorkerServiceStatus {
    APPLIED(1, "가입신청"),
    SERVICE(2, "서비스중"),
    SUSPENSION(4, "정지"),
    CLOSE(8, "해지");

    private final int value;
    private final String description;

    public static WorkerServiceStatus of(Integer value) {
        if (value == null) {
            return null;
        }

        for (WorkerServiceStatus status : values()) {
            if (status.value == value) {
                return status;
            }
        }

        return null;
    }

    /** 값에 대응하는 표시명. 정의에 없는 값은 값 자체를 문자열로 돌려준다. */
    public static String descriptionOf(Integer value) {
        WorkerServiceStatus status = of(value);

        return status != null ? status.description : String.valueOf(value);
    }
}
