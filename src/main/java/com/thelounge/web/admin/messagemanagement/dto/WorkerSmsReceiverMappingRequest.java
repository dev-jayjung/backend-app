package com.thelounge.web.admin.messagemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerSmsReceiverMappingRequest {

    @NotBlank(message = "수신자를 선택해 주세요.")
    private String receiverId;
}
