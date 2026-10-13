package com.thelounge.web.admin.push.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PushSendTarget {
    ALL_NOTICE(1, "전체공지"),
    GROUP_NOTICE(2, "단체알림"),
    INDIVIDUAL_NOTICE(3, "개별알림"),
    SYSTEM_NOTICE(4, "시스템알림"),
    MANAGEMENT_RECORD(5, "관리기록"),
    FRIEND_NOTICE(7, "회원간알림"),
    ETC(9, "기타"),
    EVENT_NOTICE(10, "이벤트공지");

    private final int value;
    private final String description;
}
