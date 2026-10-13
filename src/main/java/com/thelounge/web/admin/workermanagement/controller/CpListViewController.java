package com.thelounge.web.admin.workermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.cp.CpService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.cp.CpResult;
import com.thelounge.web.admin.workermanagement.dto.CodeOption;
import com.thelounge.web.admin.workermanagement.dto.CpDetailResponse;
import com.thelounge.web.admin.workermanagement.type.CpServiceGroup;
import com.thelounge.web.admin.workermanagement.type.WorkerServiceStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/worker-management/cp-list")
public class CpListViewController {

    /** 계정 구분 : 본사 */
    private static final String SUPERVISOR_BASE = "1";

    /** 계정 구분 : 일반 */
    private static final String SUPERVISOR_NORMAL = "0";

    private final CpService cpService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String cpList(Model model) {
        addTitle(model, "CP 관리자");
        addCodeOptions(model);

        return "worker-management/cp-list";
    }

    /** 일반 계정 등록 폼. */
    @GetMapping("/new")
    public String createNormalForm(Model model) {
        addTitle(model, "CP 일반 계정 등록");

        return createForm(model, SUPERVISOR_NORMAL);
    }

    /** 본사 계정 등록 폼. */
    @GetMapping("/new-base")
    public String createBaseForm(Model model) {
        addTitle(model, "CP 본사 계정 등록");

        return createForm(model, SUPERVISOR_BASE);
    }

    /** 수정 폼. 계정 구분은 저장된 값을 그대로 쓴다. */
    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        addTitle(model, "CP 관리자 상세");

        CpResult result = cpService.get(id);
        model.addAttribute("cp", modelMapperHelper.map(result, CpDetailResponse.class));
        addCodeOptions(model);

        return "worker-management/cp-form";
    }

    /** 등록 폼. uid 가 null 인 빈 DTO 를 넘겨 템플릿이 "등록 모드"로 렌더링하게 한다. */
    private String createForm(Model model, String supervisor) {
        // 아래 두 값은 화면이 알 수 없어 서버가 정한다. 나머지 항목의 기본값은 템플릿이 정한다.
        CpDetailResponse cp = new CpDetailResponse();
        cp.setSupervisor(supervisor);

        // 매장 선택 기능이 아직 없다. 매장 선택이 생기면 일반 계정은 선택한 매장 uid 로 바뀐다.
        cp.setLoungeUid(0L);

        model.addAttribute("cp", cp);
        addCodeOptions(model);

        return "worker-management/cp-form";
    }

    /**
     * 제공 서비스 목록은 실제 데이터의 DISTINCT 값을 쓴다.
     * 표시명은 DB 에 없으므로 {@link CpServiceGroup} 으로 채운다.
     */
    private void addCodeOptions(Model model) {
        model.addAttribute("cpServiceGroups", cpService.getCpGroups().stream()
                .map(cpGroup -> new CodeOption(cpGroup, CpServiceGroup.descriptionOf(cpGroup)))
                .toList());
        model.addAttribute("cpServiceStatuses", WorkerServiceStatus.values());
    }

    private void addTitle(Model model, String subTitle) {
        model.addAttribute("mainTitle", "작업자관리");
        model.addAttribute("subTitle", subTitle);
    }
}
