package com.virtusa.comments.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.comments.entities.Comment;
import com.virtusa.comments.entities.CommentUpvote;
import com.virtusa.comments.entities.User;
import com.virtusa.comments.rest.repository.CommentRepository;
import com.virtusa.comments.rest.repository.CommentUpvoteRepository;
import com.virtusa.comments.rest.repository.UserRepository;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

	@Autowired
	private CommentRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CommentUpvoteRepository upVoteRepository; 
	

	private Comment updateCommentModel(Comment comment, Long userId) {
		User user = userRepository.findOne(comment.getUserId());
		comment.setFullname(user.getFullname());
		if (comment.getUserId() == userId) {
			comment.setCreated_by_current_user(true);
		}
		CommentUpvote upVote = upVoteRepository.findByCommentIdUserId(comment.getId(), userId);
		if (upVote != null) {
			comment.setUser_has_upvoted(upVote.getUpVotedFlag());
		}
		return comment;
	}

	@GetMapping(params = "userId")
	public Iterable<Comment> findAll(@RequestParam(value = "userId") Long userId) {
		Iterable<Comment> comments = repository.findAll();
		List<Comment> result = new ArrayList<Comment>();
		for (Comment comment : comments) {
			comment = updateCommentModel(comment, userId);
			result.add(comment);
		}
		return result;
	}
	
	@GetMapping(path = "/topics/{topicId}", params = {"userId", "status"})
	public Iterable<Comment> findAllByUserIdTopicIdStatus(@PathVariable("topicId") Long topicId,
			@RequestParam(value = "userId") Long userId, @RequestParam(value = "status") String status) {
		Iterable<Comment> comments = repository.findAllByUserIdTopicIdStatus(topicId, status);
		List<Comment> result = new ArrayList<Comment>();
		for (Comment comment : comments) {
			comment = updateCommentModel(comment, userId);
			result.add(comment);
		}
		return result;
	}

	@GetMapping(path = "/topics/{topicId}", params = "userId")
	public Iterable<Comment> findAllByUserIdAndTopicId(@PathVariable("topicId") Long topicId,
			@RequestParam(value = "userId") Long userId) {
		Iterable<Comment> comments = repository.findAllByTopic(topicId);
		List<Comment> result = new ArrayList<Comment>();
		for (Comment comment : comments) {
			comment = updateCommentModel(comment, userId);
			result.add(comment);
		}
		return result;
	}

	@GetMapping(path = "/{id}")
	public Comment find(@PathVariable("id") Long id) {
		return repository.findOne(id);
	}
	
	@PostMapping(consumes = "application/json")
	public Comment create(@RequestBody Comment comment) {
		return repository.save(comment);
	}


	@DeleteMapping(path = "/{id}")
	public void delete(@PathVariable("id") Long id) {
		repository.delete(id);
	}

	@PutMapping(path = "/{id}")
	public Comment update(@PathVariable("id") Long id, @RequestBody Comment comment) throws BadHttpRequest {
		if (repository.exists(id)) {
			Comment dbComment = this.find(id);
			if (dbComment != null) {
				comment.setUpvote_count(dbComment.getUpvote_count());
			}
			comment.setId(id);
			return repository.save(comment);
		} else {
			throw new BadHttpRequest();
		}
	}

	@PutMapping(path = "/{commentId}/{userId}/upvote")
	public Comment update(@PathVariable("commentId") Long commentId,
			@PathVariable("userId") Long userId, @RequestBody Comment comment) throws BadHttpRequest {
		CommentUpvote upVote =  upVoteRepository.findByCommentIdUserId(commentId, userId);
		if (upVote != null) {
			upVote.setUpVotedFlag(comment.getUser_has_upvoted());
		} else {
			upVote = new CommentUpvote();
			upVote.setCommentId(commentId);
			upVote.setUserId(userId);
			upVote.setUpVotedFlag(comment.getUser_has_upvoted());
		}
		upVoteRepository.save(upVote);
		Comment _comment = repository.findOne(commentId);

		if (comment.getUser_has_upvoted()) {
			_comment.setUpvote_count(_comment.getUpvote_count() + 1);
		} else {
			_comment.setUpvote_count(_comment.getUpvote_count() - 1);
		}
		return repository.save(_comment);
	}

}
