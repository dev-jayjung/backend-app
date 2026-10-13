package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.store.StoreAccessibleIpService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.store.StoreAccessibleIpResult;
import com.thelounge.web.admin.workermanagement.dto.StoreAccessibleIpDetailResponse;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worker-management/store-accessible-ip")
public class StoreAccessibleIpViewController {

    private final StoreAccessibleIpService storeAccessibleIpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String storeAccessibleIpList(Model model) {
        addTitle(model);

        return "worker-management/store-accessible-ip-list";
    }

    /** 등록 폼. uid 가 null 인 빈 DTO 를 넘겨 템플릿이 "등록 모드"로 렌더링하게 한다. */
    @GetMapping("/new")
    public String createForm(Model model) {
        addTitle(model);

        model.addAttribute("accessibleIp", new StoreAccessibleIpDetailResponse());

        return "worker-management/store-accessible-ip-detail";
    }

    /** 수정 폼. */
    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model);

        StoreAccessibleIpResult result = storeAccessibleIpService.get(id);
        model.addAttribute("accessibleIp",
                modelMapperHelper.map(result, StoreAccessibleIpDetailResponse.class));

        return "worker-management/store-accessible-ip-detail";
    }

    private void addTitle(Model model) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", "기업 회원 허용IP");
    }
}
