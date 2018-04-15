<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<%--<script type="text/javascript" src="/webjars/jquery/3.1.0/jquery.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<script type="text/javascript">
    $(function () {
        $("#select").on('change', function () {
            if ($(this).val() === "getAll") {
                $("#getAllButton").show();
            } else {
                $("#getAllButton").hide();
            }

            if ($(this).val() == "getById") {
                $("#getByIdForm").show();
            } else {
                $("#getByIdForm").hide();
            }
            if ($(this).val() == "getByName") {
                $("#getByNameForm").show();
            } else {
                $("#getByNameForm").hide();
            }

            if ($(this).val() == "create") {
                $("#createForm").show();
            } else {
                $("#createForm").hide();
            }

            if ($(this).val() == "update") {
                $("#updateForm").show();
            } else {
                $("#updateForm").hide();
            }

            if ($(this).val() == "delete") {
                $("#deleteForm").show();
            } else {
                $("#deleteForm").hide();
            }
        })
    })
</script>
<label for="select"><select name="select" id="select">
    <option value="getAll">Get all places</option>
    <option value="getById">Get place by id</option>
    <option value="getByName">Get places by name</option>
    <option value="create">Create place</option>
    <option value="update">Update place</option>
    <option value="delete">Delete place</option>

</select></label>
<button id="getAllButton" onclick="location.href='/placeList'">Get All</button>

<form id="getByIdForm" style="display:none" action="/place" method="get">
    Please enter a place id <br>
        <input type="text" name="place_id" size="20px">
    <input type="submit" value="submit">
</form>


<form id="getByNameForm" style="display:none" action="/search" method="get">
    Please enter a place name <br>
    <label>
        <input type="text" name="place_name" size="20px">
    </label>
    <input type="submit" value="submit">
</form>


<form id="createForm" style="display:none" action="/create" method="post">
    Please enter a place <br>
    Name: <input type="text" name="place_name" size="20px">
    Address: <input type="text" name="place_address" size="20px">
    Latitude: <input type="text" name="place_latitude" size="20px">
    Longitude: <input type="text" name="place_longitude" size="20px">

    <input type="submit" value="submit">
</form>

<form id="updateForm" style="display:none" action="/update" method="post">
    Please enter a place properties <br>
    id: <input type="text" required="required" name="place_id" size="10px">
    Name: <input type="text" name="place_name" size="20px">
    Address: <input type="text" name="place_address" size="20px">
    Latitude: <input type="text" name="place_latitude" size="20px">
    Longitude: <input type="text" name="place_longitude" size="20px">

    <input type="submit" value="submit">
</form>

<form id="deleteForm" style="display:none" action="/delete" method="get">
    Please enter a place id <br>
    <input type="text" name="place_id" size="20px">
    <input type="submit" value="submit">
</form>

</html>
