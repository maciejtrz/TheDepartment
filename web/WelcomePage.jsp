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

<%
            System.out.println("Welcome in welcomePage");

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
            <title>JSP Page</title>
        </head>
        <body>
            <h1>Hello <%=session.getAttribute(ConnectionSingleton.idname)%>!</h1>

            <h:form>
                <h:commandButton value="Get me out of here" action="#{unlog.logoff}"/>
                <h:commandButton value="Delete me" action="#{user.deleteMe}"/>
                <%
                    String playerName
                        = session.getAttribute(ConnectionSingleton.idname)
                        .toString();
                    ConnectionSingleton connection =
			ConnectionSingleton.createConnection();
                    Statement statement = connection.getStatement();
                    String query = "SELECT * FROM DepartmentInfo WHERE IdName = '"
                            + playerName + "'";

                    /* *********************************** */
                    System.out.println("IM HERE!");
                    ResultSet result = statement.executeQuery(query);
                    boolean b = result.next();
                    System.out.println("IM HERE2!");
                    System.out.println("The value for " + playerName);
                    System.out.println(b);

                    /* ********************************** */

                    if (!b) {
                        /* The entry does not exist in the database. */
                 %>

                 <h:commandButton value="Add Department" action="#{addDepartment.go}" />
                 <%
                    } else {

                 %>
                 <h:commandButton value="Start playing" action="#{department.startPlaying}" />
                 <h:commandButton value="Delete Department" actionListener="#{department.deleteDepartment}" />

                 <% } %>
            </h:form>

        </body>
    </html>
</f:view>

    <% } %>