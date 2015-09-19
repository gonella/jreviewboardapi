package com.reviewboard.api.model;

import java.util.Date;
import java.util.List;

public class ReviewResource {

	private Long id;
	private Date date;
	private String content;
	private boolean shipit;
	
	private List<DiffComment> diffComments;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isShipit() {
		return shipit;
	}
	public void setShipit(boolean shipit) {
		this.shipit = shipit;
	}
	public List<DiffComment> getDiffComments() {
		return diffComments;
	}
	public void setDiffComments(List<DiffComment> diffComments) {
		this.diffComments = diffComments;
	}
}
