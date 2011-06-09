<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="UserBeans.Shop"%>
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
            <title>The Department: Buy Stuff</title>
        </head>
        <body>
<h1 ALIGN="CENTER">Buy some staff and hope you don't waste your money</h1>

<p align="center"> Your balance is <h:outputText value="#{shop.balance}"/> </p>

            <h:form>
                <p align="center">
                <h:outputText value="Student (£5/unit):" />
                <h:inputText value="#{shop.students}" />
                <br>
                <h:outputText value="PhD (£20/unit):" />
                <h:inputText value="#{shop.phds}" />
                <br>
                <h:commandButton value="Submit" action="#{shop.submit}"/>


                <br>
                <a href="/TheDepartment/faces/Logged/Playing.xhtml">Go Back to Playing</a>
                </p>
            </h:form>

        </body>
    </html>
</f:view>