package com.thelounge.web.admin.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.api.ApiMemberService;
import com.thelounge.core.admin.api.ApiMenuGroupService;
import com.thelounge.core.admin.api.ApiMenuService;
import com.thelounge.core.common.helper.JsonMapperHelper;
import com.thelounge.core.common.helper.ModelMapperHelper;
import com.thelounge.core.common.type.ServiceStatus;
import com.thelounge.core.model.api.ApiMemberResult;
import com.thelounge.core.model.api.ApiMenuGroupQuery;
import com.thelounge.core.model.api.ApiMenuGroupResult;
import com.thelounge.core.model.api.ApiMenuQuery;
import com.thelounge.core.model.api.ApiMenuResult;
import com.thelounge.web.admin.api.dto.ApiMemberResponse;
import com.thelounge.web.admin.api.dto.ApiMenuGroupResponse;
import com.thelounge.web.admin.api.dto.ApiMenuResponse;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;

@Controller
@RequestMapping("/api-members")
@RequiredArgsConstructor
public class ApiMemberViewController {

    private final ApiMemberService apiMemberService;
    private final ApiMenuGroupService apiMenuGroupService;
    private final ApiMenuService apiMenuService;
    private final ModelMapperHelper modelMapperHelper;
    private final JsonMapperHelper jsonMapperHelper;

    @GetMapping
    public String apiMemberList(Model model) {
        List<ApiMenuGroupResult> apiMenuGroupList = apiMenuGroupService.getList(new ApiMenuGroupQuery());
        model.addAttribute("apiMenuGroupList", modelMapperHelper.mapList(apiMenuGroupList, ApiMenuGroupResponse.class));

        ApiMenuQuery apiMenuQuery = ApiMenuQuery.builder()
                .onService(ServiceStatus.IN_SERVICE)
                .build();

        List<ApiMenuResult> apiMenuList = apiMenuService.getList(apiMenuQuery);
        model.addAttribute("apiMenuList", modelMapperHelper.mapList(apiMenuList, ApiMenuResponse.class));

        return "api/member_list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        ApiMemberResponse member = new ApiMemberResponse();

        member.setAllowMsgNotify("1");
        member.setOnService(ServiceStatus.STOPPED);
        member.setIsEncrypted((short) 0);

        model.addAttribute("member", member);

        List<ApiMenuGroupResult> apiMenuGroupList = apiMenuGroupService.getList(new ApiMenuGroupQuery());
        model.addAttribute("apiMenuGroupList", modelMapperHelper.mapList(apiMenuGroupList, ApiMenuGroupResponse.class));

        return "api/member_form";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Long id, Model model) {
        ApiMemberResult member = apiMemberService.get(id);

        if (member != null) {
            model.addAttribute("member", modelMapperHelper.map(member, ApiMemberResponse.class));

            if (member.getEncData() != null) {
                Map<String, String> map = jsonMapperHelper.readValue(member.getEncData(),
                        new TypeReference<Map<String, String>>() {
                        });

                model.addAttribute("encInfo", map.get("enc_info"));
                model.addAttribute("encKey", map.get("enc_key"));
                model.addAttribute("encIv", map.get("enc_iv"));
            }
        }

        List<ApiMenuGroupResult> apiMenuGroupList = apiMenuGroupService.getList(new ApiMenuGroupQuery());
        model.addAttribute("apiMenuGroupList", modelMapperHelper.mapList(apiMenuGroupList, ApiMenuGroupResponse.class));

        return "api/member_form";
    }
}