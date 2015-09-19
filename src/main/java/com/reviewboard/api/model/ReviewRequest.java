package com.reviewboard.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.reviewboard.util.DateUtil;

public class ReviewRequest {
	
	private String summary;
	private Long id;
	private Date timeAdded;
	private String status;
	private String branch;
	private String description;
	
	private String submitter;
	
	private List<String> bugsClosed=new ArrayList<String>();
	private List<String> targetPeople=new ArrayList<String>();

	private List<String> targetGroups=new ArrayList<String>();
	private Date lastUpdated;

	private List<ReviewResource> reviewResouces;
	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimeAdded() {
		return timeAdded;
	}

	public void setTimeAdded(Date timeAdded) {
		this.timeAdded = timeAdded;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	public String toString(){
		return "[ID:"+getId()+"] "+getSummary()+" - Status ["+getStatus()+"]  - Date Added :"+(DateUtil.toString(getTimeAdded()));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ReviewResource> getReviewResouces() {
		return reviewResouces;
	}

	public void setReviewResouces(List<ReviewResource> reviewResouces) {
		this.reviewResouces = reviewResouces;
	}

	

	public List<String> getBugsClosed() {
		return bugsClosed;
	}

	public void setBugsClosed(List<String> bugsClosed) {
		this.bugsClosed = bugsClosed;
	}

	public List<String> getTargetGroups() {
		return targetGroups;
	}

	public void setTargetGroups(List<String> targetGroups) {
		this.targetGroups = targetGroups;
	}

	public List<String> getTargetPeople() {
		return targetPeople;
	}

	public void setTargetPeople(List<String> targetPeople) {
		this.targetPeople = targetPeople;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
		
}
