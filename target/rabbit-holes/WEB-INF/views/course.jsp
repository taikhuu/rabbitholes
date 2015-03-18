<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contact Manager Home</title>
</head>
<body>
    <div align="center">
        <h1>Courses</h1>
        <table border="1">
            <th>No</th>
            <th>Name</th>
            <th>Email</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Action</th>

            <c:forEach var="course" items="${listCourse}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${contact.name}</td>
                <td>${contact.email}</td>
                <td>${contact.address}</td>
                <td>${contact.telephone}</td>
                <td>
                    <a href="editContact?id=${contact.id}">View</a>
                </td>

            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
