<%@page import="Connections.UserManager"%>
<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>
<%@page import="ResearchPoints.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="ResearchPoints.AddResearch" id="addResearch" scope="session" />

<%
    addResearch.initialize(session);
    session.setAttribute(ConnectionSingleton.addResearch, addResearch);

    AuthorizationSingleton.goToWelcomePage(response);
%>