<%@page import="Connections.AuthorizationSingleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
            System.out.println("Welcome in welcomePage");

             if (AuthorizationSingleton.isFacesContext() ||
                    !AuthorizationSingleton.isSessionValid(session)) {
               AuthorizationSingleton.goToIndexPage(response);
            } else {
               AuthorizationSingleton.addCookies(response, session);

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Playing</title>
    </head>
    <body>
        <h1>Playing...</h1>
    </body>
</html>

<% } %>
