package com.virtusa.comments.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="commentupvote")
public class CommentUpvote {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long commentId;
	private Long userId;
	private Boolean upVotedFlag = false;
	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getUpVotedFlag() {
		return upVotedFlag;
	}

	public void setUpVotedFlag(Boolean upVotedFlag) {
		this.upVotedFlag = upVotedFlag;
	}

	    
}
