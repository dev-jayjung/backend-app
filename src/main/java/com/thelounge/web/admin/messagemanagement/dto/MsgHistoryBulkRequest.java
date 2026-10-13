package com.thelounge.web.admin.messagemanagement.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgHistoryBulkRequest {

    @NotEmpty(message = "대상을 선택해 주세요.")
    private List<Long> uids;
}
