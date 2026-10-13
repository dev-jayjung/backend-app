package com.thelounge.web.admin.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.thelounge.core.admin.admin.AdminMenuAccessHistoryService;
import com.thelounge.core.model.admin.AdminMenuAccessHistoryCommand;
import com.thelounge.core.web.util.RequestUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuAccessLogInterceptor implements HandlerInterceptor {

    private final AdminMenuAccessHistoryService adminMenuAccessHistoryService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Controller 메서드 매핑 요청이 아닌 경우(정적 리소스 등) 패스
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        // 클래스 타입 조회
        Class<?> beanType = handlerMethod.getBeanType();

        boolean isController = beanType.isAnnotationPresent(Controller.class);

        if (isController) {
            String bestMatchingPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
            String requestUri = (bestMatchingPattern != null) ? bestMatchingPattern : request.getRequestURI();
            String method = request.getMethod();
            String clientIp = RequestUtil.getClientIp(request);
            String workerId = SecurityUtil.getWorkerId();

            log.info("[Menu Access Log] URI: {}, Method: {}, Controller: {}, IP: {}",
                    requestUri, method, beanType.getSimpleName(), clientIp);

            try {
                AdminMenuAccessHistoryCommand command = AdminMenuAccessHistoryCommand.builder()
                        .workerId(workerId)
                        .url(requestUri)
                        .ipAddress(clientIp)
                        .build();

                adminMenuAccessHistoryService.save(command);
            } catch (Exception e) {
                // 이력 저장 실패에도 화면 접근하도록 로그만 출력
                log.error("Failed to save menu access log", e);
            }
        }

        return true;
    }
}