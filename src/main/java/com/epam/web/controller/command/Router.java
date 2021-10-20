package com.epam.web.controller.command;

public class Router {
public enum RouteType{
	FORWARD, REDIRECT
}
private String path;
private RouteType route = RouteType.FORWARD;

public Router(String pagepath, RouteType routerType) {
	this.path = pagepath;
	this.route = routerType;
}

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
