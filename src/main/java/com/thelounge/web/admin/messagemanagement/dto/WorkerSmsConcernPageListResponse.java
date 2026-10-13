package com.thelounge.web.admin.messagemanagement.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.worker.WorkerSmsConcernPageReceiverResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkerSmsConcernPageListResponse {
    private Long uid;
    private String code;
    private String category;
    private String title;
    private String sourcePosition;
    private ServiceStatus onService;
    private String memo;
    private OffsetDateTime regDate;
    private OffsetDateTime updateDate;
    private List<WorkerSmsConcernPageReceiverResult> receivers;
}
