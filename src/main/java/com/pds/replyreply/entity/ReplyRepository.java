package com.pds.replyreply.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select distinct r from Reply r left outer join fetch r.child where r.board.boardId = :boardId")
    List<Reply> findAllByBoardBoardId(@Param("boardId") Long boardId);

}
