package com.pds.replyreply.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Reply {

    @Id
    @GeneratedValue
    @Column(name = "reply_id")
    private Long replyId;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Reply parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Reply> child = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private int depth;

    @Builder
    public Reply(Long replyId, String comment, Reply parent, List<Reply> child, Board board, int depth) {
        this.replyId = replyId;
        this.comment = comment;
        this.parent = parent;
        this.child = child;
        this.board = board;
        this.depth = getDepthFromParent(parent);
    }

    private int getDepthFromParent(Reply parent) {
        return parent == null ? 0 : parent.depth + 1;
    }

}
