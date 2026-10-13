package com.thelounge.web.admin.auth.controller;

import java.time.Duration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.auth.AuthService;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.auth.LoginResult;
import com.thelounge.core.model.auth.TokenResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.helper.CookieHelper;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.auth.dto.LoginReqeust;
import com.thelounge.web.admin.auth.dto.TokenResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthRestController {

    private final AuthService authService;
    private final ModelMapperHelper modelMapperHelper;
    private final CookieHelper cookieHelper;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@RequestBody LoginReqeust req) {
        LoginResult result = authService.login(req.getWorkerId(), req.getPassword());
        TokenResult tokenResult = result.getTokenResult();
        Duration expireDuration = Duration.ofSeconds(tokenResult.getExpiresIn());

        cookieHelper.add("accessToken", tokenResult.getAccessToken(), expireDuration);
        cookieHelper.add("sessionId", result.getSessionId(), expireDuration);

        return ResponseUtil.success(modelMapperHelper.map(tokenResult, TokenResponse.class));
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout() {
        cookieHelper.get("sessionId").ifPresent(cookie -> {
            authService.logout(cookie.getValue());
            cookieHelper.delete("sessionId");
        });

        cookieHelper.delete("accessToken");

        return ResponseUtil.success();
    }
}