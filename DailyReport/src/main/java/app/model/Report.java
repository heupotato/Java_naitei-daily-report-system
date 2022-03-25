package app.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Report{
	private Integer id; 
	private User user; 
	private boolean isDraft; 
	private Integer isDeleted;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt; 
	private Date approveAt; 
	private Integer isApproved; 
	private Date lastUpdatedAt; 
	private String acture; 
	private String plan; 
	private String issue;
	
	public Report(){
		isDraft = false; 
		isDeleted = 0; 
		isApproved = 0; 
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getIsDraft() {
		return isDraft;
	}
	
	public void setIsDraft(boolean isDraft) {
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