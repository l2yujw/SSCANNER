package com.sscanner.team.board.repository;

import com.sscanner.team.board.entity.Board;
import com.sscanner.team.board.type.ApprovalStatus;
import com.sscanner.team.board.type.BoardCategory;
import com.sscanner.team.trashcan.type.TrashCategory;
import com.sscanner.team.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select b from Board b where b.boardCategory = :boardCategory and b.trashCategory = :trashCategory")
    Page<Board> findAllByCategories(@Param("boardCategory") BoardCategory boardCategory,
                                                       @Param("trashCategory")TrashCategory trashCategory,
                                                       Pageable pageable);

    @Query("select b from Board b where b.approvalStatus = :approvalStatus and b.boardCategory = :boardCategory and b.trashCategory = :trashCategory")
    Page<Board> findAllByStatusAndCategories(@Param("approvalStatus") ApprovalStatus approvalStatus,
                                             @Param("boardCategory") BoardCategory boardCategory,
                                             @Param("trashCategory") TrashCategory trashCategory,
                                             Pageable pageable);

    @Query("select b from Board b where b.user = :user")
    List<Board> findAllByUser(@Param("user") User user);

    @Query("select b from Board b join fetch b.user where b.id = :id")
    Optional<Board> findBoardByIdFetchUser(@Param("id") Long id);
}
