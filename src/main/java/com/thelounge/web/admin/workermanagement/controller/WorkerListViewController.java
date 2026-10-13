package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.admin.AdminMenuService;
import com.thelounge.core.admin.worker.WorkerService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.admin.AdminMenuQuery;
import com.thelounge.core.model.worker.WorkerResult;
import com.thelounge.web.admin.workermanagement.dto.WorkerDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worker-management/worker-list")
public class WorkerListViewController {

    private final WorkerService workerService;
    private final AdminMenuService adminMenuService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String workerList(Model model) {
        addTitle(model, "작업자 목록");

        return "worker-management/worker-list";
    }

    /** 등록 폼. uid 가 null 인 빈 DTO 를 넘겨 템플릿이 "등록 모드"로 렌더링하게 한다. */
    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model, "작업자 등록");

        model.addAttribute("worker", new WorkerDetailResponse());
        addAssignedMenuList(model, null);

        return "worker-management/worker-form";
    }

    /** 수정 폼. */
    @GetMapping("/detail")
    public String detailForm(@RequestParam Integer id, Model model) {
        addTitle(model, "작업자 상세");

        WorkerResult result = workerService.get(id);
        model.addAttribute("worker", modelMapperHelper.map(result, WorkerDetailResponse.class));
        addAssignedMenuList(model, result.getWorkerId());

        return "worker-management/worker-form";
    }

    private void addAssignedMenuList(Model model, String workerId) {
        AdminMenuQuery query = AdminMenuQuery.builder()
                .workerId(workerId)
                .build();

        model.addAttribute("defaultMenuList", adminMenuService.getMenuList(query));
    }

    private void addTitle(Model model, String subTitle) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", subTitle);
    }
}
