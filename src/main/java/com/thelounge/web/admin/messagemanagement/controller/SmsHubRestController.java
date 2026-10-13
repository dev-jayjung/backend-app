package com.thelounge.web.admin.messagemanagement.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thelounge.core.admin.smshub.SmsHubResultCode;
import com.thelounge.core.admin.smshub.SmsHubSendReportService;
import com.thelounge.core.admin.smshub.SmsHubStatisticsService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.SmsHubReportFamily;
import com.thelounge.core.model.smshub.SmsHubSendReportQuery;
import com.thelounge.core.model.smshub.SmsHubSendReportResult;
import com.thelounge.core.model.smshub.SmsHubStatisticsMonthQuery;
import com.thelounge.core.model.smshub.SmsHubStatisticsMonthResult;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.messagemanagement.dto.SmsHubResultSummaryResponse;
import com.thelounge.web.admin.messagemanagement.dto.SmsHubSendReportListRequest;
import com.thelounge.web.admin.messagemanagement.dto.SmsHubSendReportListResponse;
import com.thelounge.web.admin.messagemanagement.dto.SmsHubStatisticsListRequest;
import com.thelounge.web.admin.messagemanagement.dto.SmsHubStatisticsListResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/sms-hub")
@RequiredArgsConstructor
public class SmsHubRestController {

    private static final List<Integer> SUMMARY_RESULT_CODES = List.of(0, 2, 3, 6, 38);

    private final SmsHubStatisticsService smsHubStatisticsService;
    private final SmsHubSendReportService smsHubSendReportService;
    private final ModelMapperHelper modelMapperHelper;

    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<PageResponse<SmsHubStatisticsListResponse>>> getStatisticsList(
            SmsHubStatisticsListRequest request,
            @RequestParam(defaultValue = "false") boolean recalc) {
        SmsHubStatisticsMonthQuery query = modelMapperHelper.map(request, SmsHubStatisticsMonthQuery.class);
        PageResponse<SmsHubStatisticsMonthResult> page = smsHubStatisticsService.getPageWithRecalculate(query, recalc);

        Map<String, String> tenantNames = smsHubStatisticsService.getTenantNames();

        return ResponseUtil.success(page.map(result -> toStatisticsResponse(result, tenantNames)));
    }

    @GetMapping("/send-reports")
    public ResponseEntity<ApiResponse<PageResponse<SmsHubSendReportListResponse>>> getSendReportList(
            SmsHubSendReportListRequest request) {
        SmsHubSendReportQuery query = modelMapperHelper.map(request, SmsHubSendReportQuery.class);
        SmsHubReportFamily family = SmsHubReportFamily.of(request.getReportType());

        PageResponse<SmsHubSendReportResult> page = smsHubSendReportService.getPage(query);

        return ResponseUtil.success(page.map(result -> toSendReportResponse(result, family)));
    }

    @GetMapping("/send-reports/summary")
    public ResponseEntity<ApiResponse<List<SmsHubResultSummaryResponse>>> getSendReportSummary(
            SmsHubSendReportListRequest request) {
        SmsHubSendReportQuery query = modelMapperHelper.map(request, SmsHubSendReportQuery.class);
        query.setResult(null);
        query.setResultEtc(null);
        query.setSearchTarget(null);
        query.setSearchValue(null);
        Map<Integer, Long> counts = smsHubSendReportService.getResultSummary(query);

        List<SmsHubResultSummaryResponse> summary = Stream.concat(
                SUMMARY_RESULT_CODES.stream()
                        .map(result -> new SmsHubResultSummaryResponse(String.valueOf(result),
                                SmsHubResultCode.descriptionOf(result), counts.getOrDefault(result, 0L))),
                Stream.of(new SmsHubResultSummaryResponse("etc", "기타 실패",
                        counts.entrySet().stream()
                                .filter(entry -> entry.getKey() != null
                                        && !SUMMARY_RESULT_CODES.contains(entry.getKey()))
                                .mapToLong(Map.Entry::getValue)
                                .sum())))
                .toList();

        return ResponseUtil.success(summary);
    }

    @GetMapping("/send-reports/months")
    public ResponseEntity<ApiResponse<List<String>>> getSendMonths(@RequestParam(defaultValue = "SMS") String reportType) {
        return ResponseUtil.success(smsHubSendReportService.getSendMonths(reportType));
    }

    private SmsHubStatisticsListResponse toStatisticsResponse(SmsHubStatisticsMonthResult result,
            Map<String, String> tenantNames) {
        SmsHubStatisticsListResponse response = modelMapperHelper.map(result, SmsHubStatisticsListResponse.class);

        response.setUserName(tenantNames.get(result.getUserId()));

        return response;
    }

    private SmsHubSendReportListResponse toSendReportResponse(SmsHubSendReportResult result,
            SmsHubReportFamily family) {
        SmsHubSendReportListResponse response = modelMapperHelper.map(result, SmsHubSendReportListResponse.class);

        response.setResultName(SmsHubResultCode.descriptionOf(result.getResult()));
        response.setResultSuccess(SmsHubResultCode.isSuccess(result.getResult()));
        response.setMsgKindName(family.displayKind(result.getReserved5()));

        return response;
    }
}
