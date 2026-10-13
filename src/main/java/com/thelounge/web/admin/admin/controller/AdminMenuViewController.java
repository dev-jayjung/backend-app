package com.thelounge.web.admin.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.admin.AdminMenuService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.admin.AdminMenuQuery;
import com.thelounge.core.model.admin.AdminMenuResult;
import com.thelounge.web.admin.admin.dto.AdminMenuResponse;
import com.thelounge.web.admin.common.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

/**
 * ADMIN 메뉴 관리 컨트롤러
 */
@Controller
@RequestMapping("/admin-menus")
@RequiredArgsConstructor
public class AdminMenuViewController {

    private final AdminMenuService adminMenuService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public String getMenuList(@RequestParam(defaultValue = "ALL") String bookmark, Model model) {

        AdminMenuQuery query = AdminMenuQuery.builder()
                .workerId(SecurityUtil.getWorkerId())
                .build();

        boolean isBookmarkTab = "BOOKMARK".equals(bookmark);

        List<AdminMenuResult> results = isBookmarkTab
                ? adminMenuService.getBookmarkList(query)
                : adminMenuService.getMenuList(query);

        model.addAttribute("menuList", modelMapperHelper.mapList(results, AdminMenuResponse.class));
        model.addAttribute("isBookmarkTab", isBookmarkTab);

        return "fragments/left_menu :: leftMenu";
    }
}
