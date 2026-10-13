package com.thelounge.web.admin.common.helper;

import java.time.OffsetDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Component;

import com.thelounge.core.common.util.DateTimeUtil;

@Component("dateText")
public class DateTextHelper {

    public String of(OffsetDateTime value) {
        return DateTimeUtil.formatDateTime(value.atZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());
    }
}
