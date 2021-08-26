package com.epam.web.command;

public class Router {
public enum RouteType{
	FORWARD, REDIRECT
}
private String path;
private RouteType route = RouteType.FORWARD;

public String getPath() {
	return path;
}

public void setPagePath(String path) {
	this.path = path;
}

public RouteType getRoute() {
	return route;
}

public void setRoute(RouteType route) {
	if(route != null) {
	this.route = route;
	}
}
}
