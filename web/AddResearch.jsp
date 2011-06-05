<%@page import="Connections.AuthorizationSingleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
             if (AuthorizationSingleton.isFacesContext() ||
                    !AuthorizationSingleton.isSessionValid(session)) {
               AuthorizationSingleton.goToIndexPage(response);
            } else {
               AuthorizationSingleton.addCookies(response, session);

%>
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Research Management</title>
    </head>
    <body>
        <h1>Adding research: </h1>
        <p>Now, you must specify what research you would like to perform.</p>
        <h:form>

            <h:outputText value="Specify subject: "/>
            <h:selectOneMenu value="#{researchSubject.nextSWVersion}" >
   <f:selectItems value="#{researchSubject.nextSWVersionItems}"/>
</h:selectOneMenu>


        </h:form>
    </body>
</html>
</f:view>
<% } %>
