package com.thelounge.web.admin.workermanagement.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.thelounge.core.admin.worker.WorkerHistoryService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.worker.*;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/worker-histories")
@RequiredArgsConstructor
public class WorkerHistoryRestController {
    private final WorkerHistoryService service;

    @GetMapping public ResponseEntity<ApiResponse<PageResponse<WorkerHistoryResult>>> list(WorkerHistoryQuery q){
        return ResponseUtil.success(service.getPage(q));
    }
}
