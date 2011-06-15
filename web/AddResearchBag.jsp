<%@page import="Connections.UserManager"%>
<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>
<%@page import="UserBeans.Auth"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="ResearchPoints.ResearchBag" id="researchBag" scope="session" />

<%
    Auth auth = (Auth) session.getAttribute(ConnectionSingleton.Auth);

    researchBag.initialize(new String(auth.getUsername()));
    
    session.setAttribute(ConnectionSingleton.researchBag, researchBag);
    UserManager.addResearchBag(researchBag);

    AuthorizationSingleton.goToWelcomePage(response);
%>