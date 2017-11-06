<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login Page</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">


</head>

<!-- Page Content -->
<div class="container">

    <form method="post" style="width: 50%">
        <h2 class="form-signin-heading">Please sign in</h2>
        <p>
            <label for="username">Username</label>
            <input type="text" id="username" name="username" class="form-control" />
        </p>
        <p>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" class="form-control"/>
        </p>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <button type="submit" class="btn">Log in</button>
    </form>

    <c:if test="${param.error != null}">
        <p style="color:red">
            Invalid username or password.
        </p>
    </c:if>

</div>