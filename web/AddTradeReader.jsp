
<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="messageSystem.TradeMessageReader" id="tradeMessageReader" scope="session" />

<%
    tradeMessageReader.initialize(session.getAttribute(ConnectionSingleton.idname).toString());
    session.setAttribute(ConnectionSingleton.tradeMessageReader,tradeMessageReader);

    AuthorizationSingleton.goToWelcomePage(response);
%>