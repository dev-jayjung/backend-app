package com.thelounge.web.admin.common.advice;

import java.util.List;

import org.apache.commons.lang3.Strings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import com.thelounge.core.admin.admin.AdminMenuService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.admin.AdminMenuQuery;
import com.thelounge.core.model.admin.AdminMenuResult;
import com.thelounge.web.admin.admin.dto.AdminMenuResponse;
import com.thelounge.web.admin.common.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@ControllerAdvice(annotations = Controller.class, basePackages = "com.thelounge.web")
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final AdminMenuService adminMenuService;
    private final ModelMapperHelper modelMapperHelper;

    @ModelAttribute
    public void addMenuAttributes(Model model, Authentication authentication, HttpServletRequest request) {
        String uri = request.getRequestURI();

        if (Strings.CS.equals("/error", uri)) {
            return;
        }

        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);

        if (handler instanceof HandlerMethod handlerMethod) {
            // 컨트롤러 클래스에 @RestController가 붙어있으면 스킵
            if (handlerMethod.getBeanType().isAnnotationPresent(RestController.class)) {
                return;
            }
        }

        // AJAX (application/json) 요청 시 스킵
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (accept != null && accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
            return;
        }

        if (Strings.CS.equals("/main", uri)) {
            // if (authentication != null && authentication.isAuthenticated()) {
            // String workerId = authentication.getName();
            // boolean isAdmin = authentication.getAuthorities().stream()
            // .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

            AdminMenuQuery query = AdminMenuQuery.builder()
                    .workerId(SecurityUtil.getWorkerId())
                    .build();

            // 메뉴 자동 주입
            List<AdminMenuResult> mainMenuList = adminMenuService.getMenuList(query);

            model.addAttribute("menuList", modelMapperHelper.mapList(mainMenuList, AdminMenuResponse.class));
        } else {
            AdminMenuResult result = adminMenuService.getMenuByLinkUrl(uri);
            if (result != null) {
                model.addAttribute("mainTitle", result.getMainTitle());
                model.addAttribute("subTitle", result.getSubTitle());
            }
        }
    }
}