<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<s:url value="/" var="home"/>
<s:url value="/upload" var="uploadUrl"/>
<s:url value="/logoutUrl" var="logoutUrl"/>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Application Shop</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="${home}">Home Page</a>
                </li>
                <li>
                    <a href="${uploadUrl}">Upload Application</a>
                </li>
                <li>
                    <a href="${logoutUrl}">Logout</a>
                </li>
                <li>
                    <a href="">You are logged in as <strong>${userName}</strong></a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>