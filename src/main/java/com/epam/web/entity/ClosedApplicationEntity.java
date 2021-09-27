package com.epam.web.entity;

import java.sql.Date;

public class ClosedApplicationEntity extends Entity{
	private int closedApplicationId;
	private int applicantId;
	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public void setClosedApplicationId(int closedApplicationId) {
		this.closedApplicationId = closedApplicationId;
	}

	private int applicationId;
	private Date date;
	
	public ClosedApplicationEntity() {

	}
	
	public ClosedApplicationEntity(int closedApplicationId, int applicationId, Date date) {
		this.closedApplicationId = closedApplicationId;
		this.applicationId = applicationId;
		this.date = date;
	}

	public int getClosedApplicationId() {
		return closedApplicationId;
	}
	
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicantId;
		result = prime * result + applicationId;
		result = prime * result + closedApplicationId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClosedApplicationEntity other = (ClosedApplicationEntity) obj;
		if (applicantId != other.applicantId)
			return false;
		if (applicationId != other.applicationId)
			return false;
		if (closedApplicationId != other.closedApplicationId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClosedApplicationEntity [closedApplicationId=");
		builder.append(closedApplicationId);
		builder.append(", applicantId=");
		builder.append(applicantId);
		builder.append(", applicationId=");
		builder.append(applicationId);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}


	public static class ClosedApplicationsBuilder{
		ClosedApplicationEntity closedApp = new ClosedApplicationEntity();
		public ClosedApplicationsBuilder() {
			
		}
		public ClosedApplicationsBuilder setClosedApplicationId(int id){
			closedApp.setClosedApplicationId(id);
			return this;
		}
		
		public ClosedApplicationsBuilder setApplicationId(int id){
			closedApp.setApplicationId(id);
			return this;
		}
		
		public ClosedApplicationsBuilder setApplicantId (int id){
			closedApp.setApplicantId(id);
			return this;
		}
		
		public ClosedApplicationsBuilder setDate(Date date){
			closedApp.setDate(date);
			return this;
		}
		
		public ClosedApplicationEntity build() {
			return closedApp;
		}
	}
	

}
