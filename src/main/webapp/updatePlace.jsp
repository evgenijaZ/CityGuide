<%@ page import="edu.kpi.jee.labs.entities.Place" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("place_id"));
    Boolean result =(Boolean)request.getAttribute("result");
    if (result) {
%>
<h5>
   Place was successfully updated by id <%=id%>.
</h5>
<%
    }else {%>
<h5>Cannot update place.</h5>
<%
    }
%>
<a href="/index">BACK</a>
</body>
</html>
