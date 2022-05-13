package com.pds.replyreply;

import com.pds.replyreply.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ReplyreplyApplication {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @PostConstruct
    @Transactional
    void setup() {
        Member member = Member.builder().name("김갑환").build();
        Board board = Board.builder().member(member).title("김갑환이 쓴 글").build();
        Reply motherReply = Reply.builder().board(board).comment("엄마1").build();
        Reply childReply1 = Reply.builder().parent(motherReply).board(board).comment("엄마1의 자식1").build();
        Reply childReply2 = Reply.builder().parent(motherReply).board(board).comment("엄마1의 자식2").build();
        Reply childChildReply1 = Reply.builder().parent(childReply1).board(board).comment("자식1의 자식1").build();
        Reply childChildChildReply1 = Reply.builder().parent(childChildReply1).board(board).comment("자식1의 자식1의 자식1").build();
        Reply motherReply2 = Reply.builder().board(board).comment("엄마2").build();
        Reply motherReply3 = Reply.builder().board(board).comment("엄마3").build();
        Reply childReply3 = Reply.builder().parent(motherReply2).board(board).comment("엄마2의 자식1").build();
        memberRepository.save(member);
        log.info("[SAMPLE MEMBER_ID = '{}']",member.getMemberId());
        boardRepository.save(board);
        log.info("[SAMPLE BOARD_ID = '{}']",board.getBoardId());
        replyRepository.save(motherReply);
        replyRepository.save(childReply1);
        replyRepository.save(childReply2);
        replyRepository.save(childChildReply1);
        replyRepository.save(motherReply2);
        replyRepository.save(motherReply3);
        replyRepository.save(childReply3);
        replyRepository.save(childChildChildReply1);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReplyreplyApplication.class, args);
    }

}
