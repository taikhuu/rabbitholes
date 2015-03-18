<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <center>
    <b>Login</b>
    <div>
        <form:form method="post" action="/login" modelAttribute="user">
            <table>
            <tr>
                <td>Username:</td>
                <td><form:input path="username" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Login" /></td>
            </tr>
            <tr>
                <td>Don''t have account?<a href="/register">Register</a></td>
                <td>Forgot password?<a href="/forgot">Click here</a></td>
            </tr>
            </table>
        </form:form>
    </div>
    </center>
</body>
</html>