package com.epam.web.model.entity;

public class Update extends Entity{
	private int messageId;
	private int userId;
	private int applicationId;
	private String message;
	
	public Update() {
		
	}
	
	public Update(int messageId, int userId, int applicationId, String message) {
		this.messageId = messageId;
		this.userId = userId;
		this.applicationId = applicationId;
		this.message = message;
	}


	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int id) {
		this.messageId = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicationId;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + messageId;
		result = prime * result + userId;
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
		Update other = (Update) obj;
		if (applicationId != other.applicationId)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (messageId != other.messageId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UpdateEntity [messageId=");
		builder.append(messageId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", applicationId=");
		builder.append(applicationId);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	public static class UpdateBuilder {
		Update update = new Update();
		
		public UpdateBuilder() {
			
		}
		
		public UpdateBuilder setMessageId(int id) {
			update.setMessageId(id);
			return this;
		}
		
		public UpdateBuilder setUserId (int id) {
			update.setUserId(id);
			return this;
		}
		
		public UpdateBuilder setApplicationId(int id) {
			update.setApplicationId(id);
			return this;
		}
		
		public UpdateBuilder setMessage(String message) {
			update.setMessage(message);
			return this;
		}
		
		public Update build() {
			return update;
		}
	}

}
