package com.virtusa.comments.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.comments.entities.CommentUpvote;
public interface CommentUpvoteRepository extends JpaRepository<CommentUpvote, Long> {
	@Query(value = "select * from CommentUpvote c where c.comment_Id= :commentId and c.user_Id= :userId", nativeQuery = true)
	public CommentUpvote findByCommentIdUserId(@Param("commentId") Long commentId, @Param("userId") Long userId); 
}
