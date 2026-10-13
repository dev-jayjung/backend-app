package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.ServiceDomainUrlService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.ServiceDomainUrlQuery;
import com.thelounge.core.model.system.ServiceDomainUrlResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.systemmanagement.dto.ServiceDomainUrlListRequest;
import com.thelounge.web.admin.systemmanagement.dto.ServiceDomainUrlListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/service-domain-urls")
@RequiredArgsConstructor
public class ServiceDomainUrlRestController {

    private final ServiceDomainUrlService serviceDomainUrlService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ServiceDomainUrlListResponse>>> getServiceDomainUrlList(
            ServiceDomainUrlListRequest request) {
        ServiceDomainUrlQuery query = modelMapperHelper.map(request, ServiceDomainUrlQuery.class);
        PageResponse<ServiceDomainUrlResult> page = serviceDomainUrlService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(ServiceDomainUrlListResponse.class)));
    }
}
