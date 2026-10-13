package com.thelounge.web.admin.messagemanagement.controller;

import java.time.Year;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.smshub.SmsHubSendReportService;
import com.thelounge.core.admin.smshub.SmsHubStatisticsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SmsHubViewController {

    private static final String OWN_TENANT_ID = "theloungemembers";
    private static final int STATISTICS_FIRST_YEAR = 2016;

    private final SmsHubStatisticsService smsHubStatisticsService;
    private final SmsHubSendReportService smsHubSendReportService;

    @GetMapping("/message-management/sms-hub")
    public String smsHubOwn(@RequestParam(defaultValue = "statistics") String tab,
            @RequestParam(defaultValue = "SMS") String reportType, Model model) {
        addCommon(model, tab, reportType, "문자 발송 정보");

        model.addAttribute("tenantFixed", true);
        model.addAttribute("tenantId", OWN_TENANT_ID);
        model.addAttribute("showMsgKind", true);

        return "message-management/sms-hub";
    }

    @GetMapping("/message-management/sms-hub-tenant")
    public String smsHubTenant(@RequestParam(defaultValue = "statistics") String tab,
            @RequestParam(defaultValue = "SMS") String reportType, Model model) {
        addCommon(model, tab, reportType, "문자 발송 정보(NC)");

        model.addAttribute("tenantFixed", false);
        model.addAttribute("tenantId", "");
        model.addAttribute("showMsgKind", false);

        return "message-management/sms-hub";
    }

    private void addCommon(Model model, String tab, String reportType, String subTitle) {
        model.addAttribute("mainTitle", "시스템관리(메시지)");
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("activeTab", tab);
        model.addAttribute("reportType", reportType);
        model.addAttribute("sendMonthList", smsHubSendReportService.getSendMonths(reportType));
        model.addAttribute("tenantList", smsHubStatisticsService.getTenantOptions());
        model.addAttribute("yearList", statisticsYears());
        model.addAttribute("monthList", IntStream.rangeClosed(1, 12).boxed().toList());
    }

    private List<Integer> statisticsYears() {
        return IntStream.rangeClosed(STATISTICS_FIRST_YEAR, Year.now().getValue())
                .boxed()
                .sorted((left, right) -> right - left)
                .toList();
    }
}
