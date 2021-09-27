package com.epam.web.entity;

public class ApplicationTypeEntity extends Entity{
private int id;
private String type;
public ApplicationTypeEntity() {
	
}
public ApplicationTypeEntity(int id, String type) {
	this.type = type;
	this.id = id;
}

public void setId(int id) {
	this.id = id;
}

public int getId() {
	return id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}



@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	result = prime * result + ((type == null) ? 0 : type.hashCode());
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
	ApplicationTypeEntity other = (ApplicationTypeEntity) obj;
	if (id != other.id)
		return false;
	if (type == null) {
		if (other.type != null)
			return false;
	} else if (!type.equals(other.type))
		return false;
	return true;
}
@Override
public String toString() {
	StringBuilder sb = new StringBuilder("Application Type: ");
	sb.append("id = ").append(id);
	sb.append(", type = ").append(type);
	return sb.toString();
}


public static class TypeBuilder{
	ApplicationTypeEntity type = new ApplicationTypeEntity();
	public TypeBuilder() {
		
	}
	
	public TypeBuilder setId (int id){
		type.setId(id);
		return this;
	}
	
	public TypeBuilder setType(String type){
		this.type.setType(type);
		return this;
	}
	
	public ApplicationTypeEntity build() {
		return type;
	}
}

}
