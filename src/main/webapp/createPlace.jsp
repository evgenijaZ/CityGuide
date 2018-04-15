<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Place</title>
</head>
<body>
<%
    int id = Integer.parseInt(request.getParameter("new_id"));
    Boolean result = (Boolean) request.getAttribute("result");
    if (result) {
%>
<h5>
    Place was successfully created with id <%=id%>.
</h5>
<%
} else {%>
<h5>Cannot create place.</h5>
<%
    }
%>
<a href="/index">BACK</a>
</body>
</html>
