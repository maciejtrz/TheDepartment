<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
            if (AuthorizationSingleton.isFacesContext()) {
                AuthorizationSingleton.goToIndexPage(response);
            } else if (AuthorizationSingleton.isSessionValid(session)) {
               AuthorizationSingleton.goToWelcomePage(response);
            } else {
                AuthorizationSingleton.checkCookies(request, response, session);
%>

<f:view>
    <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>Adding New User</title>
        </head>

        <body>
            <h1>Insert your name and password: </h1>

            <h:form>
                <h:panelGrid id="lpg" columns="2">
                    <h:outputText value="Username: "/>
                    <h:inputText id="username"
                                 value="#{addUser.id}"/>

                    <h:outputText value="Password: "/>
                    <h:inputSecret id="password"
                                   value="#{addUser.password}" />

                    <h:commandButton actionListener="#{addUser.insert}"
                                     value = "Add User"/>

                    <h:commandButton actionListener="#{addUser.logging}"
                                     value = "Logging"/>

                </h:panelGrid>
            </h:form>
        </body>
    </html>
</f:view>

<% }%>
