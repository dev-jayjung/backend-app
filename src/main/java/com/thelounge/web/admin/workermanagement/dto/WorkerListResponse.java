package com.thelounge.web.admin.workermanagement.dto;

import com.thelounge.core.common.dto.BaseResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkerListResponse extends BaseResult {

    private Integer uid;
    private String workerId;
    private String workerName;
    private String phoneNum;
    private String emailAddress;
    private String defaultMenuCode;
    private String menuAuth;
    private String serviceOperator;
    private String allowOnlyOneLogin;
    private String saleChannel;
    private String memo;
}
