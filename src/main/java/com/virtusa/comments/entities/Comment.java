package com.virtusa.comments.entities;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTopic() {
		return topic;
	}

	public void setTopic(Long topic) {
		this.topic = topic;
	}

	private String content;
    private Date created = new Date();
    private Date modified = new Date();
    public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	private String status = "waiting";
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "userId")
    private Long userId;
    private Long topic;
    private Long parent;
    private Integer upvote_count = 0;
    @Transient
    private String[] pings;
    @Transient
    private Long creator;
    @Transient
    private String fullname;
    @Transient 
    private String profilePictureUrl;
    @Transient
    private Boolean createdByAdmin = false;
    @Transient
    private Boolean created_by_current_user = false;
    @Transient 
    private Boolean user_has_upvoted = false;
    private Boolean isNew = true;
	public Long getParent() {
		return parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Integer getUpvote_count() {
		return upvote_count;
	}

	public void setUpvote_count(Integer upVoteCount) {
		this.upvote_count = upVoteCount;
	}

	public String[] getPings() {
		return pings;
	}

	public void setPings(String[] pings) {
		this.pings = pings;
	}

	public Long getCreator() {
		return this.userId;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}

	public Boolean getCreatedByAdmin() {
		return createdByAdmin;
	}

	public void setCreatedByAdmin(Boolean createdByAdmin) {
		this.createdByAdmin = createdByAdmin;
	}

	public Boolean getCreated_by_current_user() {
		return created_by_current_user;
	}

	public void setCreated_by_current_user(Boolean createdByCurrentUser) {
		this.created_by_current_user = createdByCurrentUser;
	}

	public Boolean getUser_has_upvoted() {
		return user_has_upvoted;
	}

	public void setUser_has_upvoted(Boolean userHasUpVoted) {
		this.user_has_upvoted = userHasUpVoted;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
    
}
