package com.pds.replyreply.service;


import com.pds.replyreply.dto.ReplyResponse;
import com.pds.replyreply.dto.ReplyWithChildResponse;
import com.pds.replyreply.entity.Reply;
import com.pds.replyreply.entity.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public List<ReplyResponse> getList() {
        List<Reply> replyList = replyRepository.findAll();
        return replyList.stream()
                .map(this::toReplyResponse)
                .collect(Collectors.toList());
    }

    public List<ReplyWithChildResponse> getListWithChildByBoardId(Long boardId) {
        List<Reply> replyList = replyRepository.findAllByBoardBoardId(boardId);
        return ReplyWithChildResponse.ofSet(replyList, getReplyListMaxDepth(replyList), 0)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private ReplyResponse toReplyResponse(Reply reply) {
        return new ReplyResponse(reply.getReplyId(), reply.getComment());
    }

    private int getReplyListMaxDepth(List<Reply> replyList) {
        return replyList.stream()
                .max(Comparator.comparing(Reply::getDepth))
                .orElseThrow(() -> new RuntimeException("안녕"))
                .getDepth();
    }

}
