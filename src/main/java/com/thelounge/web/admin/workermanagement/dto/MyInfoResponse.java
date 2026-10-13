package com.thelounge.web.admin.workermanagement.dto;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class MyInfoResponse {
 private Integer uid; private String workerId; private String workerName; private String emailAddress; private String phoneNum;
 private String serviceOperator; private Integer workerMaxIssueCount; private String saleChannel; private String autoLogin; private String defaultMenuCode;
}
