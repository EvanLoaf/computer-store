<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <c:set var="app" value="${pageContext.request.contextPath}"/>
    <jsp:include page="${app}/WEB-INF/pages/util/head.jsp"/>
    <title>Error</title>
</head>
<body>
<div class="container">
    <div class="row align-content-center">
        <div class="col-md">
            <img src="${app}/resources/images/error_cat.jpg" class="img-fluid align-content-center error-img" alt="We are sorry" title="Sad cat">
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
            <div class="alert alert-primary" role="alert">
                Oops.. Something went wrong<br>
                Our engineers are already working on the problem
            </div>
            <small class="text-muted">
                What happened: <c:out value="${error_description}"/>
                <br>Error status code: <c:out value="${error}"/>
            </small>
        </div>
        <div class="col-md-4">
        </div>
    </div>
</div>
<jsp:include page="${app}/WEB-INF/pages/util/js.jsp"/>
</body>
</html>
