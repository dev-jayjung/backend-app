package com.thelounge.web.admin.common.advice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.thelounge.core.common.type.EditableStatus;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.type.ServiceCode;

import lombok.RequiredArgsConstructor;

@ControllerAdvice(annotations = Controller.class)
@RequiredArgsConstructor
public class GlobalCodeAdvice {

    // private final CommonCodeService commonCodeService;

    @ModelAttribute
    public void addCommonCodes(Model model) {

        model.addAttribute("ServiceStatus", ServiceStatus.values());
        model.addAttribute("EditableStatus", EditableStatus.values());
        model.addAttribute("ServiceCode", ServiceCode.values());

        // TODO 추후 공통코드 추가시 아래와 같이 작업 필요
        // model.addAttribute("usrStatList", commonCodeService.getCodeList("USR_STAT"));
        // model.addAttribute("usrGrpList", commonCodeService.getCodeList("USR_GRP"));
        // model.addAttribute("usrStatMap", commonCodeService.getCodeMap("USR_STAT"));
        // model.addAttribute("usrGrpMap", commonCodeService.getCodeMap("USR_GRP"));
        // model.addAttribute("usrGbMap", commonCodeService.getCodeMap("USR_GB"));
    }
}