package com.pds.replyreply.controller;

import com.pds.replyreply.dto.ReplyResponse;
import com.pds.replyreply.dto.ReplyWithChildResponse;
import com.pds.replyreply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/normal")
    public ResponseEntity<List<ReplyResponse>> getReplyList() {
        return ResponseEntity.ok(replyService.getList());
    }

    @GetMapping("/reply-with-child/{boardId}")
    public ResponseEntity<List<ReplyWithChildResponse>> getReplyWithChildListByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(replyService.getListWithChildByBoardId(boardId));
    }

}
