package com.thelounge.web.admin.push.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thelounge.core.model.push.LoungePushType;

@Controller
@RequestMapping("/push/lounge-push")
public class LoungePushViewController {

    @GetMapping
    public String list(Model model) {
        Map<String, String> typeLabels = new LinkedHashMap<>();
        for (LoungePushType type : LoungePushType.values()) {
            typeLabels.put(type.getCode(), type.getDescription());
        }

        model.addAttribute("mainTitle", "푸시");
        model.addAttribute("subTitle", "라운지 푸시 목록");
        model.addAttribute("loungePushTypes", LoungePushType.values());
        model.addAttribute("typeLabels", typeLabels);

        return "push/lounge-push-list";
    }
}
