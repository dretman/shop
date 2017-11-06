<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container" id="gallery">

    <div class="row carousel-holder">

        <div class="col-md-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">

                    <c:if test="${not empty applicationTopList}">
                    <c:set var="count" value="0"/>
                    <c:forEach var="application" items="${applicationTopList}">

                    <c:choose>
                    <c:when test="${count == 0}">
                    <div class="item active">
                        </c:when>
                        <c:otherwise>
                        <div class="item">
                            </c:otherwise>
                            </c:choose>
                            <img onclick="openDetails(${application.id})" class="slide-image" src="${uploadedImagesFolder}/${application.picture512}" alt="">
                        </div>
                        <c:set var="count" value="1"/>
                        </c:forEach>
                        </c:if>

                    </div>
                    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function openDetails(id) {
        window.location.href = "${applicationName}/app/"+id;
    }
</script>