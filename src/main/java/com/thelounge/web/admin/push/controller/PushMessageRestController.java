package com.thelounge.web.admin.push.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thelounge.core.admin.push.PushMessageService;
import com.thelounge.core.common.dto.PageResponse;
import com.thelounge.core.model.push.PushMessageQuery;
import com.thelounge.core.model.push.PushMessageCommand;
import com.thelounge.core.model.push.PushMessageResult;
import com.thelounge.core.model.push.PushMessageReceiverQuery;
import com.thelounge.core.model.push.PushMessageReceiverResult;
import com.thelounge.core.model.push.PushMessageReceiverAddRequest;
import com.thelounge.core.model.push.PushMessageReceiverAddResult;
import com.thelounge.core.model.push.PushMessageReceiverGroupAddRequest;
import com.thelounge.core.web.dto.ApiResponse;
import com.thelounge.core.web.util.ResponseUtil;
import com.thelounge.web.admin.common.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/push-messages")
@RequiredArgsConstructor
public class PushMessageRestController {

    private final PushMessageService pushMessageService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PushMessageResult>>> getPushMessageList(
            PushMessageQuery query) {
        return ResponseUtil.success(pushMessageService.getPage(query));
    }

    @PostMapping("/{uid}/copies")
    public ResponseEntity<ApiResponse<Integer>> copy(@PathVariable int uid) {
        return ResponseUtil.success(pushMessageService.copy(uid, SecurityUtil.getWorkerId()));
    }

    @GetMapping("/{uid}")
    public ResponseEntity<ApiResponse<PushMessageResult>> getDetail(@PathVariable Integer uid) {
        return ResponseUtil.success(pushMessageService.get(uid));
    }

    @GetMapping("/{messageUid}/receivers")
    public ResponseEntity<ApiResponse<PageResponse<PushMessageReceiverResult>>> getMessageReceivers(
            @PathVariable Integer messageUid, PushMessageReceiverQuery query) {
        return ResponseUtil.success(pushMessageService.getMessageReceiverPage(messageUid, query));
    }

    @PostMapping("/{messageUid}/receivers")
    public ResponseEntity<ApiResponse<PushMessageReceiverAddResult>> addMessageReceiver(
            @PathVariable Integer messageUid, @RequestBody PushMessageReceiverAddRequest request) {
        return ResponseUtil.success(pushMessageService.addMessageReceiver(messageUid, request));
    }

    @PostMapping("/{messageUid}/receivers/groups")
    public ResponseEntity<ApiResponse<Integer>> addMessageReceiversByGroup(@PathVariable Integer messageUid,
            @RequestBody PushMessageReceiverGroupAddRequest request) {
        return ResponseUtil.success(pushMessageService.addMessageReceiversByGroup(messageUid, request));
    }

    @DeleteMapping("/{messageUid}/receivers/{messageReceiverUid}")
    public ResponseEntity<ApiResponse<Void>> deleteMessageReceiver(@PathVariable Integer messageUid,
            @PathVariable Integer messageReceiverUid) {
        pushMessageService.deleteMessageReceiver(messageUid, messageReceiverUid);
        return ResponseUtil.success();
    }

    @DeleteMapping("/{messageUid}/receivers")
    public ResponseEntity<ApiResponse<Void>> deleteMessageReceivers(@PathVariable Integer messageUid) {
        pushMessageService.deleteMessageReceivers(messageUid);
        return ResponseUtil.success();
    }

    @PostMapping("/{messageUid}/receivers/{messageReceiverUid}/dispatches")
    public ResponseEntity<ApiResponse<Void>> dispatchMessageReceiver(@PathVariable Integer messageUid,
            @PathVariable Integer messageReceiverUid) {
        pushMessageService.dispatchMessageReceiver(messageUid, messageReceiverUid);
        return ResponseUtil.success();
    }

    @PostMapping("/{messageUid}/dispatches")
    public ResponseEntity<ApiResponse<Integer>> dispatchMessageReceivers(@PathVariable Integer messageUid,
            @RequestParam(defaultValue = "0") int sendLimit) {
        return ResponseUtil.success(pushMessageService.dispatchMessageReceivers(messageUid, sendLimit));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PushMessageResult>> create(@RequestBody PushMessageCommand detail) {
        return ResponseUtil.success(pushMessageService.create(detail, SecurityUtil.getWorkerId()));
    }

    @PutMapping("/{uid}")
    public ResponseEntity<ApiResponse<PushMessageResult>> update(
            @PathVariable Integer uid,
            @RequestBody PushMessageCommand detail) {
        return ResponseUtil.success(pushMessageService.updateDetail(uid, detail));
    }
}
