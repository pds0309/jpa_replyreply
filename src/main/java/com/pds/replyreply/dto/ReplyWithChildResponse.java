package com.pds.replyreply.dto;

import com.azul.crs.com.fasterxml.jackson.annotation.JsonInclude;
import com.pds.replyreply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyWithChildResponse {

    private Long id;
    private String comment;
    private List<ReplyWithChildResponse> childReplyList = new ArrayList<>();

    public static Set<ReplyWithChildResponse> ofSet(List<Reply> replyList, int maxDepth, int currentDepth) {
        return replyList.stream()
                .map(reply -> toDTO(reply, maxDepth, currentDepth))
                .collect(Collectors.toSet());
    }

    private static ReplyWithChildResponse toDTO(Reply reply, int maxDepth, int currentDepth) {

        if (currentDepth == 0 && reply.getDepth() != 0) {
            return null;
        }

        ReplyWithChildResponseBuilder builder = ReplyWithChildResponse.builder()
                .id(reply.getReplyId())
                .comment(reply.getComment());

        if (reply.getDepth() != maxDepth && !reply.getChild().isEmpty()) {
            builder.childReplyList(
                    new ArrayList<>(ofSet(reply.getChild(),
                            maxDepth, currentDepth + 1)));
        }

        return builder.build();
    }

}
