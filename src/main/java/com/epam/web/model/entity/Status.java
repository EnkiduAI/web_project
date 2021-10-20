package com.epam.web.model.entity;

public class Status extends Entity{
	private int statusId;
	private String status;
	
	public Status() {
	
	}
		
	public Status(int statusId, String status) {
		this.statusId = statusId;
		this.status = status;
	}
	
	public void setStatusId(int id) {
		this.statusId = id;
	}

	public int getStatusId() {
		return statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + statusId;
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
		Status other = (Status) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatusEntity [statusId=");
		builder.append(statusId);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	public static class StatusBuilder{
		Status status = new Status();
		public StatusBuilder() {
			
		}
		
		public StatusBuilder setStatusId(int id) {
			status.setStatusId(id);
			return this;
		}
		
		public StatusBuilder setStatus(String status) {
			this.status.setStatus(status);
			return this;
		}
		
		public Status build() {
			return status;
		}
	}
	
}
