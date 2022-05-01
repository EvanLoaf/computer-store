<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="${app}/WEB-INF/pages/util/head.jsp"/>
    <title>Update user</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <form action="${app}/dispatcher?command=update_user&id=${user.id}" method="post">
                <div class="form-group">
                    <label for="input_email">Email address</label>
                    <input type="email" name="old_email" value="${user.email}"
                           id="input_old_email" hidden>
                    <input type="email" name="old_email" value="${user.email}" class="form-control"
                           aria-describedby="emailHelp"
                           placeholder="mail@mail.com" disabled>
                    <input type="email" name="email" value="${email}" class="form-control" id="input_email"
                           aria-describedby="emailHelp"
                           placeholder="mail@mail.com">
                </div>
                <div class="form-group">
                    <label for="input_password">Password</label>
                    <input type="password" name="old_password" value="${user.password}" class="form-control"
                           placeholder="********" disabled>
                    <input type="password" name="password" value="${password}" class="form-control" id="input_password"
                           placeholder="********">
                </div>
                <div class="form-group">
                    <label for="input_first_name">First name</label>
                    <input type="text" name="old_first_name" value="${user.firstName}" class="form-control"
                           placeholder="John" disabled>
                    <input type="text" name="first_name" value="${first_name}" class="form-control"
                           id="input_first_name"
                           placeholder="John">
                </div>
                <div class="form-group">
                    <label for="input_last_name">Last name</label>
                    <input type="text" name="old_last_name" value="${user.lastName}" class="form-control"
                           placeholder="Doe" disabled>
                    <input type="text" name="last_name" value="${last_name}" class="form-control" id="input_last_name"
                           placeholder="Doe">
                </div>
                <div class="form-group">
                    <label for="input_phone_number">Phone number</label>
                    <input type="text" name="old_phone_number" value="${user.phoneNumber}" class="form-control"
                           placeholder="1-800-1" disabled>
                    <input type="text" name="phone_number" value="${phone_number}" class="form-control"
                           id="input_phone_number"
                           placeholder="1-800-1">
                </div>
                <div class="form-group">
                    <label for="input_additional_info">Add info</label>
                    <input type="text" name="old_additional_info" value="${user.additionalInfo}" class="form-control"
                           placeholder="info" disabled>
                    <input type="text" name="additional_info" value="${additional_info}" class="form-control" id="input_additional_info"
                           placeholder="info">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <div class="col-md-4">
            <c:out value="${sessionScope.user.name}"/>
            <jsp:include page="${app}/WEB-INF/pages/util/ads.jsp"/>
            <a href="${pageContext.request.contextPath}/dispatcher?command=users" class="btn btn-primary"
               aria-pressed="true" role="button">USERS PAGE</a>
            <a href="${pageContext.request.contextPath}/dispatcher?command=items" class="btn btn-light"
               aria-pressed="true" role="button">ITEMS PAGE</a>
        </div>
    </div>
</div>
<jsp:include page="${app}/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
