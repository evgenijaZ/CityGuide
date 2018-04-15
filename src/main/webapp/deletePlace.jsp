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
   Place with id <%=id%> was successfully deleted
</h5>
<%
    }else {%>
<h5>Cannot delete place. Please check id: <%=id%> </h5>
<%
    }
%>
<a href="/index">BACK</a>
</body>
</html>
