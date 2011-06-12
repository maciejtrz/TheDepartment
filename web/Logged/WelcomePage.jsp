<%@page import="ConnectionDataBase.DepartmentinfoHelper"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="Connections.AuthorizationSingleton"%>
<%@page import="Connections.ConnectionSingleton"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h1><h:outputText value="#{auth.username}" /></h1>

            <h:form>
                <h:outputText value="Department Name:" />
                <h:inputText value="#{addDepartment.name}" />
                <h:commandButton value="Add Department" action="#{addDepartment.addDepartment}"/>
            </h:form>

        </body>
    </html>
    </f:view>