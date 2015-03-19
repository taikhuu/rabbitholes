<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h2>Welcome to the guest book full of rabbit holes</h2>
<c:choose>
    <c:when test="${isAdmin}">
        <div>
            <c:forEach var="commentItem" items="${commentList}">
                <p>
                    <span>${commentItem.user.firstName}</span> <span>: </span>
                    <span>${commentItem.comment}</span>
                    <a href="${pageContext.request.contextPath}/guestbook/delete?commentId=${commentItem.id}">Delete</a>
                </p>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            <c:forEach var="commentItem" items="${commentList}">
                <p>
                    <span>${commentItem.user.firstName}</span><span>: </span>
                    <span>${commentItem.comment}</span>
                </p>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>

<form:form action="${pageContext.request.contextPath}/guestbook" method="POST" role="form" commandName="comment">
    <input name="comment" type="text">
    </input>
    <form:errors id="comment" path="comment"></form:errors>
    <form:input type="hidden"
    path="user.id"/>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="Submit Comment"></input>
</form:form>
