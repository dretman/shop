<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<s:url value="/" var="home"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/css/error-page.css" />">
</head>
<body>
<div>
    <img src="<c:url value="/resources/image/error/error.png"/>"/>
    <a href="${home}">Home Page</a>
</div>

</body>
</html>
