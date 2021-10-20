package com.epam.web.model.entity;

public class User extends Entity{
	private int userId;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	private String phone;
	
	public User(int userId, String name, String surname, String login, String password, String email,
			String phone) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

	public User() {
		
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int id) {
		this.userId = id;
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
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserEntity [userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
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
	
	public static class UserBuilder{
		User user = new User();
		
		public UserBuilder() {
			
		}
		
		public UserBuilder setUserId(int id) {
			user.setUserId(id);
			return this;
		}
		
		public UserBuilder setUserName(String name) {
			user.setName(name);
			return this;
		}
		
		public UserBuilder setSurname(String surname) {
			user.setSurname(surname);
			return this;
		}
		
		public UserBuilder setLogin(String login) {
			user.setLogin(login);
			return this;
		}
		
		public UserBuilder setPassword(String password) {
			user.setPassword(password);
			return this;
		}
		
		public UserBuilder setEmail (String email) {
			user.setEmail(email);
			return this;
		}
		
		public UserBuilder setPhone (String phone) {
			user.setPhone(phone);
			return this;
		}
		
		public User build() {
			return user;
		}
	
	}
}
