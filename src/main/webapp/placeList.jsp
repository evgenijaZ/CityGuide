<%@ page import="edu.kpi.jee.labs.entities.Place" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Places</title>
</head>
<body>
<%
    List <Place> places = (List <Place>) request.getAttribute("places");
    if (places != null) {
%>
<ul>
    <%
        for (Place place : places) {
    %>
    <li><%= place.getId() %>
    </li>
    <li><%= place.getName() %>
    </li>
    <li><%= place.getAddress() %>
    </li>
    <%
            }
        }
    %>
</ul>
<a href="/index">BACK</a>
</body>
</html>
