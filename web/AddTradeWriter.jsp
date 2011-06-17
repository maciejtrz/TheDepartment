
<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>
<%@page import="messageSystem.MessageSingleton" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean class="messageSystem.TradeMessageWriter" id="tradeMessageWriter" scope="session" />

<%
    tradeMessageWriter.initialize(session.getAttribute(ConnectionSingleton.idname).toString(), MessageSingleton.TRADE_OFFER);
    session.setAttribute(ConnectionSingleton.tradeMessageWriter, tradeMessageWriter);

    AuthorizationSingleton.goToWelcomePage(response);
%>