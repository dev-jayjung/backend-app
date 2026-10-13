package com.thelounge.web.admin.push.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.push.PushTargetTemplateService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.push.PushTargetTemplateQuery;
import com.thelounge.core.model.push.PushTargetTemplateCommand;
import com.thelounge.core.model.push.PushTargetTemplateResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/push-target-templates")
@RequiredArgsConstructor
public class PushTargetTemplateRestController {
    private final PushTargetTemplateService pushTargetTemplateService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PushTargetTemplateResult>>> getList(
            PushTargetTemplateQuery query) {
        return ResponseUtil.success(pushTargetTemplateService.getPage(query));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ApiResponse<PushTargetTemplateResult>> getDetail(@PathVariable Integer uid) {
        return ResponseUtil.success(pushTargetTemplateService.get(uid));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PushTargetTemplateResult>> create(@RequestBody PushTargetTemplateCommand command) {
        return ResponseUtil.success(pushTargetTemplateService.create(command));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<PushTargetTemplateResult>> update(
            @PathVariable Integer uid, @RequestBody PushTargetTemplateCommand command) {
        return ResponseUtil.success(pushTargetTemplateService.updateDetail(uid, command));
    }

    @PostMapping("/{uid}/target-count")
    public ResponseEntity<ApiResponse<Integer>> refreshTargetCount(@PathVariable int uid) {
        return ResponseUtil.success(pushTargetTemplateService.refreshTargetCount(uid));
    }

    @PostMapping("/target-counts")
    public ResponseEntity<ApiResponse<Integer>> refreshAllTargetCounts() {
        return ResponseUtil.success(pushTargetTemplateService.refreshAllTargetCounts());
    }
}
