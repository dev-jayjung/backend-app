package com.thelounge.web.admin.systemmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.system.Ip2nationService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.model.system.Ip2nationQuery;
import com.thelounge.core.model.system.Ip2nationResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.systemmanagement.dto.Ip2nationListRequest;
import com.thelounge.web.admin.systemmanagement.dto.Ip2nationListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ip2nations")
@RequiredArgsConstructor
public class Ip2nationRestController {

    private static final String IP_SEARCH_TARGET = "ip_search";

    private static final long INVALID_IP_NUMBER = -1L;

    private static final int IPV4_OCTET_COUNT = 4;
    private static final int OCTET_MAX = 255;

    private final Ip2nationService ip2nationService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<Ip2nationListResponse>>> getIp2nationList(
            Ip2nationListRequest request) {
        Ip2nationQuery query = modelMapperHelper.map(request, Ip2nationQuery.class);

        if (IP_SEARCH_TARGET.equals(request.getSearchTarget())) {
            query.setIpSearch(toIpNumber(request.getSearchValue()));
        }

        PageResponse<Ip2nationResult> page = ip2nationService.getPage(query);

        return ResponseUtil.success(page.map(this::toListResponse));
    }

    private Ip2nationListResponse toListResponse(Ip2nationResult result) {
        Ip2nationListResponse response = modelMapperHelper.map(result, Ip2nationListResponse.class);

        response.setIpStartText(toIpText(result.getIpStart()));
        response.setIpEndText(toIpText(result.getIpEnd()));

        return response;
    }

    private static Long toIpNumber(String ipAddress) {
        if (ipAddress == null || ipAddress.isBlank()) {
            return INVALID_IP_NUMBER;
        }

        String[] octets = ipAddress.trim().split("[.]");
        if (octets.length != IPV4_OCTET_COUNT) {
            return INVALID_IP_NUMBER;
        }

        long ipNumber = 0;
        for (String octet : octets) {
            int value;
            try {
                value = Integer.parseInt(octet);
            } catch (NumberFormatException e) {
                return INVALID_IP_NUMBER;
            }

            if (value < 0 || value > OCTET_MAX) {
                return INVALID_IP_NUMBER;
            }

            ipNumber = (ipNumber << 8) | value;
        }

        return ipNumber;
    }

    private static String toIpText(long ipNumber) {
        return ((ipNumber >> 24) & 0xFF) + "." + ((ipNumber >> 16) & 0xFF)
                + "." + ((ipNumber >> 8) & 0xFF) + "." + (ipNumber & 0xFF);
    }
}
