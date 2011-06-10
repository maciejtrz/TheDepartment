<%@page import="utilities.Lecturer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="utilities.LecturersManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="UserBeans.BuyLecturers"%>
<%@page import="UserBeans.AddBuildings"%>
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
            <title>Buy lecturers</title>
        </head>
        <body>
<h1 ALIGN="CENTER">Buy lecturers</h1>
<%!
String printList() {
       String list;

        LecturersManager mgr 
                = new LecturersManager(utilities.BasicUtils.getUserName());

        ArrayList<Lecturer> available = mgr.getAvailabeLecturers();

        Iterator<Lecturer> it = available.iterator();
        list = "AVAILABLE \n";
        if (!it.hasNext()) {
            list += " empty ";
        }
        else {
            while (it.hasNext()) {
               Lecturer lec = it.next();
               list = list + lec.getName() + " ";
            }
        }

        ArrayList<Lecturer> owned = mgr.getOwnedLecturers();

        it = owned.iterator();
        list = list + "OWNED ";
        if (!it.hasNext()) {
            list += " empty ";
        }
        else {
               while (it.hasNext()) {
               Lecturer lec = it.next();
               list = list + lec.getName() + " ";
              }
        }

       return list;
    }

%>

            <h:form>
                <p align="center">
                <h:outputText value="Type lecturers name" />
                <h:inputText value="#{lec.lecturer}" />
                <br>
                <h:commandButton value="Submit" action="#{lec.buy}"/>
                <h:commandButton value="TEST BUILDINGS" action="#{addBuildings.test}"/>

                <br>
                <a href="/TheDepartment/faces/Logged/Playing.xhtml">Go Back to Playing</a>
                </p>
            </h:form>

            <%= printList() %>

        </body>
    </html>
</f:view>
