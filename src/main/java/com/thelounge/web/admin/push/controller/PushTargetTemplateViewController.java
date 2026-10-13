package com.thelounge.web.admin.push.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thelounge.core.admin.push.PushTargetTemplateService;
import com.thelounge.core.model.push.PushTargetTemplateResult;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/push/push-target-template")
@RequiredArgsConstructor
public class PushTargetTemplateViewController {
    private final PushTargetTemplateService pushTargetTemplateService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "푸시 대상 템플릿");
        return "push/push-target-template-list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addDetailModel(model, new PushTargetTemplateResult());
        return "push/push-target-template-detail";
    }

    @GetMapping("/detail")
    public String detailForm(@RequestParam Integer id, Model model) {
        addDetailModel(model, pushTargetTemplateService.get(id));
        return "push/push-target-template-detail";
    }

    @GetMapping("/select")
    public String select() {
        return "push/push-target-template-select";
    }

    private void addDetailModel(Model model, PushTargetTemplateResult template) {
        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", template.getUid() == null ? "푸시 대상 템플릿 등록" : "푸시 대상 템플릿 수정");
        model.addAttribute("template", template);
    }
}
