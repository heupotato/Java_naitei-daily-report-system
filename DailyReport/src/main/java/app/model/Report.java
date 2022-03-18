package app.model;

import java.util.Date;

public class Report{
	private Integer id; 
	private Integer user_id; 
	private Integer isDraft; 
	private Integer isDeleted; 
	private Date createdAt; 
	private Date approveAt; 
	private Integer isApproved; 
	private Date lastUpdatedAt; 
	private String acture; 
	private String plan; 
	private String issue;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getUser_id() {
		return user_id;
	}
	
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public Integer getIsDraft() {
		return isDraft;
	}
	
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	
	public Integer getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getApproveAt() {
		return approveAt;
	}
	
	public void setApproveAt(Date approveAt) {
		this.approveAt = approveAt;
	}
	
	public Integer getIsApproved() {
		return isApproved;
	}
	
	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}
	
	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	
	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
	
	public String getActure() {
		return acture;
	}
	
	public void setActure(String acture) {
		this.acture = acture;
	}
	
	public String getPlan() {
		return plan;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public String getIssue() {
		return issue;
	}
	
	public void setIssue(String issue) {
		this.issue = issue;
	} 
}