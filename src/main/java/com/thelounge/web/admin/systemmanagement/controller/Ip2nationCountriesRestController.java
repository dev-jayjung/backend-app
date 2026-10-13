package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.Ip2nationCountriesService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.Ip2nationCountriesQuery;
import com.thelounge.core.model.system.Ip2nationCountriesResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.systemmanagement.dto.Ip2nationCountriesListRequest;
import com.thelounge.web.admin.systemmanagement.dto.Ip2nationCountriesListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ip2nation-countries")
@RequiredArgsConstructor
public class Ip2nationCountriesRestController {

    private final Ip2nationCountriesService ip2nationCountriesService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<Ip2nationCountriesListResponse>>> getIp2nationCountriesList(
            Ip2nationCountriesListRequest request) {
        Ip2nationCountriesQuery query = modelMapperHelper.map(request, Ip2nationCountriesQuery.class);
        PageResponse<Ip2nationCountriesResult> page = ip2nationCountriesService.getPage(query);

        return ResponseUtil.success(page.map(modelMapperHelper.map(Ip2nationCountriesListResponse.class)));
    }
}
