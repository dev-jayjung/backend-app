package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.worker.WorkerAccessibleDeviceService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.worker.WorkerAccessibleDeviceResult;
import com.thelounge.web.admin.workermanagement.dto.WorkerAccessibleDeviceDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worker-management/worker-accessible-device")
public class WorkerAccessibleDeviceViewController {

    private final WorkerAccessibleDeviceService workerAccessibleDeviceService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String workerAccessibleDeviceList(Model model) {
        addTitle(model);

        return "worker-management/worker-accessible-device-list";
    }

    /** 등록 폼. uid 가 null 인 빈 DTO 를 넘겨 템플릿이 "등록 모드"로 렌더링하게 한다. */
    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("accessibleDevice", new WorkerAccessibleDeviceDetailResponse());

        return "worker-management/worker-accessible-device-detail";
    }

    /** 수정 폼. */
    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        WorkerAccessibleDeviceResult result = workerAccessibleDeviceService.get(id);
        model.addAttribute("accessibleDevice",
                modelMapperHelper.map(result, WorkerAccessibleDeviceDetailResponse.class));

        return "worker-management/worker-accessible-device-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "작업자 허용 기기");
    }
}
