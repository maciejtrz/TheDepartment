<%@page import="Connections.AuthorizationSingleton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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


            <h:outputText value="Name of research: "/>
            <h:inputText value="#{addResearch.name}" />
            <br>
            <h:outputText value="Specify subject: "/>
            <h:selectOneMenu value="#{addResearch.subject}" >
                <f:selectItems value="#{addResearch.subjects}"/>
            </h:selectOneMenu>

            <br>
            <h:outputText value="Choose lecturers: "/>
            <h:selectManyListbox value="#{addResearch.lecturers}">
                <f:selectItems value="#{addResearch.lecturerList}"/>
            </h:selectManyListbox>

            <br>

            <h:commandButton action="#{addResearch.startResearch}" value="Add Research"/>

        </h:form>
            
            <a href="/Department/faces/Logged/Playing.xhtml">Go Back to Playing</a>
    </body>
</html>
</f:view>

