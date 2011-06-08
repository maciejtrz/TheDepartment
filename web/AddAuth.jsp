
<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="UserBeans.Auth" id="auth" scope="session" />

<%
    session.setAttribute(ConnectionSingleton.Auth, auth);
    auth.setUsername(session.getAttribute(ConnectionSingleton.idname).toString());
    auth.updateResearchPoints();

    System.out.println("Creating auth bean... username: " + auth.getUsername());
    AuthorizationSingleton.goToWelcomePage(response);
%>