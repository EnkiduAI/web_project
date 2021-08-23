package com.epam.web.entity;

import java.sql.Date;
import java.util.Arrays;

public class ApplicationEntity extends Entity{
	private int applicationId;
	private String applicationStatus;
	private String organizationName;
	private String applicationType;
	//private int statusId;
	//private int applicantId;
	//private int typeId;
	private byte[] photo;
	private String name;
	private String surname;
	private String traits;
	private int weight;
	private int height;
	private String description;
	private int reward;
	private Date expirationDate;
	private ApplicationTypeEntity type;
	private StatusEntity status;
	private ApplicantEntity applicant;
	public ApplicationEntity() {

	}

	public ApplicationEntity(int applicationId, String applicationStatus, String organizationName, 
		    String applicationType, byte[] photo, 
			String name, String surname, String traits, int weight, int height, String description, 
			int reward, Date expirationDate) {
		this.applicationId = applicationId;
		this.applicationStatus = applicationStatus;
		this.organizationName = organizationName;
		this.applicationType = applicationType;
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
	
	/*public int getStatusId() {
		return statusId;
	}*/
	/*public void setStatusId(int statusId) {
		this.statusId = statusId;
	}*/
	/*public int getApplicantId() {
		return applicantId;
	}*/
	
	/*public int getTypeId() {
		return typeId;
	}*/
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
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

	/*public void setTypeId(int typeId) {
		this.typeId = typeId;
	}*/

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + applicationId;
		result = prime * result + ((applicationStatus == null) ? 0 : applicationStatus.hashCode());
		result = prime * result + ((applicationType == null) ? 0 : applicationType.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + height;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + reward;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((traits == null) ? 0 : traits.hashCode());
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
		if (applicationId != other.applicationId)
			return false;
		if (applicationStatus == null) {
			if (other.applicationStatus != null)
				return false;
		} else if (!applicationStatus.equals(other.applicationStatus))
			return false;
		if (applicationType == null) {
			if (other.applicationType != null)
				return false;
		} else if (!applicationType.equals(other.applicationType))
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
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (reward != other.reward)
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
		if (weight != other.weight)
			return false;
		return true;
	}

	
	
	
	
}
