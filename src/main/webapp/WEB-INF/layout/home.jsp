<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Page Content -->
<div class="container">


    <div class="row">

        <div class="col-md-3">
            <div class="list-group">
                <div class="categoryList">
                    <c:if test="${not empty categoryList}">
                        <c:forEach var="category" items="${categoryList}">
                            <a onclick="getAppByCategoryId(${category.id})" class="list-group-item">${category.name}</a>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>


        <div class="col-md-3">

            <div class="row" id="appList">
            </div>

        </div>

    </div>

</div>
<!-- /.container -->

<script>

    function showApps(dataArr) {

        $("#appList").empty();
        $.each(dataArr, function (index, value) {
            $("#appList").append("<div class='col-md-7'>" +
                    "<a href='${applicationName}/app/" + value.id + "'><img class='img-thumbnail' src='data:image/jpg;base64," + value.image128 + "' alt=''></a>" +
                    "</div><div class='col-md-2'><h3>" + value.applicationName + "</h3>" +
                    "<a class='btn btn-primary' href='${applicationName}/app/" + value.id + "'>View Project" +
                    "<span class='glyphicon glyphicon-chevron-right'></span></a> </div>").hide().show('fast');
        });
    }

    function getAppByCategoryId(id) {
        var url = "http://${host}:${port}${applicationName}/service/application/category/" + id;
        $.ajax({
            url: url,
            contentType: "application/json",
            success: function (data, textStatus) {
                console.log(data);
                showApps(data);
            }
        });
    }

</script>