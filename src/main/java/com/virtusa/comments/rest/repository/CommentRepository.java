package com.virtusa.comments.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.comments.entities.Comment;
public interface CommentRepository extends JpaRepository<Comment, Long> {
	@Query(value = "select * from Comment c where c.topic= :topicId", nativeQuery = true)
	public List<Comment> findAllByTopic(@Param("topicId") Long topicId);
}
