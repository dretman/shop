<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">

    <div class="row">

        <div class="col-md-7">
            <a href="#">
                <img class="img-responsive" src="${uploadedImagesFolder}/${app.picture512}" alt="">
            </a>
        </div>

        <div class="col-md-5">
            <h3>
                <small>Application Name</small>
                &nbsp;&nbsp;<c:out value="${app.applicationName}"/></h3>
            <h4>
                <small>Category Name</small>
                &nbsp;&nbsp;<c:out value="${app.category.name}"/></h4>
            <h4>
                <small>Package Name</small>
                &nbsp;&nbsp;<c:out value="${app.packageName}"/></h4>
            <h4>
                <small>Downloads Quantity</small>
                &nbsp;&nbsp;<c:out value="${app.downloadsQuantity}"/></h4>
            <h4 id="date">
                <small>Upload Date</small>
                &nbsp;&nbsp;</h4>
            <p>
                <small>Description</small>
                &nbsp;&nbsp;<c:out value="${app.description}"/></p>
            <a id="downloadLink" class="btn btn-primary" href="${applicationName}/download/app">DOWNLOAD <span
                    class="glyphicon glyphicon-chevron-down"></span></a>
        </div>
    </div>

</div>

<script>

    $(function () {
        uploadDate = new Date(<c:out value="${app.uploadDate.getTime()}"/>);
        $("#date").append($.format.date(uploadDate, "dd/MM/yyyy hh:mm:ss"));
    });

</script>


