<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <%-- APPLICATION CONTEXT PATH --%>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <%-- PUBLIC ENTRY POINT PREFIX --%>
    <c:set var="web_entry_point_prefix" value="/web"/>
    <%-- INITIAL APP PATH --%>
    <c:set var="app_entry_path" value="${app}${web_entry_point_prefix}"/>
    <jsp:include page="/WEB-INF/pages/util/head.jsp"/>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <form:form action="${app_entry_path}/register" modelAttribute="user" method="post">
                <form:errors path="email" cssClass="alert-danger" element="div"/>
                <div class="form-group">
                    <form:label path="email">Email address</form:label>
                    <form:input type="email" path="email" class="form-control" id="email"
                                aria-describedby="emailHelp"
                                placeholder="your@mail.com"/>
                </div>
                <form:errors path="password" cssClass="alert-danger" element="div"/>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input type="password" path="password" class="form-control" id="password"
                                placeholder="********"/>
                </div>
                <form:errors path="firstName" cssClass="alert-danger" element="div"/>
                <div class="form-group">
                    <form:label path="firstName">First name</form:label>
                    <form:input type="text" path="firstName" class="form-control"
                                id="firstName"
                                placeholder="John"/>
                </div>
                <form:errors path="lastName" cssClass="alert-danger" element="div"/>
                <div class="form-group">
                    <form:label path="lastName">Last name</form:label>
                    <form:input type="text" path="lastName" class="form-control" id="lastName"
                                placeholder="Doe"/>
                </div>
                <form:errors path="profile.address" cssClass="alert-danger" element="div"/>
                <div class="form-group">
                    <form:label path="profile.address">Address</form:label>
                    <form:input type="text" path="profile.address" class="form-control" id="address"
                                placeholder="10 Downing Street"/>
                </div>
                <form:errors path="profile.phoneNumber" cssClass="alert-danger" element="div"/>
                <div class="form-group">
                    <form:label path="profile.phoneNumber">Phone number</form:label>
                    <form:input type="text" path="profile.phoneNumber" class="form-control" id="phoneNumber"
                                placeholder="+86 123 123 123"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form:form>
            <a href="${app_entry_path}/login" class="btn btn-dark" aria-pressed="true" role="button">Already
                got an account</a>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
