package com.epam.web.entity;

public class ApplicationTypesEntity {
private int id;
private String type;
public ApplicationTypesEntity() {
	
}
public ApplicationTypesEntity(int id, String type) {
	this.type = type;
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
public String toString() {
	return "[id=" + id + ", type=" + type + "]";
}



}
