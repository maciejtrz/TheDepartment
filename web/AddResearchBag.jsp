<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="ResearchPoints.ResearchBag" id="researchBag" scope="session" />

<%
    session.setAttribute(ConnectionSingleton.researchBag, researchBag);
    researchBag.initialize(session.getAttribute(ConnectionSingleton.idname).toString());

    System.out.println("Creating research bag bean...");
    AuthorizationSingleton.goToWelcomePage(response);
%>