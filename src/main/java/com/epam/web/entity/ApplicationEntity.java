package com.epam.web.entity;

import java.sql.Date;


public class ApplicationEntity extends Entity {
	private int applicationId;
	private int statusId;
	private int applicantId;
	private int typeId;
	private String photo;
	private String name;
	private String surname;
	private String traits;
	private int weight;
	private int height;
	private String description;
	private int reward;
	private Date expirationDate;
	public ApplicationEntity() {

	}

	public ApplicationEntity(int applicationId, int statusId, int applicantId, 
		    int typeId, String photo, 
			String name, String surname, String traits, int weight, int height, String description, 
			int reward, Date expirationDate) {
		this.applicationId = applicationId;
		this.statusId = statusId;
		this.applicantId = applicantId;
		this.typeId = typeId;
		this.photo = photo;
		this.name = name;
		this.surname = surname;
		this.traits = traits;
		this.weight = weight;
		this.height = height;
		this.description = description;
		this.reward = reward;
		this.expirationDate = expirationDate;
	}

	public int getApplicationId() {
		return applicationId;
	}
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public int getApplicantId() {
		return applicantId;
	}
	
	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}
	
	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTraits() {
		return traits;
	}
	public void setTraits(String traits) {
		this.traits = traits;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicantId;
		result = prime * result + applicationId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + height;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + reward;
		result = prime * result + statusId;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((traits == null) ? 0 : traits.hashCode());
		result = prime * result + typeId;
		result = prime * result + weight;
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
		ApplicationEntity other = (ApplicationEntity) obj;
		if (applicantId != other.applicantId)
			return false;
		if (applicationId != other.applicationId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (height != other.height)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!photo.equals(other.photo))
			return false;
		if (reward != other.reward)
			return false;
		if (statusId != other.statusId)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (traits == null) {
			if (other.traits != null)
				return false;
		} else if (!traits.equals(other.traits))
			return false;
		if (typeId != other.typeId)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicationEntity [applicationId=");
		builder.append(applicationId);
		builder.append(", statusId=");
		builder.append(statusId);
		builder.append(", applicantId=");
		builder.append(applicantId);
		builder.append(", typeId=");
		builder.append(typeId);
		builder.append(", photo=");
		builder.append(photo);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", traits=");
		builder.append(traits);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", height=");
		builder.append(height);
		builder.append(", description=");
		builder.append(description);
		builder.append(", reward=");
		builder.append(reward);
		builder.append(", expirationDate=");
		builder.append(expirationDate);
		builder.append("]");
		return builder.toString();
	}

	
public static class ApplicationBuilder{
	ApplicationEntity application = new ApplicationEntity();
	
	public ApplicationBuilder() {		
	}
	
	public ApplicationBuilder setApplicationId(int id){
		application.setApplicationId(id);
		return this;
	}
	
	public ApplicationBuilder setStatusId(int id){
		application.setStatusId(id);
		return this;
	}
	
	public ApplicationBuilder setApplicantId(int id){
		application.setApplicantId(id);
		return this;
	}
	
	public ApplicationBuilder setTypeId(int id){
		application.setTypeId(id);
		return this;
	}
	
	public ApplicationBuilder setPhoto(String photo){
		application.setPhoto(photo);
		return this;
	}
	
	public ApplicationBuilder setName (String name){
		application.setName(name);
		return this;
	}
	
	public ApplicationBuilder setSurname (String surname){
		application.setSurname(surname);
		return this;
	}
	
	public ApplicationBuilder setTraits (String traits){
		application.setTraits(traits);
		return this;
	}
	
	public ApplicationBuilder setWeight (int weihgt){
		application.setWeight(weihgt);
		return this;
	}
	
	public ApplicationBuilder setHeight (int height){
		application.setHeight(height);
		return this;
	}
	
	public ApplicationBuilder setDescription (String description){
		application.setDescription(description);
		return this;
	}
	
	public ApplicationBuilder setReward (int reward){
		application.setReward(reward);
		return this;
	}
	
	public ApplicationBuilder setExpirationDate (Date expirationDate){
		application.setExpirationDate(expirationDate);
		return this;
	}
	
	public ApplicationEntity build() {
		return application;
	}
}
	
	
	
	
}
