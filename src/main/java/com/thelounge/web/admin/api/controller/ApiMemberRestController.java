package com.thelounge.web.admin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.api.ApiMemberService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.api.ApiMemberCommand;
import com.thelounge.core.model.api.ApiMemberQuery;
import com.thelounge.core.model.api.ApiMemberResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.api.dto.ApiMemberCreateReqeust;
import com.thelounge.web.admin.api.dto.ApiMemberResponse;
import com.thelounge.web.admin.api.dto.ApiMemberSearchReqeust;
import com.thelounge.web.admin.api.dto.ApiMemberUpdateReqeust;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/api-members")
@RequiredArgsConstructor
public class ApiMemberRestController {

    private final ApiMemberService apiMemberService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ApiMemberResponse>>> getApiMemberList(
            ApiMemberSearchReqeust request) {
        ApiMemberQuery query = modelMapperHelper.map(request, ApiMemberQuery.class);

        PageResponse<ApiMemberResult> page = apiMemberService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(ApiMemberResponse.class)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ApiMemberResult>> createApiMember(@RequestBody ApiMemberCreateReqeust request) {
        ApiMemberCommand command = modelMapperHelper.map(request, ApiMemberCommand.class);

        ApiMemberResult res = apiMemberService.save(command);

        return ResponseUtil.success(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateApiMember(@PathVariable Long id,
            @RequestBody ApiMemberUpdateReqeust request) {
        ApiMemberCommand command = modelMapperHelper.map(request, ApiMemberCommand.class);

        apiMemberService.update(id, command);

        return ResponseUtil.success();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteApiMember(@PathVariable Long id) {
        apiMemberService.delete(id);

        return ResponseUtil.success();
    }
}