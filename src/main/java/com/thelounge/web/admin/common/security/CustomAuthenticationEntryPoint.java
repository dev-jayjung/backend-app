package com.thelounge.web.admin.common.security;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.thelounge.core.common.exception.CommonErrorCode;
import com.thelounge.core.common.helper.JsonMapperHelper;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.helper.CookieHelper;
import com.thelounge.core.common.helper.MessageHelper;
import com.thelounge.core.web.util.ResponseUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final JsonMapperHelper jsonMapperHelper;
    private final MessageHelper messageHelper;
    private final CookieHelper cookieHelper;
    private static final String LOGIN_PAGE_URL = "/login";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {

        cookieHelper.delete("accessToken");
        cookieHelper.delete("sessionId");

        String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);
        String requestedWith = request.getHeader("X-Requested-With");
        String requestURI = request.getRequestURI();

        boolean isApiRequest = requestURI.startsWith("/api/");
        boolean isXmlHttpRequest = "XMLHttpRequest".equals(requestedWith);
        boolean isJsonAccept = acceptHeader != null && acceptHeader.contains(MediaType.APPLICATION_JSON_VALUE);
        // AJAX / REST API 요청인 경우 -> 401 Unauthorized JSON 응답
        if (isApiRequest || isXmlHttpRequest || isJsonAccept) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            ResponseEntity<ApiResponse<Void>> res = ResponseUtil.fail(
                    CommonErrorCode.UNAUTHORIZED,
                    messageHelper.getMessage(CommonErrorCode.UNAUTHORIZED));

            response.getWriter().write(jsonMapperHelper.writeValueAsString(res));

            return;
        }

        response.sendRedirect(LOGIN_PAGE_URL);
    }
}