package com.thelounge.web.admin.common.util;

import java.util.regex.Pattern;

public final class MaskUtil {

    private static final String SYSTEM_ACCOUNT_PREFIX = "id_";
    private static final int CREDENTIAL_VISIBLE_LENGTH = 3;
    private static final Pattern KOREAN_MOBILE_PHONE = Pattern.compile("^01[016789]-?[0-9]{3,4}-?[0-9]{4}$");

    private MaskUtil() {
    }

    // 계정 ID
    public static String accountId(String accountId) {
        if (accountId == null || accountId.isEmpty()) {
            return accountId;
        }

        if (accountId.contains("@@")) {
            return maskEmailNormal(accountId);
        }

        if (accountId.contains("@")) {
            return maskEmailKt(accountId);
        }

        if (accountId.startsWith(SYSTEM_ACCOUNT_PREFIX)) {
            return accountId;
        }

        return maskMiddleCodePoint(accountId);
    }

    // 사용자 이름
    public static String userName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return userName;
        }

        int length = userName.codePointCount(0, userName.length());
        int firstEnd = userName.offsetByCodePoints(0, 1);
        int suffixStart = userName.offsetByCodePoints(0, Math.min(2, length));

        return userName.substring(0, firstEnd) + "*" + userName.substring(suffixStart);
    }

    // 이메일
    public static String emailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            return emailAddress;
        }

        return maskEmailKt(emailAddress);
    }

    // 폰번호
    public static String phoneNum(String phoneNum) {
        if (phoneNum == null || phoneNum.isEmpty()) {
            return phoneNum;
        }

        String trimmed = phoneNum.trim();

        if (KOREAN_MOBILE_PHONE.matcher(trimmed).matches()) {
            if (phoneNum.contains("-")) {
                String[] parts = phoneNum.split("-", -1);
                return parts[0] + "-" + "*".repeat(parts[1].length()) + "-" + parts[2];
            }

            String head = phoneNum.substring(0, 3);
            String tail = phoneNum.substring(phoneNum.length() - 4);
            return head + "-" + "*".repeat(phoneNum.length() - 7) + "-" + tail;
        }

        if (phoneNum.startsWith("+")) {
            String[] parts = phoneNum.split(" ", -1);
            if (parts.length >= 2) {
                String target = parts[parts.length - 2];
                return target.isEmpty() ? phoneNum : phoneNum.replace(target, "*".repeat(target.length()));
            }
        }

        return replaceFixedSegment(phoneNum, phoneNum.length() >= 6 ? 3 : 2,
                phoneNum.length() >= 6 ? 3 : 2);
    }

    public static String credential(String credential) {
        if (credential == null || credential.length() <= CREDENTIAL_VISIBLE_LENGTH) {
            return credential;
        }

        return maskTail(credential, CREDENTIAL_VISIBLE_LENGTH);
    }

    private static String maskTail(String value, int keepLength) {
        return value.substring(0, keepLength) + "*".repeat(value.length() - keepLength);
    }

    private static String maskEmailKt(String value) {
        int atIndex = value.indexOf('@');
        String local = atIndex >= 0 ? value.substring(0, atIndex) : value;
        String domain = atIndex >= 0 ? value.substring(atIndex) : "";
        int length = local.codePointCount(0, local.length());

        if (length <= 3) {
            return "***" + domain;
        }

        int keepEnd = local.offsetByCodePoints(0, length - 3);
        return local.substring(0, keepEnd) + "***" + domain;
    }

    private static String maskEmailNormal(String value) {
        int atIndex = value.indexOf('@');
        String local = atIndex >= 0 ? value.substring(0, atIndex) : value;
        String domain = atIndex >= 0 ? value.substring(atIndex) : "";
        int length = local.codePointCount(0, local.length());

        if (length <= 1) {
            return "*" + domain;
        }

        int keepEnd = local.offsetByCodePoints(0, length - 2);
        String masked = local.substring(0, keepEnd) + "**";
        return (length >= 6 ? replaceCodePoint(masked, 1) : masked) + domain;
    }

    private static String maskMiddleCodePoint(String value) {
        int length = value.codePointCount(0, value.length());
        int index = length == 2 ? 1 : (length - 1) / 2;
        return replaceCodePoint(value, index);
    }

    private static String replaceCodePoint(String value, int index) {
        int start = value.offsetByCodePoints(0, index);
        int end = value.offsetByCodePoints(start, 1);
        return value.substring(0, start) + "*" + value.substring(end);
    }

    private static String replaceFixedSegment(String value, int offset, int count) {
        int start = Math.min(offset, value.length());
        int end = Math.min(start + count, value.length());
        return value.substring(0, start) + "*".repeat(count) + value.substring(end);
    }
}
