package org.omoknoone.onionhotsayyo.comment.command.repository;

import java.util.List;

import org.omoknoone.onionhotsayyo.comment.command.aggregate.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findAllByMemberId(String memberId);
}