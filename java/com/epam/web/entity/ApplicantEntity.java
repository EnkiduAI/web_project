package com.epam.web.entity;

public class ApplicantEntity extends Entity{
	private int id;
	private String organizationName;
	private String login;
	private String password;
	private String email;
	private String phone;
	
	
	public ApplicantEntity() {
		
	}
	
	public ApplicantEntity(int id, String organiationName, String login, String password, String email,
			String phone) {
		this.id = id;
		this.organizationName = organiationName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((organizationName == null) ? 0 : organizationName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		ApplicantEntity other = (ApplicantEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (organizationName == null) {
			if (other.organizationName != null)
				return false;
		} else if (!organizationName.equals(other.organizationName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApplicantEntity [id=");
		builder.append(id);
		builder.append(", organizationName=");
		builder.append(organizationName);
		builder.append(", login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phone=");
		builder.append(phone);
		builder.append("]");
		return builder.toString();
	}
	

	
	public static class ApplicantBuilder{
		
		ApplicantEntity applicant = new ApplicantEntity();
		
		public ApplicantBuilder() {
		}
		
		public ApplicantBuilder setId(int id){
			applicant.setId(id);
			return this;
		}
		
		public ApplicantBuilder setOrganizationName(String name){
			applicant.setOrganizationName(name);
			return this;
		}
		public ApplicantBuilder setLogin(String login){
			applicant.setLogin(login);
			return this;
		}
		public ApplicantBuilder setPassword(String password){
			applicant.setPassword(password);
			return this;
		}
		public ApplicantBuilder setEmail(String email){
			applicant.setEmail(email);
			return this;
		}
		public ApplicantBuilder setPhone(String phone){
			applicant.setPhone(phone);
			return this;
		}
		public ApplicantEntity build() {
			return applicant;
		}
	}
}
 