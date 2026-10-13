package com.thelounge.web.admin.workermanagement.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CP 서비스 그룹 (CP.cp_group 컬럼값)의 표시명 매핑.
 */
@Getter
@AllArgsConstructor
public enum CpServiceGroup {
    LOUNGE("lounge", "공항 라운지"),
    MATINA_GOLD("matina_gold", "마티나 골드"),
    DINING("dining", "공항 다이닝"),
    AIRPORT_COFFEE("airport_coffee", "공항 커피"),
    AIRPORT_HOTEL("airport_hotel", "다락휴 호텔"),
    USIM_ESIM("usim_esim", "글로벌 One SIM"),
    AIRPORT_EXPRESS("airport_express", "공항 직통열차"),
    AIRPORT_VALET("airport_valet", "공항 발렛"),
    GOLFCLUB("golfclub", "골프장"),
    GOLF_DRIVING_RANGE("golf_driving_range", "골프연습장"),
    BRUNCH("brunch", "호텔 브런치"),
    HOTEL_BUFFET("hotel_buffet", "특급호텔 뷔페"),
    HOTEL_VALET("hotel_valet", "호텔 발렛"),
    AIRPORT_LIMOUSINE("airport_limousine", "K공항리무진"),
    INCHEON_EXPRESSWAY("incheon_expressway", "공항 고속도로"),
    JAPAN_DINING("japan_dining", "일본 다이닝");

    private final String code;
    private final String description;

    public static CpServiceGroup of(String code) {
        for (CpServiceGroup group : values()) {
            if (group.code.equals(code)) {
                return group;
            }
        }

        return null;
    }

    /** 코드에 대응하는 표시명. 정의에 없는 코드는 코드값을 그대로 돌려준다. */
    public static String descriptionOf(String code) {
        CpServiceGroup group = of(code);

        return group != null ? group.description : code;
    }
}
